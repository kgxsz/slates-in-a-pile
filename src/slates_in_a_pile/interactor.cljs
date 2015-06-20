(ns slates-in-a-pile.interactor
  (:require [dommy.core :as dommy :refer-macros [sel sel1]]
            [om.core :as om]
            [goog.events :as events]
            [goog.dom :as dom]
            [goog.events.EventType :as EventType]))

(defn handle-arrow-key-press
  [direction cursor]
  (let [slate-heights (map #(dommy/px % :height) (sel :.slate))
        slate-boundaries (drop-last (reductions #(+ %1 %2) 0 slate-heights))
        current-scroll (.-y (dom/getDocumentScroll))
        current-slate (reduce #(if (>= current-scroll %2) (inc %1) %1) 0 (rest slate-boundaries))
        current-slate-keyword (keyword (str "slate-" (inc current-slate)))
        flush-with-slate? #(contains? (set slate-boundaries) current-scroll)
        scroll-to-slate #(. js/window (scrollTo 0 (nth slate-boundaries % current-scroll)))
        transact-n-for-slate! #(let [c (om/root-cursor cursor)
                                     ks [:slates current-slate-keyword :n]]
                                 (when (get-in c ks) (om/transact! c ks %)))]
    (case direction
      :up (scroll-to-slate (if (and (pos? current-slate) (flush-with-slate?))
                             (dec current-slate)
                             current-slate))
      :down (scroll-to-slate (if (< current-slate (count slate-boundaries))
                               (inc current-slate)
                               current-slate))
      :right (transact-n-for-slate! inc)
      :left (transact-n-for-slate! #(if (pos? %) (dec %) %)))))

(defn setup-key-press-interaction
  "This function sets up a listener idempotently on key
   down events and prevents default bubbling for arrow
   key events before forwarding them to relevant functions.
   The cursor is required for interactions that affect the
   application's state."
  [cursor]
  (events/removeAll js/window)
  (events/listen js/window EventType/KEYDOWN
    (fn [e]
      (let [key-map {37 :left 38 :up 39 :right 40 :down}]
        (when (contains? (set (keys key-map)) (.-keyCode e))
          (.preventDefault e)
          (handle-arrow-key-press (get key-map (.-keyCode e)) cursor))))))

(ns slates-in-a-pile.interactor
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om]
            [goog.events :as events]
            [goog.dom :as dom]
            [goog.events.EventType :as EventType]))

(defn handle-arrow-key-press
  [d cursor]
  (let [slate-heights (map
                        #(.. (dom/getElement (name %)) -offsetHeight)
                        (-> @cursor :slates keys))
        slate-boundaries (reduce #(conj %1 (+ %2 (last %1))) [0] slate-heights)
        current-scroll (.. (dom/getDocumentScroll) -y)
        scroll-to (fn [h] (. js/window (scrollTo 0 h)))
        transact-cursor! (partial om/transact! (om/root-cursor cursor))]
    (println "Current scroll is:" current-scroll)
    (println "Slate heights are:" slate-heights)
    (println "Slate offsets are:" slate-boundaries)
    (case d
      :up (scroll-to (- current-scroll 200))
      :down (scroll-to (+ current-scroll 200))
      :right (transact-cursor! [:slates :slate-1 :n] inc)
      :left (transact-cursor! [:slates :slate-1 :n] #(if (pos? %) (dec %) %)))))

(defn setup-key-press-interaction
  "This function sets up a listener on key down events
   and prevents default bubbling for arrow key events
   before forwarding them to relevant functions. The
   cursor is required for interactions that affect the
   application's state."
  ;; TODO try using a vector of codes and use it as a set and zipmap into directions
  [cursor]
  (events/listen js/window EventType/KEYDOWN
    (fn [e]
      (when (contains? #{37 38 39 40} (.-keyCode e))
       (.preventDefault e)
       (case (.-keyCode e)
         38 (handle-arrow-key-press :up cursor)
         40 (handle-arrow-key-press :down cursor)
         39 (handle-arrow-key-press :right cursor)
         37 (handle-arrow-key-press :left cursor))))))

(ns slates-in-a-pile.interactor
  (:require [om.core :as om]
            [goog.events :as events]
            [goog.events.EventType :as EventType]))

(defn handle-vertical-arrow-key-press
  [d]
  (let [
        scroll-to (fn [h] (. js/window (scrollTo 0 h)))]
    (case d
      :up (scroll-to 200)
      :down (scroll-to 500))))

(defn handle-horizontal-arrow-key-press
  [cursor d]
  (case d
    :right (om/transact! (om/root-cursor cursor) [:slates :slate-1 :n] inc)
    :left (om/transact! (om/root-cursor cursor) [:slates :slate-1 :n] #(if (pos? %) (dec %) %))))

(defn setup-key-press-interaction
  "This function sets up a listener on key down events
   and prevents default bubbling for arrow key events
   before forwarding them to relevant functions. The
   cursor is required for interactions that affect the
   application's state."
  [cursor]
  (events/listen js/window EventType/KEYDOWN
    (fn [e]
      (when (contains? #{37 38 39 40} (.-keyCode e))
       (.preventDefault e)
       (case (.-keyCode e)
         38 (handle-vertical-arrow-key-press :up)
         40 (handle-vertical-arrow-key-press :down)
         39 (handle-horizontal-arrow-key-press cursor :right)
         37 (handle-horizontal-arrow-key-press cursor :left))))))

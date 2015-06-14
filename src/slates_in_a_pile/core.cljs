(ns slates-in-a-pile.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [slates-in-a-pile.utils.helpers :refer [slate]])
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]))

(enable-console-print!)

(defonce application-state (atom {:dummy-key "dummy-value"}))

(defn log [& s] (apply println s))

(defn say-direction [d] (log "Received arrow key press: " (name d)))

(defn handle-arrow-key-press
  [key-code]
  (let [scroll-to (fn [h] (. js/window (scrollTo 0 h)))])
  (case key-code
    38 (say-direction :up)
    40 (say-direction :down)
    39 (say-direction :right)
    37 (say-direction :left)))

(defonce arrow-key-press-chan
  (let [arrow-key-press-chan (chan)
        handle-key-press (partial
                           (fn [e] (when (contains? #{37 38 39 40} (.-keyCode e))
                                     (.preventDefault e)
                                     (put! arrow-key-press-chan (.-keyCode e)))))]
    (log "Registering arrow key press events to the arrow-key-press-chan")
    (events/listen js/window EventType/KEYDOWN handle-key-press)
    (go (while true (handle-arrow-key-press (<! arrow-key-press-chan))))
    arrow-key-press-chan))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (slate :slate-1
      (dom/h1 "SLATES IN A PILE")
      (dom/h5 "A disgustingly lightweight presentation framework"))))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (slate :slate-2
      (dom/h1 "SLATE 2")
      (dom/h5 "The second slate in the pile."))))

(defcomponent slate-3
  [cursor owner]
  (render-state
    [_ _]
    (slate :slate-3
      (dom/h1 "SLATE 3")
      (dom/h5 "The third slate in the pile."))))

(defcomponent pile
  [cursor owner]
  (render-state [_ _]
    (log "Rendered root component with state: " cursor)
    (dom/div
      {:id "pile"}
      (om/build slate-1 cursor)
      (om/build slate-2 cursor)
      (om/build slate-3 cursor))))

(om/root
  pile
  application-state
  {:target (sel1 :#application-container)})

(ns slates-in-a-pile.core
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [slates-in-a-pile.utils.helpers :refer [slate]])
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [slates-in-a-pile.slates.slate-1 :refer [slate-1]]
            [slates-in-a-pile.slates.slate-2 :refer [slate-2]]
            [slates-in-a-pile.slates.slate-3 :refer [slate-3]]))

(enable-console-print!)

(defonce application-state
  (atom {:slates {:slate-1 {}
                  :slate-2 {}
                  :slate-3 {}}}))

(defn say-direction [d] (println "Received arrow key press:" (name d)))

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
    (println "Registering arrow key press events to the arrow-key-press-chan")
    (events/listen js/window EventType/KEYDOWN handle-key-press)
    (go (while true (handle-arrow-key-press (<! arrow-key-press-chan))))
    arrow-key-press-chan))

(defcomponent pile
  [{:keys [slates] :as cursor} owner]
  (render-state [_ _]
    (println "Rendered root component with state:" cursor)
    (dom/div
      {:id "pile"}
      (om/build slate-1 cursor)
      (om/build slate-2 cursor)
      (om/build slate-3 cursor))))

(om/root
  pile
  application-state
  {:target (sel1 :#application-container)})

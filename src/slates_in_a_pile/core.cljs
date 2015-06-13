(ns slates-in-a-pile.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]))

(enable-console-print!)

(defn log [& m] (.log js/console (apply str m)))

(defonce application-state
  (atom {:dummy-key "dummy-value"}))

(defn arrow-key-handler [d] (log "Arrow key event received: " (name d)))

(defn key-down-handler
  [event]
  (case (.-keyCode event)
    38 (do (.preventDefault event) (arrow-key-handler :up))
    40 (do (.preventDefault event) (arrow-key-handler :down))
    39 (do (.preventDefault event) (arrow-key-handler :right))
    37 (do (.preventDefault event) (arrow-key-handler :left))
    "default"))

(defonce key-down-chan
  (let [key-down-chan (chan)]
    (log "registering key down events to the key down channel")
    (events/listen js/window EventType/KEYDOWN #(put! key-down-chan %))
    (go (while true (key-down-handler (<! key-down-chan))))
    key-down-chan))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (dom/div
      {:class "slate-container"}
      (dom/div
        {:id "slate-1" :class "slate"}
        (dom/section
          {:class "slate-content"}
          (dom/h1 "SLATE 1")
          (dom/h5 "The first slate in the pile."))))))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (dom/div
      {:class "slate-container"}
      (dom/div
        {:id "slate-2" :class "slate"}
        (dom/section
          {:class "slate-content"}
          (dom/h1 "SLATE 2")
          (dom/h5 "The second slate in the pile."))))))

(defcomponent slate-3
  [cursor owner]
  (render-state
    [_ _]
    (dom/div
      {:class "slate-container"}
      (dom/div
        {:id "slate-3" :class "slate"}
        (dom/section
          {:class "slate-content"}
          (dom/h1 "SLATE 3")
          (dom/h5 "The third slate in the pile."))))))

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

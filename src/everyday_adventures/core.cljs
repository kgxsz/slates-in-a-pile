(ns everyday-adventures.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]
            [cljs.core.async :refer [<! put! chan]]))

(defonce state (atom {:slate-number 1 :slates []}))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (div {:id "slates-container"}
      (div
        {:class "slate-container"}
        (div {:id "slate-1" :class "slate"}
          (div {:class "slate-content"})))
      (div
        {:class "slate-container"}
        (div {:id "slate-2" :class "slate"}
          (div {:class "slate-content"})))
      (div
        {:class "slate-container"}
        (div {:id "slate-3" :class "slate"}
          (div {:class "slate-content"}))))))

(defn init []
  (om/root
    root-component
    state
    {:target (dom/getElement "root-container")}))

(defn arrow-key-handler [direction]
  (let [slate-height (.. (dom/getElementByClass "slate") -offsetHeight)
        current-offset (.. (dom/getDocumentScroll) -y)
        offset-flush (zero? (rem current-offset slate-height))
        current-slate (int (/ current-offset slate-height))
        scroll-to-slate (fn [n] (. js/window (scrollTo 0 (* n slate-height))))]
    (case direction
      :up (if offset-flush (scroll-to-slate (dec current-slate)) (scroll-to-slate current-slate))
      :down (scroll-to-slate (inc current-slate)))))

(defn key-down-handler [event]
  (let [key-code (.. event -keyCode)]
    (case key-code
      38 (do (.preventDefault event) (arrow-key-handler :up))
      40 (do (.preventDefault event) (arrow-key-handler :down))
      "default")))

(defonce key-down-event-emitter
  (events/listen js/window EventType/KEYDOWN #(key-down-handler %)))

(init)

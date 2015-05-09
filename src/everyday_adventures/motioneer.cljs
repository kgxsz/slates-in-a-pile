(ns everyday-adventures.motioneer
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]
            [cljs.core.async :refer [<! put! chan]]))

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

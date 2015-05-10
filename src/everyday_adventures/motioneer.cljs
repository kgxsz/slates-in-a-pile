(ns everyday-adventures.motioneer
  (:require [goog.dom :as dom]))

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
  (let [key-code (.. event -keyCode)
        with-prevent-default (fn [f x] (.preventDefault event) (f x))]
    (case key-code
      38 (with-prevent-default arrow-key-handler :up)
      40 (with-prevent-default arrow-key-handler :down)
      "default")))
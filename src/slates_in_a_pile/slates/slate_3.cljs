(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljsjs.d3 :as d3]))

(defn initialize-canvas []
  (-> (.select js/d3 "#slate-3 .content")
      (.append "svg")
      (.attr "class" "canvas")
      (.attr "width" 700)
      (.attr "height" 150)))

(defcomponent slate-3
  [cursor owner]
  (did-mount
    [_]
    (initialize-canvas)
    (let [svg (.select js/d3 "svg")
          circle (-> svg (.selectAll "circle") (.data (clj->js (range 1))))
          circleEnter (-> circle .enter (.append "circle"))]
      (-> circleEnter
          (.attr "cy" 75)
          (.attr "cx" (fn [d i] (+ 30 (* i 50))))
          (.attr "r" 5))))
  (did-update
    [_ _ _]
    (let [data (clj->js (range (:n cursor)))
          svg (.select js/d3 "svg")]
      (-> svg
          (.selectAll "circle")
          (.data data)
          .exit
          .remove)
      (-> svg
          (.selectAll "circle")
          (.data data)
          .enter
          (.append "circle")
          (.attr "cy" 75)
          (.attr "cx" (fn [d i] (+ 30 (* i 50))))
          (.attr "r" 5))))
  (render-state
    [_ _]
    (println "Rendering slate-3 component with cursor:" cursor)
    (slate :slate-3
      (dom/h1 "SLATE 3"))))

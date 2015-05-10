(ns everyday-adventures.slates.slate-2
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))

(defcomponent slate-2 [state owner]
  (did-mount [_]
    (let [svg (-> js/d3 (.select "#slate-2 .slate-content") (.append "svg") (.attr "width" 1000) (.attr "height" 300))
          circle (-> svg (.selectAll "circle") (.data (clj->js (vec (range 20)))))]
      (-> circle .enter (.append "circle") (.attr "r" 6) (.attr "cy" 150) (.attr "cx" (fn [d i] (+ 20 (* i 50)))))))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-2" :class "slate"}
        (div
          {:class "slate-content"}
          (h1 "SLATE 2")
          (p "The second slate in the pile."))))))

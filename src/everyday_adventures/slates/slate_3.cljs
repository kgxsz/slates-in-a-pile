(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))



(defn horizontal [_ i] 500)

(defn vertical [_ i] 150)

(defn move-right []
  (-> js/d3 (.selectAll "circle") .transition (.duration 2000) (.attr "cx" (fn [_ i] (+ 800 (* i 20))))))

(defn move-left []
  (-> js/d3 (.selectAll "circle") .transition (.duration 2000) (.attr "cx" (fn [_ i] (+ 200 (* (- i) 20))))))

(defcomponent slate-3 [state owner]
  (did-mount [_]
    (let [svg (-> js/d3 (.select "#slate-3 .slate-content") (.append "svg") (.attr "width" 1000) (.attr "height" 300))
          circle (-> svg (.selectAll "circle") (.data (clj->js [1 1 1 1 1])))]
      (-> circle .enter (.append "circle") (.attr "r" 6) (.attr "cy" vertical) (.attr "cx" horizontal))))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

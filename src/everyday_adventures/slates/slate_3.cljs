(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))



(defn horizontal [_ i] 500)

(defn vertical [_ i] 150)

(defn move-right []
  (-> js/d3 (.selectAll "circle") .transition (.duration 1000) (.attr "cx" (fn [_ i] (+ 800 (* i 20))))))

(defn move-left []
  (-> js/d3 (.selectAll "circle") .transition (.duration 1000) (.attr "cx" (fn [_ i] (+ 200 (* (- i) 20))))))

(defcomponent slate-3 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "Slate 3 mounted with step: " step)
    (let [svg (-> js/d3 (.select "#slate-3 .slate-content") (.append "svg") (.attr "width" 1000) (.attr "height" 400))
          circle (-> svg (.selectAll "circle") (.data #js [5 4 3 2 1]))
          texts (-> svg (.selectAll "text") (.data #js ["b1"]) .enter
                    (.append "text") (.text (fn [d i] d)) (.attr "dy" 217) (.attr "dx" 127)
                    (.attr "font-size" "small"))]
      (-> circle .enter (.append "circle") (.attr "r" 7) (.attr "cy" 200) (.attr "cx" (fn [d i] (+ 20 (* 100 (+ i step))))))))
  (did-update [_ _ _]
    (.log js/console "Slate 3 updated with step: " step)
    (let [svg (-> js/d3 (.select "#slate-3 .slate-content"))
          circle (-> svg (.selectAll "circle"))]
      (-> circle .transition (.duration 700) (.attr "cx" (fn [d i] (+ 20 (* 100 (+ i step))))))))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))

#_(circle (-> svg (.selectAll "circle") (.data #js [3 2 1])))
#_(-> circle .enter (.append "circle") (.attr "r" 7) (.attr "cy" 214) (.attr "cx" (fn [d i] (+ 330 (* 300 (+ i step))))))

(defn create-svg []
  (-> js/d3 (.select "#slate-3 .slate-content")
            (.append "svg")
            (.attr "width" 1088)
            (.attr "height" 400)))

(defn create-service-line [svg]
  (let [g (-> svg (.append "g") (.attr "transform" "translate(0, 200)"))]
    (-> g (.selectAll "rect") (.data (clj->js [{:width 100 :height 28 :x 5 :rx 10 :ry 10}]))
          .enter (.append "rect")
          (.attr "ry" (fn [d _] (.-ry d)))
          (.attr "rx" (fn [d _] (.-rx d)))
          (.attr "x" (fn [d _] (.-x d)))
          (.attr "height" (fn [d _] (.-height d)))
          (.attr "width" (fn [d _] (.-width d))))
    (-> g (.selectAll "text") (.data #js ["service a"]) .enter
                    (.append "text") (.text (fn [d i] d)) (.attr "dy" 19) (.attr "dx" 24))
    (-> g (.selectAll "line")
                  (.data (clj->js [{:x1 105 :x2 320 :y1 14 :y2 14 :class "dashed"}
                                   {:x1 340 :x2 620 :y1 14 :y2 14 :class "solid"}
                                   {:x1 640 :x2 920 :y1 14 :y2 14 :class "solid"}]))
                  .enter (.append "line")
                  (.attr "x1" (fn [d _] (.-x1 d)))
                  (.attr "x2" (fn [d _] (.-x2 d)))
                  (.attr "y1" (fn [d _] (.-y1 d)))
                  (.attr "y2" (fn [d _] (.-y2 d)))
                  (.attr "class" (fn [d _] (.-class d))))))

(defn create-dev-line [svg]
  (let [g (-> svg (.append "g") (.attr "transform" "translate(280, 100)"))]
    (-> g (.selectAll "rect") (.data (clj->js [{:width 40 :height 40 :y 5 :rx 35 :ry 35}]))
          .enter (.append "rect")
          (.attr "ry" (fn [d _] (.-ry d)))
          (.attr "rx" (fn [d _] (.-rx d)))
          (.attr "y" (fn [d _] (.-y d)))
          (.attr "height" (fn [d _] (.-height d)))
          (.attr "width" (fn [d _] (.-width d))))
    (-> g (.selectAll "polygon") (.data #js [1]) .enter (.append "polygon")
        (.attr "points" "10,0 40,0 25,25") (.attr "style" "fill:#343434"))
    (-> g (.selectAll "text") (.data #js ["dev"]) .enter
                    (.append "text") (.text (fn [d i] d)) (.attr "dy" 19) (.attr "dx" 24))
    (-> g (.selectAll "line")
                  (.data (clj->js [{:x1 40 :x2 40 :y1 20 :y2 200 :class "solid"}]))
                  .enter (.append "line")
                  (.attr "x1" (fn [d _] (.-x1 d)))
                  (.attr "x2" (fn [d _] (.-x2 d)))
                  (.attr "y1" (fn [d _] (.-y1 d)))
                  (.attr "y2" (fn [d _] (.-y2 d)))
                  (.attr "class" (fn [d _] (.-class d))))))

(defcomponent slate-3 [{:keys [step] :as cursor} owner]
  (init-state [_]
    {:width 1000
     :height 400})
  (did-mount [_]
    (.log js/console "Slate 3 mounted with step: " step)
    (let [svg (create-svg)]
      (create-service-line svg)
      (create-dev-line svg)
      (om/set-state! owner :svg svg)))
  (did-update [_ _ _]
    (.log js/console "Slate 3 updated with step: " step)
    (let [svg (-> js/d3 (.select "#slate-3 .slate-content"))
          circle (-> svg (.selectAll "circle"))]
      (-> circle .transition (.duration 700) (.attr "cx" (fn [d i] (+ 330 (* 300 (+ i step))))))))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

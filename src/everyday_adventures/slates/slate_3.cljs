(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))

(defn create-svg []
  (-> js/d3 (.select "#slate-3 .slate-content")
            (.append "svg")
            (.attr "width" 1088)
            (.attr "height" 400)))

(defn use-attribute [element attribute]
  (.attr element attribute (fn [d _] (aget d attribute))))

(defn create-element-join [parent-element element-name data]
    (let [join (-> parent-element (.selectAll element-name) (.data (clj->js data)))
          attributes (->> data first keys (mapv name))
          use-attributes (fn [element attributes] (reduce use-attribute element attributes))]
      (-> join .enter (.append element-name) (use-attributes attributes))))

(defn create-text-join [parent-element data]
    (let [join (-> parent-element (.selectAll "text") (.data (clj->js data)))
          use-text (fn [element] (.text element (fn [d _] (aget d "text"))))]
      (-> join .enter (.append "text") (use-attribute "dx") (use-attribute "dy") (use-text))))

(defn create-element-grouping [parent-element class x y]
  (let [transform (str "translate(" x "," y ")")]
    (-> parent-element (.append "g") (.attr "class" class) (.attr "transform" transform))))

(defn create-service-static [parent-element service y-offset]
  (let [g (create-element-grouping parent-element "service" 0 y-offset)]
    (create-element-join g "rect" [{:width 100 :height 28 :x 5 :rx 10 :ry 10}])
    (create-text-join g [{:text service :dx 24 :dy 19}])
    (create-element-join g "line" [{:x1 105 :x2 320 :y1 14 :y2 14 :class "dashed"}
                                   {:x1 340 :x2 620 :y1 14 :y2 14 :class "solid"}
                                   {:x1 640 :x2 920 :y1 14 :y2 14 :class "solid"}])))

(defn create-env-static [parent-element env x-offset]
  (let [g (create-element-grouping parent-element env x-offset 100)]
    (create-element-join g "circle" [{:cx 30 :cy 10 :r 24}])
    (create-text-join g [{:text env :dx 30 :dy 15}])
    (create-element-join g "line" [{:x1 30 :x2 30 :y1 34 :y2 103 :class "solid"}
                                   {:x1 30 :x2 30 :y1 125 :y2 153 :class "solid"}])))

(defcomponent slate-3 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "Slate 3 mounted with step: " step)
    (let [svg (create-svg)]
      (create-service-static svg "service a" 200)
      (create-env-static svg "dev" 300)
      (create-env-static svg "qa" 600)
      (create-env-static svg "prod" 900)
      (om/set-state! owner :svg svg)))
  (did-update [_ _ _]
    (.log js/console "Slate 3 updated with step: " step)
    (let [svg (-> js/d3 (.select "#slate-3 .slate-content"))
          circle (-> svg (.selectAll "circle"))]))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

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

(defn create-service [parent-element service y]
  (let [g (create-element-grouping parent-element "service" 0 y)]
    (create-element-join g "rect" [{:width 100 :height 28 :x 5 :rx 10 :ry 10}])
    (create-text-join g [{:text service :dx 24 :dy 19}])
    (create-element-join g "line" [{:x1 105 :x2 320 :y1 14 :y2 14 :class "dashed"}
                                   {:x1 340 :x2 620 :y1 14 :y2 14}
                                   {:x1 640 :x2 920 :y1 14 :y2 14}
                                   {:x1 940 :x2 970 :y1 14 :y2 14}])))

(defn create-env [parent-element env x]
  (let [g (create-element-grouping parent-element env x 100)]
    (create-element-join g "circle" [{:cx 30 :cy 10 :r 24}])
    (create-text-join g [{:text env :dx 30 :dy 15}])
    (create-element-join g "line" [{:x1 30 :x2 30 :y1 34 :y2 103}
                                   {:x1 30 :x2 30 :y1 125 :y2 153}])))

(defn create-commit [parent-element x y]
  (let [g (create-element-grouping parent-element "commit" x y)]
    (create-element-join g "line" [{:x1 0 :x2 8 :y1 0 :y2 8}
                                   {:x1 8 :x2 0 :y1 6 :y2 14}
                                   {:x1 10 :x2 18 :y1 0 :y2 8}
                                   {:x1 18 :x2 10 :y1 6 :y2 14}
                                   {:x1 20 :x2 28 :y1 0 :y2 8}
                                   {:x1 28 :x2 20 :y1 6 :y2 14}])))

(defn create-build [parent-element {:keys [label opacity x y]}]
  (let [group (create-element-grouping parent-element "build" x y)]
    (-> group (.attr "opacity" opacity))
    (create-text-join group [{:text label :dx 7 :dy 15}])
    (create-element-join group "circle" [{:cx 0 :cy 0 :r 7}])
    group))

(defn update-build [build {:keys [opacity x y]}]
  (let [transform (str "translate(" x "," y ")")]
    (-> build .transition (.duration 700) (.attr "transform" transform) (.attr "opacity" opacity))))

(defn gen-build-1-data [step]
  {:label "b1"
   :opacity (if (pos? step) 1 0)
   :x (+ 330 (if (> step 1)  (* (dec step) 300) 0))
   :y 214})

(defcomponent slate-3 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "Slate 3 mounted with step: " step)
    (let [svg (create-svg)
          commit (create-commit svg 201 207)
          build (create-build svg (gen-build-1-data step))]
      (create-service svg "service a" 200)
      (create-env svg "dev" 300)
      (create-env svg "qa" 600)
      (create-env svg "prod" 900)
      (om/set-state! owner :svg svg)
      (om/set-state! owner :commit commit)
      (om/set-state! owner :build build)))
  (did-update [_ _ _]
    (.log js/console "Slate 3 updated with step: " step)
    (let [build (om/get-state owner :build)]
      (update-build build (gen-build-1-data step))))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

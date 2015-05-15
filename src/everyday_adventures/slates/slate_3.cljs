(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))


;;;;;;;;;;;;;;;;;;;;; helpers ;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-canvas []
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

(defn create-element-grouping [parent-element class x y opacity]
  (let [transform (str "translate(" x "," y ")")]
    (-> parent-element (.append "g") (.attr "class" class) (.attr "transform" transform) (.attr "opacity" opacity))))



;;;;;;;;;;;;;;;;;;;;; statics ;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-service [parent-element service y]
  (let [group (create-element-grouping parent-element "service" 0 y 1)]
    (create-element-join group "rect" [{:width 100 :height 28 :x 5 :rx 10 :ry 10}])
    (create-text-join group [{:text service :dx 24 :dy 19}])
    (create-element-join group "line" [{:x1 105 :x2 320 :y1 14 :y2 14 :class "dashed"}
                                       {:x1 340 :x2 620 :y1 14 :y2 14}
                                       {:x1 640 :x2 920 :y1 14 :y2 14}
                                       {:x1 940 :x2 970 :y1 14 :y2 14}])))

(defn create-env [parent-element env x]
  (let [group (create-element-grouping parent-element env x 100 1)]
    (create-element-join group "circle" [{:cx 30 :cy 10 :r 24}
                                         {:cx 30 :cy 114 :r 10}])
    (create-text-join group [{:text env :dx 30 :dy 15}])
    (create-element-join group "line" [{:x1 30 :x2 30 :y1 34 :y2 103}
                                       {:x1 30 :x2 30 :y1 125 :y2 153}])))

(defn register-statics [parent-element]
  (create-env parent-element "dev" 300)
  (create-env parent-element "qa" 600)
  (create-env parent-element "prod" 900)
  (create-service parent-element "service a" 200))



;;;;;;;;;;;;;;;;;;;;; animatics ;;;;;;;;;;;;;;;;;;;;;;;;

(defn generate-trigger-data [step]
  {:opacity (if (contains? #{1 4} step) 1 0)
   :x (if ( contains? #{1 2 4} step) 271 121)
   :y 207})

(defn create-trigger [parent-element {:keys [opacity x y]}]
  (let [group (create-element-grouping parent-element "trigger" x y opacity)]
    (create-element-join group "line" [{:x1 0 :x2 8 :y1 0 :y2 8}
                                       {:x1 8 :x2 0 :y1 6 :y2 14}
                                       {:x1 10 :x2 18 :y1 0 :y2 8}
                                       {:x1 18 :x2 10 :y1 6 :y2 14}
                                       {:x1 20 :x2 28 :y1 0 :y2 8}
                                       {:x1 28 :x2 20 :y1 6 :y2 14}])
    group))

(defn register-trigger [parent-element owner step]
  (->> (generate-trigger-data step)
       (create-trigger parent-element)
       (om/set-state! owner :trigger)))

(defn animize-trigger [trigger step]
  (when (contains? #{1 2 3} step)
    (-> trigger
        .transition (.duration 1000) (.attr "transform" "translate(231,207)") (.attr "opacity" 1)
        .transition (.duration 1000) (.attr "transform" "translate(151,207)") (.attr "opacity" 0))))



;;;;;;;;;;;;;;;;;;;;; dynamics ;;;;;;;;;;;;;;;;;;;;;;;;

(defn generate-builds-data [step]
  [{:label "b1"
    :opacity (if (contains? #{1 2 3} step) 1 0)
    :x (+ 330 (if (contains? #{1 2 3} step) (* (dec step) 300) (if (> step 3) 600 0)))
    :y 214}
   {:label "b2"
    :opacity (if (contains? #{2 3 4} step) 1 0)
    :x (+ 330 (if (contains? #{2 3 4} step) (* (- step 2) 300) (if (> step 4) 600 0)))
    :y 214}
   {:label "b3"
    :opacity (if (contains? #{3 4 5} step) 1 0)
    :x (+ 330 (if (contains? #{3 4 5} step) (* (- step 3) 300) (if (> step 5) 600 0)))
    :y 214}])

(defn create-build [parent-element {:keys [label opacity x y]}]
  (let [group (create-element-grouping parent-element "build" x y opacity)]
    (create-text-join group [{:text label :dx 10 :dy 16}])
    (create-element-join group "circle" [{:cx 0 :cy 0 :r 7}])
    group))

(defn register-builds [parent-element owner step]
  (->> (generate-builds-data step)
       (map #(create-build parent-element %))
       (om/set-state! owner :builds)))

(defn dynamize-build [build {:keys [opacity x y]}]
  (let [transform (str "translate(" x "," y ")")]
    (-> build .transition (.duration 1000) (.attr "transform" transform) (.attr "opacity" opacity))))

(defn dynamize-builds [builds step]
  (mapv dynamize-build builds (generate-builds-data step)))

;;;;;;;;;;;;;;;;;;;;; om component ;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponent slate-3 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "Slate 3 mounted with step: " step)
    (let [canvas (create-canvas)]
      (register-statics canvas)
      (register-trigger canvas owner step)
      (register-builds canvas owner step)
      (om/set-state! owner :canvas canvas)))

  (did-update [_ _ _]
    (.log js/console "Slate 3 updated with step: " step)
    (let [builds (om/get-state owner :builds)
          trigger (om/get-state owner :trigger)]
      (animize-trigger trigger step)
      (dynamize-builds builds step)))

  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"})))))

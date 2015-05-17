(ns everyday-adventures.slates.slate-5
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]
            [everyday-adventures.slates.slate-utils :refer [create-element-join
                                                            create-element-grouping
                                                            create-text-join]]))
(defn select-canvas []
  (.select js/d3 "#slate-5 .canvas"))

(defn initialize-canvas []
  (-> (.select js/d3 "#slate-5 .slate-content")
      (.append "svg")
      (.attr "class" "canvas")
      (.attr "width" 1088)
      (.attr "height" 600)))

(defn vectorize-constructs [constructs-data]
  (let [select-by-id (fn [id] (.select js/d3 (str "#slate-5 #" id)))]
    (mapv (comp select-by-id :id) constructs-data)))



;; statics ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; statics get created with constant data, and are always static ;;

(def hosts-properties
  [{:id "host-1"
    :text "host 1"
    :x 140
    :y 150 }])

(def services-properties
  [{:id "service-a"
    :text "service a"
    :x 155
    :y 185}])

(defn create-host-construct
  [{:keys [id text x y opacity] :or {x 0 opacity 1}}]
  (let [group-data {:class "host" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 160 :height 140 :opacity 0.6}])
    (create-text-join group [{:text text :dx 38 :dy 24}])))

(defn create-service-construct
  [{:keys [id text x y opacity] :or {y 100 opacity 1}}]
  (let [group-data {:class "service" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 130 :height 90 :rx 3 :ry 3}])
    (create-text-join group [{:text text :dx 42 :dy 20}])))

(defn initialize-statics []
  (doseq [[create-construct construct-properties] [[create-host-construct hosts-properties]
                                                   [create-service-construct services-properties]]]
    (mapv (partial create-construct) construct-properties)))

;; dynamics ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; dynamics get created with data as a function of step, and are subject to dynamizing as a function of step ;;

(def system-metrics-properties
  [{:id "system-metric"
    :text "system metrics"
    :opacity (fn [step] (condp >= step 0 0 1))
    :x 270
    :y 170}])

(def service-metrics-properties
  [{:id "service-metric"
    :text "service metrics"
    :opacity (fn [step] (condp >= step 1 0 1))
    :x 270
    :y 200}])

(def custom-events-properties
  [{:id "custom-event"
    :text "custom events"
    :opacity (fn [step] (condp >= step 2 0 1))
    :x 270
    :y 230}])

(def service-logs-properties
  [{:id "service-log"
    :text "service logs"
    :opacity (fn [step] (condp >= step 3 0 1))
    :x 270
    :y 260}])

(def hosted-graphites-properties
  [{:id "hosted-graphite"
    :text "hosted graphite"
    :opacity (fn [step] (condp >= step 0 0 1))
    :x 615
    :y 135}])

(def kibanas-properties
  [{:id "kibana"
    :text "kibana"
    :opacity (fn [step] (condp >= step 0 0 1))
    :x 350
    :y 320}])

(defn create-system-metric-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "system-metric" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-text-join group [{:text text :dx 130 :dy 5}])
    (create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    (create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 190 :x2 320 :y1 0 :y2 0 :opacity 0.6}])
    (create-element-join group "polygon" [{:points "320,-6 330,0 320,6" :opacity 0.6}])))


(defn create-service-metric-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "service-metric" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-text-join group [{:text text :dx 130 :dy 5}])
    (create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    (create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 190 :x2 320 :y1 0 :y2 0 :opacity 0.6}])
    (create-element-join group "polygon" [{:points "320,-6 330,0 320,6" :opacity 0.6}])))

(defn create-custom-event-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "custom-event" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-text-join group [{:text text :dx 129 :dy 5}])
    (create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    (create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 188 :x2 291 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 290 :x2 290 :y1 1 :y2 30 :opacity 0.6}])
    (create-element-join group "polygon" [{:points "296,30 290,40 284,30" :opacity 0.6}])))

(defn create-service-log-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "custom-event" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-text-join group [{:text text :dx 119 :dy 5}])
    (create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    (create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 168 :x2 201 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 200 :x2 200 :y1 1 :y2 40 :opacity 0.6}])
    (create-element-join group "polygon" [{:points "206,40 200,50 194,40" :opacity 0.6}])))

(defn create-hosted-graphite-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "hosted-graphite" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 150 :height 100}])
    (create-text-join group [{:text text :dx 63 :dy 18}])
    #_(create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    #_(create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 168 :x2 201 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 200 :x2 200 :y1 1 :y2 40 :opacity 0.6}])
    #_(create-element-join group "polygon" [{:points "206,40 200,50 194,40" :opacity 0.6}])))

(defn create-kibana-construct [{:keys [id text x y opacity]}]
  (let [group-data {:class "kibana" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 150 :height 100}])
    (create-text-join group [{:text text :dx 33 :dy 18}])
    #_(create-element-join group "circle" [{:cx -5 :cy 0 :r 5 :opacity 0.6}])
    #_(create-element-join group "line" [{:x1 0 :x2 70 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 168 :x2 201 :y1 0 :y2 0 :opacity 0.6}
                                       {:x1 200 :x2 200 :y1 1 :y2 40 :opacity 0.6}])))

(defn evaluate [constructs-properties step]
  (let [apply-step (fn [v step] (if (fn? v) (v step) v))]
    (mapv
      #(reduce (fn [m [k v]] (assoc m k (apply-step v step))) {} %)
      constructs-properties)))

(defn initialize-dynamics [step]
  (doseq [[create-construct constructs-properties] [[create-system-metric-construct system-metrics-properties]
                                                    [create-service-metric-construct service-metrics-properties]
                                                    [create-custom-event-construct custom-events-properties]
                                                    [create-service-log-construct service-logs-properties]
                                                    [create-hosted-graphite-construct hosted-graphites-properties]
                                                    [create-kibana-construct kibanas-properties]]]
    (mapv (partial create-construct) (evaluate constructs-properties step))))

(defn dynamize-construct [construct {:keys [opacity x y]}]
  (let [transform (str "translate(" x "," y ")")]
    (-> construct .transition (.duration 1000) (.attr "transform" transform) (.attr "opacity" opacity))))

(defn dynamize-dynamics [step]
  (doseq [constructs-properties [system-metrics-properties
                                 service-metrics-properties
                                 custom-events-properties
                                 service-logs-properties
                                 hosted-graphites-properties
                                 kibanas-properties]]
    (mapv dynamize-construct
          (vectorize-constructs constructs-properties)
          (evaluate constructs-properties step))))



;; om component ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponent slate-5 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "Slate 5 mounted with step: " step)
    (initialize-canvas)
    (initialize-statics)
    (initialize-dynamics step))

  (did-update [_ _ _]
    (.log js/console "Slate 5 updated with step: " step)
    (dynamize-dynamics step))

  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-5" :class "slate"}
        (div
          {:class "slate-content"})))))

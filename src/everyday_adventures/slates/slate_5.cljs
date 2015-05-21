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

(def services-properties
  [{:id "service-a" :text "service a" :y 200}
   {:id "service-b" :text "service b" :y 280}
   {:id "service-c" :text "service c" :y 360}
   {:id "service-d" :text "service d" :y 440}])

(def environments-properties
  [{:id "dev" :text "dev" :x 300}
   {:id "qa" :text "qa" :x 600}
   {:id "prod" :text "prod" :x 900}])

(defn create-service-construct
  [{:keys [id text x y opacity] :or {x 0 opacity 1}}]
  (let [group-data {:class "service" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 100 :height 28 :x 5 :rx 10 :ry 10}])
    (create-text-join group [{:text text :dx 24 :dy 19}])
    (create-element-join group "line" [{:x1 105 :x2 320 :y1 14 :y2 14 :class "dashed"}
                                       {:x1 340 :x2 620 :y1 14 :y2 14}
                                       {:x1 640 :x2 920 :y1 14 :y2 14}
                                       {:x1 940 :x2 970 :y1 14 :y2 14}])))

(defn create-environment-construct
  [{:keys [id text x y opacity] :or {y 100 opacity 1}}]
  (let [group-data {:class "environment" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "circle" [{:cx 30 :cy 10 :r 24}
                                         {:cx 30 :cy 114 :r 10}
                                         {:cx 30 :cy 194 :r 10}
                                         {:cx 30 :cy 274 :r 10}
                                         {:cx 30 :cy 354 :r 10}])
    (create-text-join group [{:text text :dx 30 :dy 15}])
    (create-element-join group "line" [{:x1 30 :x2 30 :y1 34 :y2 103}
                                       {:x1 30 :x2 30 :y1 125 :y2 185}
                                       {:x1 30 :x2 30 :y1 205 :y2 265}
                                       {:x1 30 :x2 30 :y1 285 :y2 345}
                                       {:x1 30 :x2 30 :y1 365 :y2 393}])))

(defn initialize-statics []
  (doseq [[create-construct construct-properties] [[create-service-construct services-properties]
                                                   [create-environment-construct environments-properties]]]
    (mapv (partial create-construct) construct-properties)))



;; animatics ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; animatics get created with constant data, but are subject to animizing as a function of step ;;

(def triggers-properties
  [{:id "trigger-a"
    :x 121
    :y 207
    :opacity 0
    :animize (fn [self step]
               (when (contains? #{1 2 3 4 8 10} step)
                 (-> self
                     .transition
                     (.duration 700)
                     (.attr "transform" "translate(231,207)")
                     (.attr "opacity" 1)
                     .transition
                     (.duration 600)
                     (.attr "transform" "translate(121,207)")
                     (.attr "opacity" 0))))}
   {:id "trigger-b"
    :x 121
    :y 287
    :opacity 0
    :animize (fn [self step]
               (when (contains? #{2 4} step)
                 (-> self
                     .transition
                     (.duration 700)
                     (.attr "transform" "translate(231,287)")
                     (.attr "opacity" 1)
                     .transition
                     (.duration 600)
                     (.attr "transform" "translate(121,287)")
                     (.attr "opacity" 0))))}
   {:id "trigger-c"
    :x 121
    :y 367
    :opacity 0
    :animize (fn [self step]
               (when (contains? #{1 3} step)
                 (-> self
                     .transition
                     (.duration 700)
                     (.attr "transform" "translate(231,367)")
                     (.attr "opacity" 1)
                     .transition
                     (.duration 600)
                     (.attr "transform" "translate(121,367)")
                     (.attr "opacity" 0))))}
   {:id "trigger-d"
    :x 121
    :y 447
    :opacity 0
    :animize (fn [self step]
               (when (contains? #{2 3 4} step)
                 (-> self
                     .transition
                     (.duration 700)
                     (.attr "transform" "translate(231,447)")
                     (.attr "opacity" 1)
                     .transition
                     (.duration 600)
                     (.attr "transform" "translate(121,447)")
                     (.attr "opacity" 0))))}])

(defn create-trigger-construct
  [{:keys [id x y opacity]}]
  (let [group-data {:class "trigger" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "line" [{:x1 0 :x2 8 :y1 0 :y2 8}
                                       {:x1 8 :x2 0 :y1 6 :y2 14}
                                       {:x1 10 :x2 18 :y1 0 :y2 8}
                                       {:x1 18 :x2 10 :y1 6 :y2 14}
                                       {:x1 20 :x2 28 :y1 0 :y2 8}
                                       {:x1 28 :x2 20 :y1 6 :y2 14}])))

(defn initialize-animatics []
  (doseq [[create-construct constructs-properties] [[create-trigger-construct triggers-properties]]]
    (mapv (partial create-construct) constructs-properties)))

(defn animize-animatics [step]
  (let [animizers (mapv :animize triggers-properties)
        triggers (vectorize-constructs triggers-properties)]
    (mapv (fn [animizer trigger] (animizer trigger step)) animizers triggers)))



;; dynamics ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; dynamics get created with data as a function of step, and are subject to dynamizing as a function of step ;;

(def builds-properties
  [{:id "b1-a"
    :class "service-a"
    :text "b1"
    :opacity (fn [step] (condp >= step 5 1 0))
    :x 930
    :y 214}
   {:id "b2-a"
    :class "service-a"
    :text "b2"
    :opacity (fn [step] (condp >= step 7 1 0))
    :x (fn [step] (condp >= step 5 630 930))
    :y 214}
   {:id "b3-a"
    :class "service-a"
    :text "b3"
    :opacity (fn [step] (condp >= step 8 1 0))
    :x (fn [step] (condp >= step 0 330 5 560 7 630 930))
    :y 214}
   {:id "b4-a"
    :class "service-a"
    :text "b4"
    :opacity (fn [step] (condp >= step 0 0 9 1 0))
    :x (fn [step] (condp >= step 1 330 5 515 7 560 8 630 930))
    :y 214}
   {:id "b5-a"
    :class "service-a"
    :text "b5"
    :opacity (fn [step] (condp >= step 1 0 1))
    :x (fn [step] (condp >= step 2 330 5 470 7 515 8 560 9 630 930))
    :y 214}
   {:id "b6-a"
    :class "service-a"
    :text "b6"
    :opacity (fn [step] (condp >= step 2 0 1))
    :x (fn [step] (condp >= step 3 330 5 425 7 470 8 515 9 560 630))
    :y 214}
   {:id "b7-a"
    :class "service-a"
    :text "b7"
    :opacity (fn [step] (condp >= step 3 0 1))
    :x (fn [step] (condp >= step 7 330 8 470 9 515 560))
    :y 214}
   {:id "b8-a"
    :class "service-a"
    :text "b8"
    :opacity (fn [step] (condp >= step 7 0 1))
    :x (fn [step] (condp >= step 9 330 515))
    :y 214}
   {:id "b9-a"
    :class "service-a"
    :text "b9"
    :opacity (fn [step] (condp >= step 9 0 1))
    :x 330
    :y 214}


   {:id "b2-b"
    :class "service-b"
    :text "b2"
    :opacity (fn [step] (condp >= step 5 1 0))
    :x 930
    :y 294}
   {:id "b3-b"
    :class "service-b"
    :text "b3"
    :opacity 1
    :x (fn [step] (condp >= step 5 630 930))
    :y 294}
   {:id "b4-b"
    :class "service-b"
    :text "b4"
    :opacity 1
    :x (fn [step] (condp >= step 1 330 5 560 630))
    :y 294}
   {:id "b5-b"
    :class "service-b"
    :text "b5"
    :opacity (fn [step] (condp >= step 1 0 1))
    :x (fn [step] (condp >= step 3 330 5 515 560))
    :y 294}
   {:id "b6-b"
    :class "service-b"
    :text "b6"
    :opacity (fn [step] (condp >= step 3 0 1))
    :x 330
    :y 294}


   {:id "b4-c"
    :class "service-c"
    :text "b4"
    :opacity (fn [step] (condp >= step 5 1 0))
    :x 930
    :y 374}
   {:id "b5-c"
    :class "service-c"
    :text "b5"
    :opacity 1
    :x (fn [step] (condp >= step 5 630 930))
    :y 374}
   {:id "b6-c"
    :class "service-c"
    :text "b6"
    :opacity 1
    :x (fn [step] (condp >= step 0 330 5 560 630))
    :y 374}
   {:id "b7-c"
    :class "service-c"
    :text "b7"
    :opacity (fn [step] (condp >= step 0 0 1))
    :x (fn [step] (condp >= step 2 330 5 515 560))
    :y 374}
   {:id "b8-c"
    :class "service-c"
    :text "b8"
    :opacity (fn [step] (condp >= step 2 0 1))
    :x 330
    :y 374}


   {:id "b2-d"
    :class "service-d"
    :text "b2"
    :opacity (fn [step] (condp >= step 5 1 0))
    :x 930
    :y 454}
   {:id "b3-d"
    :class "service-d"
    :text "b3"
    :opacity 1
    :x (fn [step] (condp >= step 5 630 930))
    :y 454}
   {:id "b4-d"
    :class "service-d"
    :text "b4"
    :opacity 1
    :x (fn [step] (condp >= step 1 330 5 560 630))
    :y 454}
   {:id "b5-d"
    :class "service-d"
    :text "b5"
    :opacity (fn [step] (condp >= step 1 0 1))
    :x (fn [step] (condp >= step 2 330 5 515 560))
    :y 454}
   {:id "b6-d"
    :class "service-d"
    :text "b6"
    :opacity (fn [step] (condp >= step 2 0 1))
    :x (fn [step] (condp >= step 3 330 5 470 515))
    :y 454}
   {:id "b7-d"
    :class "service-d"
    :text "b7"
    :opacity (fn [step] (condp >= step 3 0 1))
    :x 330
    :y 454}])

(defn create-build-construct [{:keys [id class text x y opacity]}]
  (let [group-data {:class (str "build " class) :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-text-join group [{:text text :dx 10 :dy 16}])
    (create-element-join group "circle" [{:cx 0 :cy 0 :r 7}])))

(def uberbuilds-properties
  [{:id "uberbuild-1"
    :x (fn [step] (condp >= step 5 600 900))
    :y 190
    :opacity (fn [step] (condp >= step 4 0 6 0.4 0))}
   {:id "uberbuild-2"
    :x 600
    :y 190
    :opacity (fn [step] (condp >= step 5 0 6 0.4 0))}])

(defn create-uberbuild-construct [{:keys [id x y opacity]}]
  (let [group-data {:class "uberbuild" :id id :x x :y y :opacity opacity}
        group (create-element-grouping (select-canvas) group-data)]
    (create-element-join group "rect" [{:width 60 :height 295 :rx 10 :ry 10}])))

(defn evaluate [constructs-properties step]
  (let [apply-step (fn [v step] (if (fn? v) (v step) v))]
    (mapv
      #(reduce (fn [m [k v]] (assoc m k (apply-step v step))) {} %)
      constructs-properties)))

(defn initialize-dynamics [step]
  (doseq [[create-construct constructs-properties] [[create-build-construct builds-properties]
                                                    [create-uberbuild-construct uberbuilds-properties]]]
    (mapv (partial create-construct) (evaluate constructs-properties step))))

(defn dynamize-construct [construct {:keys [opacity x y]}]
  (let [transform (str "translate(" x "," y ")")]
    (-> construct .transition (.duration 1000) (.attr "transform" transform) (.attr "opacity" opacity))))

(defn dynamize-dynamics [step]
  (doseq [constructs-properties [builds-properties
                                 uberbuilds-properties]]
    (mapv dynamize-construct
          (vectorize-constructs constructs-properties)
          (evaluate constructs-properties step))))



;; om component ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponent slate-5 [{:keys [step] :as cursor} owner]
  (did-mount [_]
    (.log js/console "slate 5 mounted with step: " step)
    (initialize-canvas)
    (initialize-statics)
    (initialize-animatics)
    (initialize-dynamics step))

  (did-update [_ _ _]
    (.log js/console "slate 5 updated with step: " step)
    (animize-animatics step)
    (dynamize-dynamics step))

  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-5" :class "slate"}
        (div
          {:class "slate-content"})))))

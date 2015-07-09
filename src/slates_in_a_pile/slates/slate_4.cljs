(ns slates-in-a-pile.slates.slate-4
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]
            [slates-in-a-pile.utils.data :refer [data]]
            [cljsjs.d3 :as d3]))

(def canvas-dimensions [700 450])

(def charge-force (-> js/d3 .-layout .force
                      (.charge -120)
                      (.linkDistance 35)
                      (.size (clj->js canvas-dimensions))))

(defn enterfy-data
  [canvas data class svg-type]
  (-> (.selectAll canvas (str "." class))
      (.data data) .enter
      (.append svg-type)
      (.attr "class" class)))

(defn datarize-attributes
  [entity relations]
  (doseq [{:keys [attr data-korks]} relations]
    (.attr entity attr (fn [d] (apply (partial aget d) data-korks)))))

(defn update-entities
  [link node]
  (datarize-attributes link [{:attr "x1" :data-korks ["source" "x"]}
                             {:attr "y1" :data-korks ["source" "y"]}
                             {:attr "x2" :data-korks ["target" "x"]}
                             {:attr "y2" :data-korks ["target" "y"]}])
  (datarize-attributes node [{:attr "cx" :data-korks "x"}
                             {:attr "cy" :data-korks "y"}]))

(defn graphify
  [data]
  (let [canvas (.select js/d3 "#slate-4 #canvas")
        link (-> (enterfy-data canvas (.-links data) "link" "line")
                 (.style "stroke-width" (fn [d] (->> d .-value (.sqrt js/Math)))))
        node (-> (enterfy-data canvas (.-nodes data) "node" "circle")
                 (.attr "r" 6)
                 (.call (.-drag charge-force)))]
    (-> node
        (.append "title")
        (.text (fn [d] (.-name d))))
    (-> charge-force
        (.nodes (.-nodes data))
        (.links (.-links data))
        .start)
    (-> charge-force (.on "tick" #(update-entities link node)))))

(defcomponent slate-4
  [{:keys [n] :as state} owner]
  (did-mount
    [_]
    (graphify (clj->js data)))
  (render-state
    [_ _]
    (println "Rendering slate-4 component with state:" state)
    (slate :slate-4
      (dom/div
        {:class "title"}
        (dom/h1 "MORE")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Here is an example using d3's force directed graph to represent")
        (dom/p "the co-occurences of characters in 'Les Miserables'."))
      (dom/svg
        {:id "canvas"}))))

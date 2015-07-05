(ns slates-in-a-pile.slates.slate-4
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]
            [slates-in-a-pile.utils.data :refer [data]]
            [cljsjs.d3 :as d3]))

(def colour (-> js/d3 .-scale .category20))
(def d3-force (-> js/d3
                  .-layout
                  .force
                  (.charge -120)
                  (.linkDistance 40)
                  (.size (clj->js [700 450]))))

(defn graphify
  [data]
  (let [
        canvas (-> js/d3 (.select "#slate-4 #canvas"))
        link (-> canvas
                 (.selectAll ".link")
                 (.data (.-links data))
                 .enter
                 (.append "line")
                 (.attr "class" "link")
                 (.style "stroke" "#B1B2D7")
                 (.style "stroke-width" "1.5px"))
        node (-> canvas
                 (.selectAll ".node")
                 (.data (.-nodes data))
                 .enter
                 (.append "circle")
                 (.attr "class" "node")
                 (.attr "r" 6)
                 (.style "stroke" "#EAEAF4")
                 (.style "stroke-width" "1.5px")
                 (.style "fill" "#2E3192")
                 (.call (.-drag d3-force)))]
    (-> d3-force
        (.nodes (.-nodes data))
        (.links (.-links data))
        .start)
    (-> node
        (.append "title")
        (.text (fn [d] (.-name d))))
    (-> d3-force
        (.on "tick" #(do (-> link
                             (.attr "x1" (fn [d] (-> d .-source .-x)))
                             (.attr "y1" (fn [d] (-> d .-source .-y)))
                             (.attr "x2" (fn [d] (-> d .-target .-x)))
                             (.attr "y2" (fn [d] (-> d .-target .-y))))
                         (-> node
                             (.attr "cx" (fn [d] (.-x d)))
                             (.attr "cy" (fn [d] (.-y d)))))))))

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
        {:id "title"}
        (dom/h1 "MORE")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Here is example using d3's force directed graph to represent the co-occurences of characters in 'Les Miserables'."))
      (dom/svg
        {:id "canvas"}))))

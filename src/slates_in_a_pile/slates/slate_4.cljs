(ns slates-in-a-pile.slates.slate-4
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]
            [cljsjs.d3 :as d3]))

(defn circle-select
  [data]
  (-> (.select js/d3 "#slate-4 #canvas")
      (.selectAll "circle")
      (.data data)))

(defn circle-enter
  [circle]
  (-> (.enter circle)
      (.append "circle")
      (.attr "cy" (fn [_ i] (->> (mod i 5) (* 20) (- 130))))
      (.attr "cx" (fn [_ i] (->> (/ i 5) int (* 20) (+ 30))))
      (.attr "r" 5)))

(defcomponent slate-4
  [{:keys [n] :as state} owner]
  (did-mount
    [_]
    (-> (clj->js (range n)) circle-select circle-enter))
  (did-update
    [_ _ _]
    (let [circle (circle-select (clj->js (range n)))]
      (-> circle circle-enter)
      (-> circle .exit .remove)))
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
      (dom/div
        {:id "canvas-container"}
        (dom/h1 "n")
        (dom/h3 "=")
        (dom/h2 (if (< n 1000) n 999))
        (dom/h3 "=")
        (dom/svg {:id "canvas"})))))

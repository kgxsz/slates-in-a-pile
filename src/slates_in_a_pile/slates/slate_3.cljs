(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]
            [cljsjs.d3 :as d3]))

(defn circle-select
  [data]
  (-> (.select js/d3 "#slate-3 #canvas")
      (.selectAll "circle")
      (.data data)))

(defn circle-enter
  [circle]
  (-> (.enter circle)
      (.append "circle")
      (.attr "cy" (fn [_ i] (->> (mod i 5) (* 20) (- 130))))
      (.attr "cx" (fn [_ i] (->> (/ i 5) int (* 20) (+ 30))))
      (.attr "r" 5)))

(defcomponent slate-3
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
    (println "Rendering slate-3 component with state:" state)
    (slate :slate-3
      (dom/div
        {:id "title"}
        (dom/h1 "HOW")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "This is a slate; It's state is represented by an integer n. The left & right arrow keys can change the value of n."))
      (dom/div
        {:id "canvas-container"}
        (dom/h1 "n")
        (dom/h3 "=")
        (dom/h2 (if (< n 1000) n 999))
        (dom/h3 "=")
        (dom/svg {:id "canvas"}))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Each time n changes, some function of n generates data. That data can be displayed with the power of d3.")))))

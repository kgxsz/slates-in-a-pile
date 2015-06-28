(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljsjs.d3 :as d3]))

(defn circle
  [data]
  (-> (.select js/d3 "#slate-3 #canvas")
      (.selectAll "circle")
      (.data data)))

(defn circle-enter
  [circle]
  (-> (.enter circle)
      (.append "circle")
      (.attr "cy" (fn [_ i] (+ 130 (- (* (mod i 5) 20)))))
      (.attr "cx" (fn [_ i] (+ 30 (* ( int (/ i 5)) 20))))
      (.attr "r" 5)))

(defn pointer
  []
  (dom/svg
    {:id "pointer"}
    (dom/line {:x1 20 :x2 48 :y1 7 :y2 35 :opacity 0.5})
    (dom/line {:x1 20 :x2 48 :y1 53 :y2 25 :opacity 0.5})))

(defcomponent slate-3
  [{:keys [n] :as cursor} owner]
  (did-mount
    [_]
    (-> (clj->js (range n)) circle circle-enter))
  (did-update
    [_ _ _]
    (let [circle (circle (clj->js (range n)))]
      (-> circle circle-enter)
      (-> circle .exit .remove)))
  (render-state
    [_ _]
    (println "Rendering slate-3 component with cursor:" cursor)
    (slate :slate-3
      (dom/div
        {:id "title"}
        (dom/h1 "HOW")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Each slate has it's state defined as an integer n. The left & right arrow keys can change the value of n."))
      (dom/div
        {:id "canvas-container"}
        (dom/h1 "n")
        (dom/h3 "=")
        (dom/h2 (if (< n 1000) n 999))
        (dom/h3 "=")
        (dom/svg {:id "canvas"})))))

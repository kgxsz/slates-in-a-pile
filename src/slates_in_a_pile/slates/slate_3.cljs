(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]
            [cljsjs.d3 :as d3]))

(defn get-canvas [] (.select js/d3 "#slate-3 #canvas"))

(defn datarize-circle
  [canvas data]
  (-> canvas (.selectAll "circle") (.data data)))

(defn enterfy-circle
  [circle]
  (-> (.enter circle)
      (.append "circle")
      (.attr "cy" (fn [_ i] (->> (mod i 5) (* 20) (- 130))))
      (.attr "cx" (fn [_ i] (->> (/ i 5) int (* 20) (+ 30))))
      (.attr "r" 5)))

(defn exitize-circle [circle] (-> circle .exit .remove))

(defcomponent slate-3
  [{:keys [n] :as state} owner]
  (did-mount
    [_]
    (let [canvas (get-canvas)
          data (clj->js (range n))]
      (enterfy-circle (datarize-circle canvas data))))
  (did-update
    [_ _ _]
    (let [canvas (get-canvas)
          data (clj->js (range n))]
      ((juxt enterfy-circle exitize-circle) (datarize-circle canvas data))))
  (render-state
    [_ _]
    (println "Rendering slate-3 component with state:" state)
    (slate :slate-3
      (dom/div
        {:class "title"}
        (dom/h1 "HOW")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "This is a slate; It's state is represented by an integer n.")
        (dom/p "The left & right arrow keys can change the value of n."))
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
        (dom/p "Each time n changes, some function of n generates data.")
        (dom/p "That data can be displayed with the power of d3.")))))

(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljsjs.d3 :as d3]))

(defn initialize-canvas
  []
  (-> (.select js/d3 "#slate-3 .content")
      (.append "svg")
      (.attr "class" "canvas")
      (.attr "width" 700)
      (.attr "height" 150)))

(defn circle
  [data]
  (-> (.select js/d3 "svg")
      (.selectAll "circle")
      (.data data)))

(defn circle-enter
  [circle]
  (-> (.enter circle)
      (.append "circle")
      (.attr "cy" 75)
      (.attr "cx" (fn [d i] (+ 30 (* i 50))))
      (.attr "r" 5)))

(defcomponent slate-3
  [{:keys [n] :as cursor} owner]
  (did-mount
    [_]
    (initialize-canvas)
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
      (dom/h1 "SLATE 3"))))

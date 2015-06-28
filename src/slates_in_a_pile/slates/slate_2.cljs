(ns slates-in-a-pile.slates.slate-2
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defn pointer
  []
  (dom/svg
    {:id "pointer"}
    (dom/line {:x1 20 :x2 48 :y1 7 :y2 35 :opacity 0.5})
    (dom/line {:x1 20 :x2 48 :y1 53 :y2 25 :opacity 0.5})))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-2 component with cursor:" cursor)
    (slate :slate-2
      (dom/div
        {:id "title"}
        (dom/h1 "WHAT")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Slates in a Pile is a simple template for building beautiful d3 enabled presentations."))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "It's 100% Clojure(Script) - so no messing around with awkward templating and styling languages.")))))

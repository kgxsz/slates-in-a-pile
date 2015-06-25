(ns slates-in-a-pile.slates.slate-2
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-2 component with cursor:" cursor)
    (slate :slate-2
      (dom/div
        {:id "title"}
        (dom/h1 "WHAT?"))
      (dom/div {:id "separator"})
      (dom/div
        {:id "points"}
        (dom/div
          {:class "point"}
          (dom/svg
            {:id "pointer"}
            (dom/line {:x1 1 :x2 7 :y1 4 :y2 10})
            (dom/line {:x1 1 :x2 7 :y1 14 :y2 8}))
          (dom/p "Slates in a Pile is a simple framework for building beautiful d3 enabled presentations."))
        (dom/div
          {:class "point"}
          (dom/svg
            {:id "pointer"}
            (dom/line {:x1 1 :x2 7 :y1 4 :y2 10})
            (dom/line {:x1 1 :x2 7 :y1 14 :y2 8}) )
          (dom/p "It's 100% Clojure(Script), so no templating languages, no styling languages, nothing."))))))

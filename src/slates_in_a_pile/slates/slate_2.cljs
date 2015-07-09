(ns slates-in-a-pile.slates.slate-2
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]))

(defcomponent slate-2
  [state owner]
  (render-state
    [_ _]
    (println "Rendering slate-2 component with state:" state)
    (slate :slate-2
      (dom/div
        {:class "title"}
        (dom/h1 "WHAT")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "Slates in a Pile is a simple template for building")
        (dom/p "beautiful d3 enabled presentations."))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "It's 100% Clojure(Script); so no messing around")
        (dom/p "with awkward templating and styling languages.")))))

(ns slates-in-a-pile.slates.slate-1
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [divider down-arrow]]))

(defcomponent slate-1
  [state owner]
  (render-state
    [_ _]
    (println "Rendering slate-1 component with state:" state)
    (slate :slate-1
      (divider)
      (dom/div
        {:id "heading"}
        (dom/h1 "SLATES")
        (dom/h1 "IN A")
        (dom/h1 "PILE"))
      (divider)
      (dom/div
        {:id "directions"}
        (dom/p "( press") (down-arrow) (dom/p ")")))))

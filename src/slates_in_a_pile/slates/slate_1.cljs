(ns slates-in-a-pile.slates.slate-1
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defn divider
  []
  (dom/svg
    {:class "divider"}
    (dom/line {:x1 0 :x2 465 :y1 2 :y2 2})))

(defn arrow
  []
  (dom/svg
    {:id "arrow"}
    (dom/line {:x1 12 :x2 12 :y1 0 :y2 16})
    (dom/polygon {:points "7,16 17,16 12,23"})))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-1 component with cursor:" cursor)
    (slate :slate-1
      (divider)
      (dom/div
        {:id "title"}
        (dom/h1 "SLATES")
        (dom/h1 "IN A")
        (dom/h1 "PILE"))
      (divider)
      (dom/div
        {:id "directions"}
        (dom/p "( press") (arrow) (dom/p ")")))))

(ns slates-in-a-pile.slates.slate-1
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-1 component with cursor:" cursor)
    (slate :slate-1
      (dom/div
        {:id "title"}
        (dom/span "SLATES")
        (dom/span "IN A")
        (dom/span "PILE"))
      (dom/div {:id "separator"})
      (dom/div
        {:id "blurb"}
        (dom/p "A disgustingly lightweight")
        (dom/p "presentation framework."))
      (dom/div {:id "separator"})
      (dom/div
        {:id "directions"}
        (dom/p "( press ")
        (dom/div
          {:id "arrow"}
          (dom/svg
            (dom/line {:x1 12 :x2 12 :y1 0 :y2 16})
            (dom/polygon {:points "7,16 17,16 12,23"})))
        (dom/p " )")))))

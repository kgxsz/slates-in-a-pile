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
        (dom/span " IN A ")
        (dom/span "PILE"))
     (dom/div
       {:id "blurb"}
       (dom/p "A disgustingly lightweight")
       (dom/p "presentation framework.")))))

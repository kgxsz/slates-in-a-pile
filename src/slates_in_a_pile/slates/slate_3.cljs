(ns slates-in-a-pile.slates.slate-3
  (:require-macros [slates-in-a-pile.utils.helpers :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defcomponent slate-3
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-3 component with cursor: " cursor)
    (slate :slate-3
      (dom/h1 "SLATE 3")
      (dom/h5 "The third slate in the pile"))))

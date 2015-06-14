(ns slates-in-a-pile.slates.slate-1
  (:require-macros [slates-in-a-pile.utils.helpers :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (println "Rendering slate-1 component with cursor:" cursor)
    (slate :slate-1
      (dom/h1 "SLATES IN A PILE")
      (dom/h5 "A disgustingly lightweight presentation framework"))))

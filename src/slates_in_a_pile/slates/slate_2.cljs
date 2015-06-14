(ns slates-in-a-pile.slates.slate-2
  (:require-macros [slates-in-a-pile.utils.helpers :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (slate :slate-2
      (dom/h1 "SLATE 2")
      (dom/h5 "The second slate in the pile"))))

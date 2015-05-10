(ns everyday-adventures.slates.slate-5
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))

(defcomponent slate-5 [state owner]
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-5" :class "slate"}
        (div
          {:class "slate-content"}
          (h1 "SLATE 5")
          (p "The fifth slate in the pile."))))))

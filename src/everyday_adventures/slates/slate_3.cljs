(ns everyday-adventures.slates.slate-3
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p svg]]))

(defcomponent slate-3 [state owner]
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-3" :class "slate"}
        (div
          {:class "slate-content"}
          (h1 "SLATE 3")
          (p "The third slate in the pile."))))))

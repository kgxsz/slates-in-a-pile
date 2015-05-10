(ns everyday-adventures.slates.slate-1
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p svg]]))

(defcomponent slate-1 [state owner]
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-1" :class "slate"}
        (div
          {:class "slate-content"}
          (h1 "SLATE 1")
          (p "The first slate in the pile."))))))

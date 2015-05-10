(ns everyday-adventures.slates.slate-1
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 h2 h3 h4 h5 h6 p]]))

(defcomponent slate-1 [state owner]
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-1" :class "slate"}
        (div
          {:class "slate-content"}
          (h4 "EVERYDAY ADVENTURES")
          (h6 "WITH")
          (h1 "μSERVICES"))))))

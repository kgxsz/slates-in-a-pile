(ns everyday-adventures.slates.slate-4
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]))

(defcomponent slate-4 [cursor owner]
  (did-mount [_]
    (.log js/console "Slate 4 step at mount is: " (:step cursor)))
  (did-update [_ _ _]
    (.log js/console "Slate 4 step at update is: " (:step cursor)))
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-4" :class "slate"}
        (div
          {:class "slate-content"}
          (h1 "SLATE 4")
          (p "The fourth slate in the pile."))))))

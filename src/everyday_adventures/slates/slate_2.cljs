(ns everyday-adventures.slates.slate-2
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h2 h3 h4 h6 p img]]))

(defcomponent slate-2 [state owner]
  (render-state [_ _]
    (div
      {:class "slate-container"}
      (div
        {:id "slate-2" :class "slate"}
        (div
          {:class "slate-content"}
          (h3 "A SINGLE WORD")
          (div
            {:id "colleague-1" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "complexity")))
          (div
            {:id "colleague-2" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "choreography")))
          (div
            {:id "colleague-3" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "buzz-word")))
          (div
            {:id "colleague-4" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "fiddly")))
          (div
            {:id "colleague-5" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "posh")))
          (div
            {:id "colleague-6" :class "colleague"}
            (div
              {:id "image"}
              (div {:id "band"})
              (h6
                {:id "word"}
                "maturity"))))))))

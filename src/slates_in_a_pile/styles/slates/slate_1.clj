(ns slates-in-a-pile.styles.slates.slate-1
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}
    [:.content
     [:#title
      [:span
        (nth-child "odd" {:font-size "60px"})]]]]])

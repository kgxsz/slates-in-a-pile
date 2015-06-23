(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-crimson colours)}
   [:.backdrop {:background-color (:soft-crimson colours)}
    [:.content
     [:h1 {:padding-bottom "150px"}]]]])

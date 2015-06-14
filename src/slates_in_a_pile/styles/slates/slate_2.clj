(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}]])

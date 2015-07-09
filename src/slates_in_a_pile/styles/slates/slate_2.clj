(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-blue colours)}
   [:.backdrop {:background-color (:soft-blue colours)}
    [:.content
     (slate-title (:faded-blue colours))
     [:.blurb {:float "left"
               :text-align "left"
               :width "700px"
               :margin "60px 0 25px 0"}
      (pointer (:hard-blue colours))
      [:p {:float "left"}]]]]])

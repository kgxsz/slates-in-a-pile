(ns slates-in-a-pile.styles.slates.slate-5
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-5
  [:#slate-5 {:color (:hard-pink colours)}
   [:.backdrop {:background-color (:soft-pink colours)}
    [:.content
     (slate-title (:faded-pink colours))
     [:.blurb {:float "left"
               :text-align "left"
               :width "700px"
               :margin "60px 0 25px 0"}
      (pointer (:hard-pink colours))
      [:p {:float "left"}]]
     [:a {:float "left"
          :color (:soft-pink colours)
          :font-size "2em"
          :background-color (:hard-pink colours)
          :opacity 0.5
          :padding "10px 20px"
          :margin "30px 15px"}]
     [:a:hover {:opacity 0.8}]]]])

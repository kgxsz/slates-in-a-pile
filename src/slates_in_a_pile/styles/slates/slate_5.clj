(ns slates-in-a-pile.styles.slates.slate-5
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-5
  [:#slate-5 {:color (:hard-pink colours)}
   [:.backdrop {:background-color (:soft-pink colours)}
    [:.content
     [:#title {:float "left"
               :width "300px"}
      [:h1 {:color (:faded-pink colours)}
        (nth-child
          "1" {:font-size "4em"
               :line-height "0.7em"}
          "2" {:font-size "24em"
               :line-height "0.8em"})]]
     [:.blurb {:float "left"
               :text-align "left"
               :margin "60px 0 25px 0"}
      [:#pointer {:float "left"
                  :width "80px"
                  :height "58px"}
       [:line {:stroke (:hard-pink colours)
               :stroke-width "15"}]]
      [:p {:width "510px"
           :float "left"}]]
     [:a {:float "left"
          :color (:soft-pink colours)
          :font-size "2em"
          :background-color "#F68FCC"
          :padding "10px 20px"
          :margin "30px 15px"}]]]])

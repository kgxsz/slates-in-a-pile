(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-blue colours)}
   [:.backdrop {:background-color (:soft-blue colours)}
    [:.content
     [:#title {:float "left"
               :width "200px"
               :opacity "0.3"}
      [:h1
        (nth-child
          "1" {:font-size "4em"
               :line-height "0.7em"}
          "2" {:font-size "24em"
               :line-height "0.8em"})]]
     [:.blurb {:float "left"
               :text-align "left"
               :margin "60px 0 30px 70px"}
      [:#pointer {:float "left"
                  :position "relative"
                  :width "60px"
                  :height "60px"
                  :top "4px"}
       [:line {:stroke (:hard-blue colours)
               :stroke-width "15"}]]
      [:p {:width "450px"
           :padding "4px 0 0 20px"
           :float "left"}]]]]])

(ns slates-in-a-pile.styles.slates.slate-4
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-4
  [:#slate-4 {:color (:hard-violet colours)}
   [:.backdrop {:background-color (:soft-violet colours)}
    [:.content
     [:#title {:float "left"
               :width "300px"
               :margin "120px 0"}
      [:h1 {:color (:faded-violet colours)}
       (nth-child
         "1" {:font-size "4em"
              :line-height "0.7em"}
         "2" {:font-size "24em"
              :line-height "0.8em"})]]
     [:.blurb {:float "left"
               :text-align "left"
               :margin "40px 0"}
      [:#pointer {:float "left"
                  :width "80px"
                  :height "58px"}
       [:line {:stroke (:hard-violet colours)
               :stroke-width "15"}]]
      [:p {:width "570px"
           :float "left"}]]
     [:#canvas {:width "700px"
                :height "450px"
                :float "left"}]]]])

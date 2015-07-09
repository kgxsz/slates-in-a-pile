(ns slates-in-a-pile.styles.slates.slate-4
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-4
  [:#slate-4 {:color (:hard-violet colours)}
   [:.backdrop {:background-color (:soft-violet colours)}
    [:.content
     (slate-title (:faded-violet colours))
     [:.title {:margin "100px 0"}]
     [:.blurb {:float "left"
               :text-align "left"
               :width "700px"
               :margin "20px 0"}
      (pointer (:hard-violet colours))
      [:p {:float "left"}]]
     [:#canvas {:width "700px"
                :height "450px"
                :float "left"}
      [:.link {:stroke (:faded-violet colours)}]
      [:.node {:stroke (:soft-violet colours)
               :stroke-width 1
               :fill (:hard-violet colours)}]]]]])

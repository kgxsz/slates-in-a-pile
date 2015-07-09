(ns slates-in-a-pile.styles.slates.slate-3
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-3
  [:#slate-3 {:color (:hard-yellow colours)}
   [:.backdrop {:background-color (:soft-yellow colours)}
    [:.content
     (slate-title (:faded-yellow colours))
     [:.blurb {:float "left"
               :text-align "left"
               :width "700px"
               :margin "10px 0"}
      (pointer (:hard-yellow colours))
      [:p {:float "left"}]]
     [:#canvas-container {:float "left"
                          :width "700px"
                          :height "180px"
                          :text-align "left"}
      [:h1 {:float "left"
            :font-size "8em"
            :padding-left "75px"}]
      [:h2 {:float "left"
            :font-size "3em"
            :width "80px"
            :text-align "center"
            :padding-top "63px"}]
      [:h3 {:float "left"
            :font-size "2em"
            :padding "75px 15px 0 15px"}]
      [:#canvas {:width "220px"
                 :height "180px"}
       [:circle {:fill (:hard-yellow colours)}]]]]]])

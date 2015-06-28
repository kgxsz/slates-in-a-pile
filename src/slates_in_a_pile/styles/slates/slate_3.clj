(ns slates-in-a-pile.styles.slates.slate-3
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-3
  [:#slate-3 {:color (:hard-yellow colours)}
   [:.backdrop {:background-color (:soft-yellow colours)}
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
               :margin "0 0 10px 70px"}
      [:#pointer {:float "left"
                  :width "60px"
                  :height "58px"}
       [:line {:stroke (:hard-yellow colours)
               :stroke-width "15"}]]
      [:p {:width "510px"
           :padding-left "20px"
           :float "left"}]]
     [:#canvas-container {:float "left"
                          :width "700px"
                          :height "180px"
                          :text-align "left"
                          :margin-left "70px"}
      [:h1 {:float "left"
            :font-size "8em"}]
      [:h2 {:float "left"
            :font-size "3em"
            :width "80px"
            :text-align "center"
            :padding-top "63px"}]
      [:h3 {:float "left"
            :font-size "2em"
            :padding "75px 15px 0 15px"}]
      [:#canvas {:width "320px"
                 :height "180px"}
       [:circle {:fill (:hard-yellow colours)}]]]]]])

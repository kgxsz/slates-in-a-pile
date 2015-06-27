(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-blue colours)}
   [:.backdrop {:background-color (:soft-blue colours)}
    [:.content {:text-align "left"}
     [:#title {:float "left"
               :width "200px"
               :opacity "0.3"}
      [:h1
        (nth-child
          "1" {:font-size "4em"}
          "2" {:font-size "24em"
               :line-height "0.7em"})]]
     [:#divider {:float "left"
                   :width "2px"
                   :height "300px"}]
     [:#points {:float "left"
                :width "500px"
                :padding "14px 0 120px 0px"
                :text-align "left"}
      [:.point {:padding "7px 0"}
       [:#pointer {:display "inline"
                   :position "relative"
                   :width "18px"
                   :height "18px"
                   :top "4px"}
        [:line {:stroke (:hard-blue colours)
                :stroke-width "3"}]]
       [:p {:display "inline"}]]]]]])

(ns slates-in-a-pile.styles.slates.slate-1
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}
    [:.content
     [:#title
      [:h1 {:display "inline"
            :line-height "0.7em"}
        (nth-child
          "1" {:font-size "6.6em"}
          "2" {:font-size "3em"
               :padding "0 8px"}
          "3" {:font-size "15em"
               :display "block"})]]
     [:#separator {:width "190px"
                   :height "1px"
                   :margin "auto"
                   :background-color (:hard-green colours)}]
     [:#blurb {:padding "20px 0"}]
     [:#directions {:padding-top "100px"}
      [:p {:display "inline"}]
      [:#arrow {:display "inline"
                :position "relative"
                :width "24px"
                :height "24px"
                :top "7px"}]
       [:line {:stroke (:hard-green colours)
               :stroke-width "2"}]
       [:polygon {:stroke (:hard-green colours)
                  :fill (:hard-green colours)}]]]]])

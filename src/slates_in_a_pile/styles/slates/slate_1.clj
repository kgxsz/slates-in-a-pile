(ns slates-in-a-pile.styles.slates.slate-1
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}
    [:.content
     [:#title {:margin-top "10px"}
      [:h1 {:line-height "0.7em"}
        (nth-child
          "1" {:display "inline"
               :font-size "6.6em"}
          "2" {:display "inline"
               :font-size "3em"
               :padding "0 8px"}
          "3" {:font-size "15em"})]]
     [:#divider {:margin " 35px auto 0 auto"
                 :width "460px"
                 :height "10px"
                 :opacity "0.5"}
      [:line {:stroke (:hard-green colours)
              :stroke-width "3"}]]
     [:#directions {:margin-top "60px"}
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

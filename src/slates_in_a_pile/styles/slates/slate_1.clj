(ns slates-in-a-pile.styles.slates.slate-1
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}
    [:.content
     [:#title {:margin "23px auto 27px auto"}
      [:h1 {:line-height "0.7em"}
        (nth-child
          "1" {:display "inline"
               :font-size "6.6em"}
          "2" {:display "inline"
               :font-size "3em"
               :padding "0 8px"}
          "3" {:font-size "15em"})]]
     [:.divider {:margin "auto"
                 :width "460px"
                 :height "5px"}
      [:line {:stroke (:faded-green colours)
              :stroke-width "3"}]]
     [:#directions {:margin "60px auto 0 auto"}
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

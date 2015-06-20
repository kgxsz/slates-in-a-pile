(ns slates-in-a-pile.styles.slates.slate-1
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]))

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}
    [:.content
     [:#title {:margin "40px 0"}
      [:span {:font-weight "bold"}
        (nth-child
          "1" {:font-size "54px"}
          "2" {:font-size "18px"
               :padding "0 8px"}
          "3" {:font-size "120px"
               :line-height "90px"
               :display "block"})]]
     [:#separator {:width "190px"
                   :height "1px"
                   :margin "auto"
                   :background-color (:hard-green colours)}]
     [:#blurb {:margin "25px 0"}]]]])

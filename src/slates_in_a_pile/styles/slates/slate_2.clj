(ns slates-in-a-pile.styles.slates.slate-2
  (:require [garden.def :refer [defstyles]]
            [slates-in-a-pile.styles.utils.variables :refer :all]))

(defstyles slate-2
  [:#slate-2 {:color (:hard-crimson colours)}
   [:.backdrop {:background-color (:soft-crimson colours)}
    [:.content {:padding-left "130px"
                }
     [:#title {:padding-bottom "14px"
               :text-align "left"}]
     [:#separator {:width "620px"
                   :height "1px"
                   :background-color (:hard-crimson colours)}]
     [:#points {:padding "14px 0 120px 0px"
                :text-align "left"}
     [:p {:padding "7px 0"}]]]]])

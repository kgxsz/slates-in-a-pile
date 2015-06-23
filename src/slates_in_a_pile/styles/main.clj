(ns slates-in-a-pile.styles.main
  (:require [garden.core :refer [css]]
            [garden.def :refer [defstyles defrule]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.slates.slate-1 :refer [slate-1]]
            [slates-in-a-pile.styles.slates.slate-2 :refer [slate-2]]
            [slates-in-a-pile.styles.slates.slate-3 :refer [slate-3]]))

(defstyles common
  [:body {:margin 0
          :font-family "Helvetica Neue"}
   [:h1 :h2 :h3 :h4 :h5 :h6 {:margin 0}]
   [:p {:font-size "16px"
        :margin "0"}]])

(defstyles pile
  [:#pile {:position "absolute"
           :height "100%"
           :width "100%"}
   [:.slate {:display "table"
             :height "100%"
             :width "100%"}
    [:.backdrop {:display "table-cell"
                 :vertical-align "middle"}
     [:.content {:margin "60px auto"
                 :width "1000px"
                 :text-align "center"}]]]])

(defstyles base
  common
  pile
  slate-1
  slate-2
  slate-3)

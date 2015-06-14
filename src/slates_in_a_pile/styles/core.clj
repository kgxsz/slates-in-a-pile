(ns slates-in-a-pile.styles.core
  (:require [garden.core :refer [css]]
            [garden.def :refer [defstyles defrule]]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.slates.slate-1 :refer [slate-1]]
            [slates-in-a-pile.styles.slates.slate-2 :refer [slate-2]]
            [slates-in-a-pile.styles.slates.slate-3 :refer [slate-3]]))

(defrule headings :h1 :h2 :h3 :h4 :h5 :h6)

(defstyles base
  [:body {:margin 0
          :font-family "Helvetica Neue"}
   (headings {:margin 0})
   [:h1 {:font-size "80px"}]
   [:h5 {:font-size "22px"}]])

(defstyles pile
  [:#pile {:position "absolute"
           :height "100%"
           :width "100%"}
   [:.slate {:display "table"
             :height "100%"
             :width "100%"}
    [:.backdrop {:display "table-cell"
                 :vertical-align "middle"}
     [:.content {:margin "auto"
                 :width "1000px"
                 :text-align "center"}]]]])

(defstyles main
  base
  pile
  slate-1
  slate-2
  slate-3)

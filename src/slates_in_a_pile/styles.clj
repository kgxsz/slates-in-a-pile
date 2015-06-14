(ns slates-in-a-pile.styles
  (:require [garden.core :refer [css]]
            [garden.def :refer [defstyles defrule]]
            [garden.stylesheet :refer [rule]]))

(def colours
  {:soft-blue "#E5F7FD"
   :hard-blue "#2DBCF0"
   :soft-violet "#EAEAF4"
   :hard-violet "#2E3192"
   :soft-pink "#FDE5F3"
   :hard-pink "#ED0F93"
   :soft-crimson "#F9E7E8"
   :hard-crimson "#C4161C"
   :soft-yellow "#FEF6E8"
   :hard-yellow "#FAA61A"
   :soft-green "#F3F9EB"
   :hard-green "#8CC63F"})

(defrule headings :h1 :h2 :h3 :h4 :h5 :h6)

(defstyles common
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

(defstyles slate-1
  [:#slate-1 {:color (:hard-green colours)}
   [:.backdrop {:background-color (:soft-green colours)}]])

(defstyles slate-2
  [:#slate-2 {:color (:hard-crimson colours)}
   [:.backdrop {:background-color (:soft-crimson colours)}]])

(defstyles slate-3
  [:#slate-3 {:color (:hard-blue colours)}
   [:.backdrop {:background-color (:soft-blue colours)}]])

(defstyles base
  common
  pile
  slate-1
  slate-2
  slate-3)

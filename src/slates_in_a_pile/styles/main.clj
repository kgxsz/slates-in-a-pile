(ns slates-in-a-pile.styles.main
  (:require [garden.core :refer [css]]
            [garden.def :refer [defstyles defrule]]
            [garden.stylesheet :refer :all]
            [slates-in-a-pile.styles.utils.variables :refer :all]
            [slates-in-a-pile.styles.utils.helpers :refer :all]
            [slates-in-a-pile.styles.slates.slate-1 :refer [slate-1]]
            [slates-in-a-pile.styles.slates.slate-2 :refer [slate-2]]
            [slates-in-a-pile.styles.slates.slate-3 :refer [slate-3]]))

(defstyles common
  (at-font-face {:font-family "Source Sans Pro Bold"
                 :src "url('fonts/source-sans-pro-bold.otf')"})
  (at-font-face {:font-family "Source Sans Pro Regular"
                 :src "url('fonts/source-sans-pro-regular.otf')"})
  [:body {:margin 0
          :font-family "Source Sans Pro Regular"}
   [:h1 :h2 :h3 :h4 :h5 :h6 {:margin 0
                             :font-family "Source Sans Pro Bold"}]
   [:p {:font-size "1.4em"
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
     [:.content {:overflow "hidden"
                 :margin "60px auto"
                 :width "1000px"
                 :text-align "center"}]]]])

(defstyles base
  common
  pile
  slate-1
  slate-2
  slate-3)

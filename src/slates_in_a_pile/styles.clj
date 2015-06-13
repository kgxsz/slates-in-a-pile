(ns slates-in-a-pile.styles
  (:require [garden.core :refer [css]]
            [garden.def :refer [defstyles]]
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

(defstyles base
  ((rule :body)
   {:margin 0
    :font-family "Helvetica Neue"})
  ((rule :h1)
   {:margin 0
    :font-size "80px"})
  ((rule :h5)
   {:margin 0
    :font-size "22px"})
  ((rule :#pile)
   {:position "absolute"
    :height "100%"
    :width "100%"})
  ((rule :.slate-container)
   {:display "table"
    :height "100%"
    :width "100%"})
  ((rule :.slate)
   {:display "table-cell"
    :vertical-align "middle"})
  ((rule :.slate-content)
   {:margin "auto"
    :width "1000px"
    :text-align "center"})
  ((rule :#slate-1)
   {:background-color (:soft-green colours)
    :color (:hard-green colours)})
  ((rule :#slate-2)
   {:background-color (:soft-crimson colours)
    :color (:hard-crimson colours)})
  ((rule :#slate-3)
   {:background-color (:soft-yellow colours)
    :color (:hard-yellow colours)}))

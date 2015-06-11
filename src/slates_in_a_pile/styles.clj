(ns slates-in-a-pile.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]))

(defstyles base
  (let [body (rule :body)]
    (body
      {:font-family "Helvetica Neue"
       :font-size "20px"
       :line-height 1.5
       :background-color "pink"})))

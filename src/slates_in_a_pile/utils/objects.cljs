(ns slates-in-a-pile.utils.objects
  (:require [om-tools.dom :as dom :include-macros true]))



(defn pointer
  []
  (dom/svg
    {:id "pointer"}
    (dom/line {:x1 20 :x2 48 :y1 7 :y2 35 :opacity 0.5})
    (dom/line {:x1 20 :x2 48 :y1 53 :y2 25 :opacity 0.5})))

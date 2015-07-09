(ns slates-in-a-pile.utils.objects
  (:require [om-tools.dom :as dom :include-macros true]))

(defn divider
  []
  (dom/svg
    {:class "divider"}
    (dom/line {:x1 0 :x2 465 :y1 2 :y2 2})))

(defn down-arrow
  []
  (dom/svg
    {:id "arrow"}
    (dom/line {:x1 12 :x2 12 :y1 0 :y2 16})
    (dom/polygon {:points "7,16 17,16 12,23"})))

(defn pointer
  []
  (dom/svg
    {:id "pointer"}
    (dom/line {:x1 20 :x2 48 :y1 7 :y2 35 :opacity 0.5})
    (dom/line {:x1 20 :x2 48 :y1 53 :y2 25 :opacity 0.5})))

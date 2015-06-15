(ns slates-in-a-pile.interactor
  (:require [om.core :as om]))

(defn say-direction [d] (println "Received arrow key press:" (name d)))

(defn handle-arrow-key-press
  [key-code state]
  (let [scroll-to (fn [h] (. js/window (scrollTo 0 h)))]
    (case key-code
      38 (say-direction :up)
      40 (say-direction :down)
      39 (om/transact! state [:slates :slate-1 :n] inc)
      37 (say-direction :left))))

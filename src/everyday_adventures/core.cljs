(ns everyday-adventures.core
  (:require [om.core :as om :include-macros true]
            [dommy.core :as dommy :refer-macros [sel1]]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom]))

(defonce state (atom {:slide-number 1}))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (dom/div
      {:id "slide-number"
       :on-click #(om/transact! state :slide-number inc)}
      (str "The sliding number is: " (:slide-number state)))))

(defn init []
  (om/root
    root-component
    state
    {:target (sel1 :#root-container)}))

(init)

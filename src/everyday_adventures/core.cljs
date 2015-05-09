(ns everyday-adventures.core
  (:require [om.core :as om :include-macros true]
            [dommy.core :as dommy :refer-macros [sel1]]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom]))

(defonce state (atom {:slate-number 1}))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (dom/div {:id "slates-container"}
      (dom/div
        {:class "slate-container"}
        (dom/div {:id "slate-1" :class "slate"}
          (dom/div {:class "slate-content"})))
      (dom/div
        {:class "slate-container"}
        (dom/div {:id "slate-2" :class "slate"}
          (dom/div {:class "slate-content"})))
      (dom/div
        {:class "slate-container"}
        (dom/div {:id "slate-3" :class "slate"}
          (dom/div {:class "slate-content"}))))))

(defn init []
  (om/root
    root-component
    state
    {:target (sel1 :#root-container)}))

(init)

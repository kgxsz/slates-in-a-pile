(ns everyday-adventures.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]
            [everyday-adventures.motioneer :refer [key-down-handler]]))

(defonce state (atom {:slate-number 1 :slates []}))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (div {:id "slates-container"}
      (div
        {:class "slate-container"}
        (div
          {:id "slate-1" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 1")
            (p "The first slate in the pile."))))
      (div
        {:class "slate-container"}
        (div
          {:id "slate-2" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 2")
            (p "The second slate in the pile."))))
      (div
        {:class "slate-container"}
        (div
          {:id "slate-3" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 3")
            (p "The third slate in the pile."))))
      (div
        {:class "slate-container"}
        (div
          {:id "slate-4" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 4")
            (p "The fourth slate in the pile."))))
      (div
        {:class "slate-container"}
        (div
          {:id "slate-5" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 5")
            (p "The fifth slate in the pile."))))
      (div
        {:class "slate-container"}
        (div
          {:id "slate-6" :class "slate"}
          (div
            {:class "slate-content"}
            (h1 "SLATE 6")
            (p "The sixth slate in the pile.")))))))

(defn init []
  (om/root
    root-component
    state
    {:target (dom/getElement "root-container")}))

(defonce key-down-handler-registration
  (events/listen js/window EventType/KEYDOWN #(key-down-handler %)))

(init)

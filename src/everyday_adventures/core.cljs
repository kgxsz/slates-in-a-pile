(ns everyday-adventures.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div h1 p svg]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]
            [everyday-adventures.motioneer :refer [key-down-handler]]
            [everyday-adventures.slates.slate-1 :refer [slate-1]]
            [everyday-adventures.slates.slate-2 :refer [slate-2]]
            [everyday-adventures.slates.slate-3 :refer [slate-3]]
            [everyday-adventures.slates.slate-4 :refer [slate-4]]
            [everyday-adventures.slates.slate-5 :refer [slate-5]]
            [everyday-adventures.slates.slate-6 :refer [slate-6]]))

; TODO what do I really need here
(defonce state (atom {:slates ["slate-1" "slate-2" "slate-3" "slate-4" "slate-5" "slate-6"]}))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (div {:id "slates-container"}
      (om/build slate-1 owner)
      (om/build slate-2 owner)
      (om/build slate-3 owner)
      (om/build slate-4 owner)
      (om/build slate-5 owner)
      (om/build slate-6 owner))))

(defn init []
  (om/root
    root-component
    state
    {:target (dom/getElement "root-container")}))

(defonce key-down-handler-registration
  (events/listen js/window EventType/KEYDOWN #(key-down-handler %)))

(init)

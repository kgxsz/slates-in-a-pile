(ns slates-in-a-pile.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.interactor :refer [setup-key-press-interaction]]
            [slates-in-a-pile.slates.slate-1 :refer [slate-1]]
            [slates-in-a-pile.slates.slate-2 :refer [slate-2]]
            [slates-in-a-pile.slates.slate-3 :refer [slate-3]]
            [slates-in-a-pile.slates.slate-4 :refer [slate-4]]
            [slates-in-a-pile.slates.slate-5 :refer [slate-5]]))

(enable-console-print!)

(defonce state
  (atom {:slates {:slate-1 {}
                  :slate-2 {}
                  :slate-3 {:n 30}
                  :slate-4 {}
                  :slate-5 {}}}))

(defcomponent pile
  [{:keys [slates] :as state} owner]
  (render-state [_ _]
    (println "Rendered root component with state:" state)
    (dom/div
      {:id "pile"}
      (om/build slate-1 (:slate-1 slates))
      (om/build slate-2 (:slate-2 slates))
      (om/build slate-3 (:slate-3 slates))
      (om/build slate-4 (:slate-4 slates))
      (om/build slate-5 (:slate-5 slates)))))

(defn setup-root-component
  [state]
  (om/root
    pile
    state
    {:target (sel1 :#application-container)}))

(defn main
  []
  (setup-key-press-interaction state)
  (setup-root-component state))

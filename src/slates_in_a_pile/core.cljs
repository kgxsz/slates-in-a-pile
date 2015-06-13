(ns slates-in-a-pile.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(enable-console-print!)
(.log js/console "Hello world!")

(defonce application-state
  (atom {:dummy-key "dummy-value"}))

(defcomponent slate-1
  [cursor owner]
  (render-state
    [_ _]
    (dom/div
      {:class "slate-container"}
      (dom/div
        {:id "slate-1" :class "slate"}
        (dom/section
          {:class "slate-content"}
          (dom/h1 "SLATE 1")
          (dom/h5 "The first slate in the pile."))))))

(defcomponent slate-2
  [cursor owner]
  (render-state
    [_ _]
    (dom/div
      {:class "slate-container"}
      (dom/div
        {:id "slate-2" :class "slate"}
        (dom/section
          {:class "slate-content"}
          (dom/h1 "SLATE 2")
          (dom/h5 "The second slate in the pile."))))))

(defcomponent pile
  [cursor owner]
  (render-state [_ _]
    (println "Rendered root component with state: " cursor)
    (dom/div
      {:id "pile"}
      (om/build slate-1 cursor)
      (om/build slate-2 cursor))))

(om/root
  pile
  application-state
  {:target (sel1 :#application-container)})

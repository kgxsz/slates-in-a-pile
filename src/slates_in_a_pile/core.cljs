(ns slates-in-a-pile.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(enable-console-print!)
(.log js/console "Hello world!")

(defonce application-state (atom {:a 1
                                  :b 2
                                  :c 3}))

(defcomponent root-component [cursor owner]
  (render-state [_ _]
    (println "Rendered root component with state: " cursor)
    (dom/div
      {:id "pile"}
      (dom/div
        {:class "slate-container"}
        (dom/div
          {:id "slate-1" :class "slate"}
          (dom/section
            {:class "slate-content"}
            (dom/h1 "A SLATE")
            (dom/h5 "one of many")))))))

(om/root
  root-component
  application-state
  {:target (sel1 :#application-container)})

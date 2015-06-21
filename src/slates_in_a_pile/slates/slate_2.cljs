(ns slates-in-a-pile.slates.slate-2
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljsjs.d3 :as d3]))

(defn initialize-canvas []
  (-> (.select js/d3 "#slate-2 .content")
      (.append "svg")
      (.attr "class" "canvas")
      (.attr "width" 700)
      (.attr "height" 300)))

(defcomponent slate-2
  [cursor owner]
  (did-mount [_]
    (initialize-canvas))
  (render-state
    [_ _]
    (println "Rendering slate-2 component with cursor:" cursor)
    (slate :slate-2
      (dom/h1 "SLATE 2"))))

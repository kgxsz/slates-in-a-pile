(ns slates-in-a-pile.slates.slate-5
  (:require-macros [slates-in-a-pile.utils.macros :refer [slate]])
  (:require [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [slates-in-a-pile.utils.objects :refer [pointer]]))

(defcomponent slate-5
  [state owner]
  (render-state
    [_ _]
    (println "Rendering slate-5 component with state:" state)
    (slate :slate-5
      (dom/div
        {:id "title"}
        (dom/h1 "WHERE")
        (dom/h1 "?"))
      (dom/div
        {:class "blurb"}
        (pointer)
        (dom/p "For the code itself, check out the repository on Github. You'll also find more information in the readme."))
      (dom/a
        {:href "https://github.com/kgxsz/slates-in-a-pile" :target "_blank"}
        "https://github.com/kgxsz/slates-in-a-pile"))))

(ns slates-in-a-pile.utils.macros
  (:require [om-tools.dom :as dom :include-macros true]))

(defmacro slate
  [id & body]
 `(dom/div
    {:id (name ~id) :class "slate"}
    (dom/div
      {:class "backdrop"}
      (dom/section
        {:class "content"}
        ~@body))))

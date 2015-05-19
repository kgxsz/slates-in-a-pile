(ns everyday-adventures.slates.slate-utils
  (:require [om.core :as om :include-macros true]))

(defn use-attribute [element attribute]
  (.attr element attribute (fn [data _] (aget data attribute))))

(defn create-element-join [parent-element element-name data]
    (let [join (-> parent-element (.selectAll element-name) (.data (clj->js data)))
          attributes (->> data first keys (mapv name))
          use-attributes (fn [element attributes] (reduce use-attribute element attributes))]
      (-> join .enter (.append element-name) (use-attributes attributes))))

(defn create-text-join [parent-element data]
    (let [join (-> parent-element (.selectAll "text") (.data (clj->js data)))
          use-text (fn [element] (.text element (fn [data _] (aget data "text"))))]
      (-> join
          .enter
          (.append "text")
          (use-attribute "dx")
          (use-attribute "dy")
          (use-text))))

(defn create-element-grouping
  [parent-element {:keys [class id x y opacity]}]
  (let [transform (str "translate(" x "," y ")")]
    (-> parent-element
        (.append "g")
        (.attr "class" class)
        (.attr "id" id)
        (.attr "transform" transform)
        (.attr "opacity" opacity))))

(ns slates-in-a-pile.styles.utils.helpers)

(defn nth-child [& styles]
  (->> (partition-by string? styles)
       (partition-all 2)
       (map (fn [[h t]]
              (let [[n & styles] (concat h t)]
                [(format "&:nth-child(%s)" n)
                 styles])))))

(defn slate-title
  [colour]
  [:.title {:float "left"
                  :width "300px"}
   [:h1 {:color colour}
    (nth-child
      "1" {:font-size "4em"
           :line-height "0.7em"}
      "2" {:font-size "24em"
           :line-height "0.8em"})]])

(defn pointer
  [colour]
  [:#pointer {:float "left"
              :width "80px"
              :height "58px"}
   [:line {:stroke colour
           :stroke-width "15"}]])

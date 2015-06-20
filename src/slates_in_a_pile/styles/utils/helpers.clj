(ns slates-in-a-pile.styles.utils.helpers)

(defn nth-child [& styles]
  (->>
    (partition-by string? styles)
    (partition-all 2)
    (map (fn [[h t]]
           (let [[n & styles] (concat h t)]
             [(format "&:nth-child(%s)" n)
              styles])))))

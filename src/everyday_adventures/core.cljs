(ns everyday-adventures.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :refer [div]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]
            [cljs.core.async :refer [put! chan <!]]
            [everyday-adventures.slates.slate-1 :refer [slate-1]]
            [everyday-adventures.slates.slate-2 :refer [slate-2]]
            [everyday-adventures.slates.slate-3 :refer [slate-3]]
            [everyday-adventures.slates.slate-4 :refer [slate-4]]
            [everyday-adventures.slates.slate-5 :refer [slate-5]]
            [everyday-adventures.slates.slate-6 :refer [slate-6]]
            [everyday-adventures.slates.slate-7 :refer [slate-7]]
            [everyday-adventures.slates.slate-8 :refer [slate-8]]))

(defonce state (atom {:slate-1 {}
                      :slate-2 {}
                      :slate-3 {:step 0}
                      :slate-4 {:step 0}
                      :slate-5 {:step 0}
                      :slate-6 {:step 0}
                      :slate-7 {:step 0}
                      :slate-8 {:step 0}}))

(defn change-current-slate-step [current-slate-n f]
  (let [current-slate (keyword (str "slate-" (inc current-slate-n)))
        cursor (om/ref-cursor (current-slate (om/root-cursor state)))]
    (om/transact! cursor :step #(let [r (f %)] (if (neg? r) % r)))))

(defn arrow-key-handler [direction]
  (let [slate-height (.. (dom/getElementByClass "slate") -offsetHeight)
        window-offset (.. (dom/getDocumentScroll) -y)
        window-slate-aligned (zero? (rem window-offset slate-height))
        current-slate-n (int (/ window-offset slate-height))
        scroll-to-slate (fn [n] (. js/window (scrollTo 0 (* n slate-height))))]
    (case direction
      :up (if window-slate-aligned
            (scroll-to-slate (dec current-slate-n))
            (scroll-to-slate current-slate-n))
      :down (scroll-to-slate (inc current-slate-n))
      :right (change-current-slate-step current-slate-n inc)
      :left (change-current-slate-step current-slate-n dec))))

(defn key-down-handler [event]
  (case (.-keyCode event)
    38 (do (.preventDefault event) (arrow-key-handler :up))
    40 (do (.preventDefault event) (arrow-key-handler :down))
    39 (do (.preventDefault event) (arrow-key-handler :right))
    37 (do (.preventDefault event) (arrow-key-handler :left))
    "default"))

(defonce key-down-chan
  (let [key-down-chan (chan)]
    (.log js/console "registering key down events to a channel")
    (events/listen js/window EventType/KEYDOWN #(put! key-down-chan %))
    (go (while true (key-down-handler (<! key-down-chan))))
    key-down-chan))

(defcomponent root-component [state owner]
  (render-state [_ _]
    (div {:id "slates-container"}
      (om/build slate-1 (:slate-1 state))
      (om/build slate-2 (:slate-2 state))
      (om/build slate-3 (:slate-3 state))
      (om/build slate-4 (:slate-4 state))
      (om/build slate-5 (:slate-5 state))
      (om/build slate-6 (:slate-6 state))
      (om/build slate-7 (:slate-7 state))
      (om/build slate-8 (:slate-8 state)))))

(defn init []
  (om/root
    root-component
    state
    {:target (dom/getElement "root-container")}))

(init)

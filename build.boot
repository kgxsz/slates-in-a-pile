(set-env!
  :source-paths   #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[org.clojure/clojure "1.6.0"]
                  [org.clojure/clojurescript "0.0-3308"]
                  [adzerk/boot-cljs "0.0-3269-2"]
                  [adzerk/boot-cljs-repl "0.1.9"]
                  [adzerk/boot-reload "0.3.0"]
                  [pandeiro/boot-http "0.6.2"]
                  [org.martinklepsch/boot-garden "1.2.5-4"]
                  [org.omcljs/om "0.8.8"]
                  [prismatic/om-tools "0.3.10" :exclude [prismatic/schema
                                                         prismatic/plumbing]]
                  [prismatic/schema "0.4.2"]
                  [prismatic/plumbing "0.4.3"]
                  [prismatic/dommy "1.0.0"]
                  [garden "1.2.5"]])

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[pandeiro.boot-http :as http]
  '[org.martinklepsch.boot-garden :refer [garden]])

(deftask dev []
  (set-env! :source-paths #{"src"})
  (comp (http/serve
          :dir "target/"
          :httpkit true)
        (watch)
        (speak)
        (reload)
        (cljs
          :optimizations :none
          :compiler-options {:output-to "scripts/main.js"})
        (garden
          :styles-var 'slates-in-a-pile.styles.main/base
          :output-to "styles/main.css")))

(deftask build []
  (set-env! :source-paths #{"src"})
  (comp (cljs
          :optimizations :advanced
          :compiler-options {:output-to "scripts/main.js"})
        (garden
          :styles-var 'slates-in-a-pile.styles.main/base
          :output-to "styles/main.css")))

(deftask serve []
  (set-env! :source-paths #{"src"})
  (comp (http/serve :dir "target/" :httpkit true)
        (watch)))

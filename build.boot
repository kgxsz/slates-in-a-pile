(set-env!
  :source-paths   #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[org.clojure/clojurescript "0.0-3308"]
                  [adzerk/boot-cljs "0.0-3269-2"]
                  [adzerk/boot-cljs-repl "0.1.9"]
                  [adzerk/boot-reload "0.2.6"]
                  [pandeiro/boot-http "0.6.2"]])

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[pandeiro.boot-http :refer [serve]])

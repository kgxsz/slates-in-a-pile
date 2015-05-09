(defproject everyday-adventures "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [org.omcljs/om "0.8.8"]
                 [prismatic/dommy "1.1.0"]
                 [prismatic/om-tools "0.3.11" :exclude [prismatic/schema
                                                        prismatic/plumbing]]
                 [prismatic/schema "0.4.2"]
                 [prismatic/plumbing "0.4.3"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.1"]]

  :clean-targets ^{:protect false} [:target-path
                                    "resources/public/out/"
                                    "resources/public/main.js"
                                    "resources/public/style.css"
                                    "resources/public/style.css.map"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:main everyday-adventures.core
                                   :asset-path "out"
                                   :output-to "resources/public/main.js"
                                   :output-dir "resources/public/out"}
                        :figwheel {:on-jsload "everyday-adventures.core/init"}}]})

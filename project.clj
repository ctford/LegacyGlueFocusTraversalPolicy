(defproject legacygluefocustraversalpolicy "0.1.0-SNAPSHOT"
  :description "FIXME: A RESTful maze."
  :url "https://github.com/ctford/LegacyGlueFocusTraversalPolicy"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler legacygluefocustraversalpolicy.handler/app}
  :profiles
  {:dev {:plugins [[lein-midje "3.0.0"]]
         :dependencies [[ring-mock "0.1.5"]
                        [midje "1.5.1"]]}})

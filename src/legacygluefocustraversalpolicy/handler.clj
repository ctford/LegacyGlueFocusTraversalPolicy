(ns legacygluefocustraversalpolicy.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [legacygluefocustraversalpolicy.maze :as maze]))

(defroutes app-routes
  (GET "/" [] "Enter...")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

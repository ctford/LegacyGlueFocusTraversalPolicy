(ns legacygluefocustraversalpolicy.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [legacygluefocustraversalpolicy.maze :as maze]
            [ring.middleware.json :as json]))

(defroutes app-routes
  (GET "/" []
       (-> maze/default maze/ways))
  (GET "/:id" [id]
       (if-let [node (-> maze/default (maze/lookup ,id))]
         (maze/ways node)
         (route/not-found "Not Found")))
  (route/not-found
    "Not Found"))

(def app
  (json/wrap-json-response (handler/site app-routes)))

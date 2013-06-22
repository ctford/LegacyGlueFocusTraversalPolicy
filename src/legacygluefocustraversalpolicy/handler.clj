(ns legacygluefocustraversalpolicy.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [legacygluefocustraversalpolicy.maze :as maze]
            [ring.middleware.json :as json]))

(defn to-url [n] (str "http://localhost/" n))

(defroutes app-routes
  (GET "/" []
       (->> maze/default maze/ways (map to-url)))
  (GET "/:id" [id]
       (if-let [node (-> maze/default (maze/lookup id))]
         (->> node maze/ways (map to-url))
         (route/not-found "Not Found")))
  (route/not-found
    "Not Found"))

(def app
  (-> app-routes handler/site json/wrap-json-response))

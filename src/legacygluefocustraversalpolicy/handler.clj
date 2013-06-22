(ns legacygluefocustraversalpolicy.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [legacygluefocustraversalpolicy.maze :as maze]
            [ring.middleware.json :as json]))

(defn to-url [host port n] (str "http://" host ":" port "/" n))

(defroutes app-routes
  (GET "/" [:as {uri :uri host :server-name port :server-port}]
       (->> maze/default maze/ways (map (partial to-url host port))))
  (GET "/:id" [id :as {uri :uri host :server-name port :server-port}]
       (if-let [node (-> maze/default (maze/lookup id))]
         (->> node maze/ways (map (partial to-url host port)))
         (route/not-found "Not Found")))
  (route/not-found
    "Not Found"))

(def app
  (-> app-routes handler/site json/wrap-json-response))

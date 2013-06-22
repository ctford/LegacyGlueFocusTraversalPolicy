(ns legacygluefocustraversalpolicy.test.handler
  (:use midje.sweet 
        ring.mock.request  
        legacygluefocustraversalpolicy.handler)
  (:require [legacygluefocustraversalpolicy.maze :as maze]))

(fact "Webroot gives you some ways forward."
  (:body (app (request :get "/")))
      => (contains (str "\"http://localhost:80/" (nth maze/ids 1)))
  (app (request :get "/"))
      => (contains {:status 200}))

(fact "A specific node gives you some ways forward."
  (:body (app (request :get (str "/" (nth maze/ids 0)))))
      => (contains (str "\"http://localhost:80/" (nth maze/ids 1)))
  (app (request :get (str "/" (nth maze/ids 0)))) => (contains {:status 200}))

(fact "Missing route 404s."
  (app (request :get "/invalid/what")) => (contains {:status 404})
  (app (request :get "/42")) => (contains {:status 404}))

(fact "Victory is shown."
  (app (request :get (str "/" maze/goal))) => (contains {:status 200})
  (app (request :get (str "/" maze/goal))) => (contains {:body "Finished!"}))

(fact "Solution is available."
  (app (request :get "/solution")) => (contains {:status 200})
  (:body (app (request :get "/solution" ))) => (contains (str "http://localhost:80/" maze/goal)))

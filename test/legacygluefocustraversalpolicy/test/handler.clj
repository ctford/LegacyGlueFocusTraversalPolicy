(ns legacygluefocustraversalpolicy.test.handler
  (:use midje.sweet 
        ring.mock.request  
        legacygluefocustraversalpolicy.handler))

(fact "Webroot gives you some ways forward."
  (app (request :get "/"))
      => (contains {:body "[\"http://localhost:80/2\",\"http://localhost:80/3\"]"})
  (app (request :get "/"))
      => (contains {:status 200}))

(fact "A specific node gives you some ways forward."
  (app (request :get "/2"))
      => (contains {:body "[\"http://localhost:80/1\"]"})
  (app (request :get "/2")) => (contains {:status 200}))

(fact "Missing route 404s."
  (app (request :get "/invalid/what")) => (contains {:status 404})
  (app (request :get "/42")) => (contains {:status 404}))

(fact "Victory is shown."
  (app (request :get "/3")) => (contains {:status 200})
  (app (request :get "/3")) => (contains {:body "Finished!"}))

(ns legacygluefocustraversalpolicy.test.handler
  (:use midje.sweet 
        ring.mock.request  
        legacygluefocustraversalpolicy.handler))

(fact "Webroot asks that you enter..."
  (app (request :get "/")) => (contains {:body "Enter..."})
  (app (request :get "/")) => (contains {:status 200}))

(fact "Missing route 404s."
  (app (request :get "/invalid")) => (contains {:status 404}))

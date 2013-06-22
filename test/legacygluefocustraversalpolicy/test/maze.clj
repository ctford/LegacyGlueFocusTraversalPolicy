(ns legacygluefocustraversalpolicy.test.maze
  (:use midje.sweet 
        legacygluefocustraversalpolicy.maze)
  (:require [clojure.zip :as zip]))

(def example 
  (maze-zip {:value 1 :children
             [{:value 2 :children []}
              {:value 3 :children []}]}))

(fact "ways gets all the adjacent values."
  (-> example ways) => [2 3]
  (-> example zip/down ways) => [1])

(fact "lookup finds nodes based on their value"
  (-> example (lookup 2) ways) => [1]
  (-> example (lookup 1) ways) => [2 3])

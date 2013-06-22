(ns legacygluefocustraversalpolicy.test.maze
  (:use midje.sweet 
        legacygluefocustraversalpolicy.maze)
  (:require [clojure.zip :as zip]))

(fact "ways gets all the adjacent values."
  (-> {:value 3 :children [{:value 4 :children []} {:value 44 :children []}]}
      maze-zip
      ways)
      => [4 44]
  (-> {:value 3 :children [{:value 4 :children []} {:value 44 :children []}]}
      maze-zip
      zip/down
      ways)
      => [3])

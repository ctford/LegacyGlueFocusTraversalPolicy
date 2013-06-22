(ns legacygluefocustraversalpolicy.test.maze
  (:use midje.sweet 
        legacygluefocustraversalpolicy.maze))

(fact "ways gets all the adjacent values."
  (-> {:value 3 :children [{:value 4 :children []} {:value 44 :children []}]}
      maze-zip
      ways)
  => [4 44])

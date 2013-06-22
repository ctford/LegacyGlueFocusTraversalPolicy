(ns legacygluefocustraversalpolicy.maze
  (:require [clojure.zip :as zip]))

(def random
  (let [generator (java.util.Random. 42)]
    (fn [] (-> generator (.nextInt Integer/MAX_VALUE)))))

(def maze-zip
  (partial zip/zipper (constantly true) :children #(assoc %1 :children %2))) 

(def default 
  (maze-zip {:value 1 :children
             [{:value 2 :children []}
              {:value 3 :children []}]}))

(def goal 3)

(defn with-parent [node nodes]
  (if-let [parent (zip/up node)]
    (conj nodes (zip/node parent))
    nodes))

(defn ways [submaze]
  (->> submaze zip/children (with-parent submaze) (map :value)))

(defn lookup [submaze value]
  (cond
    (= value (-> submaze zip/node :value str)) submaze
    (zip/end? submaze) nil
    :otherwise (-> submaze zip/next (lookup value))))

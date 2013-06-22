(ns legacygluefocustraversalpolicy.maze
  (:require [clojure.zip :as zip]))

(def random
  (let [generator (java.util.Random. 42)]
    (fn [] (-> generator (.nextInt Integer/MAX_VALUE)))))

(def maze-zip
  (partial zip/zipper (constantly true) :children #(assoc %1 :children %2))) 

(defn with-parent [node nodes]
  (if-let [parent (zip/up node)]
    (conj nodes (zip/node parent))
    nodes))

(defn ways [submaze]
  (->> submaze zip/children (with-parent submaze) (map :value)))

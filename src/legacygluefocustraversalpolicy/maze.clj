(ns legacygluefocustraversalpolicy.maze
  (:require [clojure.zip :as zip]))

(def psuedo-random
  (let [generator (java.util.Random. 42)]
    (fn [limit] (-> generator (.nextInt limit)))))

(def maze-zip
  (partial zip/zipper (constantly true) :children #(assoc %1 :children %2))) 

(def ids
  (repeatedly 1000 (fn [] (psuedo-random Integer/MAX_VALUE))))

(defn generate [random [n & others]]
  (cond others 
    (let [remaining (count others)
          left (random remaining)]
      {:value n :children
       (->> [(generate random (take left others))
             (generate random (drop left others))]
            (filter (comp not nil?))
            (sort-by :value))})
    n {:value n :children []}
    :otherwise nil))

(def default (maze-zip (generate psuedo-random ids)))
(def goal (last ids))

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

(defn ancestry [submaze]
  (let [parent (zip/up submaze)
        value (-> submaze zip/node :value)]
    (if parent
      (cons value (ancestry parent)) 
      [value])))

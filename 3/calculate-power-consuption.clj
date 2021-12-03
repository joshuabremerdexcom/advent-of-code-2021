(use 'clojure.java.io)
(require '[clojure.zip :as zip])

(defn parse-binary [s] (Integer/parseInt s 2))

(defn zip [& colls]
  (partition (count colls) (apply interleave colls)))

(defn most-frequent [items]
  (->> items
    frequencies
    (sort-by val)
    reverse
    (take 1)
    (map first)))

(defn least-frequent [items]
  (->> items
    frequencies
    (sort-by val)
    (take 1)
    (map first)))

(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))


;; This confused me so I'm ditching it

;; (defn process-file-by-lines
;;   "Process file reading it line-by-line"
;;   ([file]
;;    (process-file-by-lines file identity))
;;   ([file process-fn]
;;    (process-file-by-lines file process-fn println))
;;   ([file process-fn output-fn]
;;    (with-open [rdr (clojure.java.io/reader file)]
;;      (doseq [line (line-seq rdr)]
;;        (output-fn
;;         (process-fn line))))))

;; (defn read-file-by-lines
;;   "Read file reading it line-by-line"
;;   ([file]
;;    (with-open [rdr (clojure.java.io/reader file)]
;;      (map seq (map (line-seq rdr)))
;;    )
;;   )
;; )


;;; Original Lists
(println (map seq (get-lines "./3/input.txt")))

;;; Zipped lists
(println
 (apply map vector (map seq (get-lines "./3/input.txt")))
)

;;; Frequencies
(println
(map most-frequent (apply map vector (map seq (get-lines "./3/input.txt")))
)
)

;;; Frequencies
(println
(apply map str (map most-frequent (apply map vector (map seq (get-lines "./3/input.txt"))))
)
)

;;; Gamma
(println
(map parse-binary (apply map str (map most-frequent (apply map vector (map seq (get-lines "./3/input.txt"))))
))
)

;;; Epsilon
(println
(map parse-binary (apply map str (map least-frequent (apply map vector (map seq (get-lines "./3/input.txt"))))
))
)


;; I was too excited and just multiplied these values myself.

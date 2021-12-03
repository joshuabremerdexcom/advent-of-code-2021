(use 'clojure.java.io)
(require '[clojure.zip :as zip])

(defn zip [& colls]
  (partition (count colls) (apply interleave colls)))
(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))

(defn process-file-by-lines
  "Process file reading it line-by-line"
  ([file]
   (process-file-by-lines file identity))
  ([file process-fn]
   (process-file-by-lines file process-fn println))
  ([file process-fn output-fn]
   (with-open [rdr (clojure.java.io/reader file)]
     (doseq [line (line-seq rdr)]
       (output-fn
        (process-fn line))))))

(defn read-file-by-lines
  "Read file reading it line-by-line"
  ([file]
   (with-open [rdr (clojure.java.io/reader file)]
     (map seq (map (line-seq rdr)))
   )
  )
)


;;; Original Lists
(println (map seq (get-lines "./3/simple_input.txt")))

;;; Zipped lists
(println
 (apply map vector (map seq (get-lines "./3/simple_input.txt")))
)

;;; Frequencies
(println
(map frequencies (apply map vector (map seq (get-lines "./3/simple_input.txt")))
)
)


;; Will just print file line by ine
;; (process-file-by-lines "./3/input.txt"
;;                        reverse) ;; Will print each line reversed



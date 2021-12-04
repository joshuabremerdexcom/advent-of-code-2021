(use 'clojure.java.io)
;; (require '[clojure.zip :as zip])


(defn parse-binary [s] (Integer/parseInt s 2))

(defn zip [& colls]
  (partition (count colls) (apply interleave colls)))

(defn most-frequent [items]
  (->> (map str (conj items 1))
    frequencies
    (sort-by val)
    reverse
    (take 1)
    (map first)))

(defn least-frequent [items]
  (->> (map str (conj items 1))
    frequencies
    (sort-by val)
    (take 1)
    (map first)))

(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))
(println "test")

(println ( seq [1 1 1 1 1 1 0]))

(println ( least-frequent (seq [1 0 1 0 1 0])))
(println ( least-frequent (seq [1 1 0])))
(println ( most-frequent (seq [1 0 1 0 1 0])))
(println ( most-frequent (seq [1 1 0])))

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


(def all-lines (get-lines "./3/input.txt"))

(def list-of-bits (apply map vector (map seq all-lines))
)

;;; Original Lists
(println (map seq all-lines))

;;; Zipped lists
(println
 list-of-bits
)

;;; Frequencies
(println
(map most-frequent list-of-bits
)
)

;;; Frequencies
(println
(apply map str (map most-frequent list-of-bits)
)
)

;;; Most Common
(println "Most Common")

(def most-common (apply str (apply map str (map most-frequent list-of-bits))))

(println most-common)

;;; Epsilon
(println "Least Common")
(def least-common (apply str (apply map str (map least-frequent list-of-bits))))

;; (def least-common "010001011010")

(println least-common)


;;;DEBUG

;; (println list-of-bits)
;; (println (map
;;   #(->> (map str(conj % 1))
;;     frequencies
;;     (sort-by val)
;;     ;; (take 1)
;;     ;; (map first)
;;     )

;;     list-of-bits))

;;;DEBUG



;; I was too excited and just multiplied these values myself.

(defn keep-if-starts-with [items, string]
  (keep #(if (clojure.string/starts-with?  % string) %) items )
  )


;; (println (map first all-lines))

;; (defn inspect-seq [seq num]
;;   (do
;; (println (str "Item:" num))
;; (println seq)
;;   ))

(println "Looking for matches")

;; (def oxygen (flatten
;;         (keep #(if (= (count %) 1) %) (
;;             map #(keep-if-starts-with all-lines
;;               (subs most-common 0 %))
;;                 (range 3)))))

;; (println oxygen)


(def one-to-twelve (take (count(first all-lines)) (drop 1 (range))))


(println one-to-twelve)
;; (println (take 4 most-common ) )
;; (println (seq [1]) )

;; (println "this is hard")
;; (println (concat (take 4 most-common) (seq [1]) (take-last  7 most-common)))
;; (def most-common-with-patch (apply str (concat (take 4 most-common) (seq [1]) (take-last 7 most-common))))

;; (println most-common-with-patch)


(println "Oxygen row")
(def oxygen-match (apply str
      (flatten
        (first
        (keep #(if (= (count %) 1) %)
          (map
            #(keep-if-starts-with all-lines
              (subs most-common 0 %)
            )
            one-to-twelve
          )
        )
      )
    )))

(println most-common)
(println oxygen-match)
(println
  (parse-binary
    oxygen-match
  )
)


;; (println (take 4 least-common ) )
;; (println (seq [1]) )

;; (println "this is hard")
;; (println (concat (take 4 least-common) (seq [1]) (take-last  7 least-common)))
;; (def least-common-with-patch (apply str (concat (take 4 least-common) (seq [1]) (take-last 7 least-common))))

;; (println least-common-with-patch)


(println "Carbon row")

(def carbon-match
  (apply str
      (flatten
        (first
        (keep #(if (= (count %) 1) %)
          (map
            #(keep-if-starts-with all-lines
              (subs least-common 0 %)
            )
            one-to-twelve
          )
        )
      )
      )
    )
  )

(println least-common)
(println "blah")
(println carbon-match)
(println
  (parse-binary
    carbon-match
  )
)

;; (println (keep-if-starts-with all-lines (subs (first all-lines) 0 6)))

;; 3336858

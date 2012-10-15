(ns clip-test.testutil-cljs
  (:require [clojure.string     :as str]
            [cljs.reader        :as reader]
            [clip-test.internal :as internal]))


(def Throwable                nil)
(def Error                    nil)
(def AssertionError           nil)
(def Exception                nil)
(def RuntimeException         nil)
(def IllegalArgumentException nil)
(def IllegalStateException    nil)
(def IllegalErrorException    nil)


(def read-str reader/read-string)


(defn re-quote
  [x] {:pre [(string? x)]}
  x)


(defn throw-msg
  [x] {:pre [(string? x)]}
  (throw (js/Error x)))


(defn try-catch
  [try-f catch-f]
  (try (try-f)
    (catch js/Error err
      (catch-f err))))


(defn error-msg
  [err]
  (.-message err))


(defn millis-now
  []
  (.getTime (js/Date.)))


(defn sleep
  [millis]
  (let [start (millis-now)]
    (while (< (- (millis-now) start) millis))))


(defn init!
  []
  (set! internal/try-catch try-catch)
  (set! internal/error-msg error-msg)
  (set! *print-fn* (fn [& args] (.log js/console (str/join " " args)))))


(init!)


(defn print-test-summary
  []
  (print (format "%d tests, %d assertions"
                 @internal/test-count @internal/assert-count))
  (print (format "%d failures, %d errors"
                 @internal/fail-count @internal/error-count)))

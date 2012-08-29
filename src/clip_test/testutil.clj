(ns clip-test.testutil
  (:require [clip-test.internal :as internal])
  (:import [java.util.regex Pattern]))


(def read-str read-string)


(defn re-quote
  [x] {:pre [(string? x)]}
  (re-pattern (Pattern/quote x)))


(defn throw-msg
  [x] {:pre [(string? x)]}
  (throw (RuntimeException. ^String x)))


(defn try-catch
  [try-f catch-f]
  (try (try-f)
    (catch Throwable t
      (catch-f t))))


(defn error-msg
  [err] {:pre [(instance? Throwable err)]}
  (.getMessage ^Throwable err))


(defn init!
  []
  (alter-var-root #'internal/try-catch (constantly try-catch))
  (alter-var-root #'internal/error-msg (constantly error-msg)))


(init!)
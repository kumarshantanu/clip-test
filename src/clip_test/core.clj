(ns clip-test.core
  (:require [clojure.test :as test]))


(defmacro deftest
  "Alias for `deftest`"
  [testname & body]
  `(test/deftest ~testname ~@body))


(defmacro testing
  "Alias for `testing`"
  [caption & body]
  `(test/testing ~caption ~@body))


(defmacro is
  "Alias for `is`"
  [& body]
  `(test/is ~@body))
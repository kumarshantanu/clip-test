(ns clip-test.run-tests
  (:require [clip-test.core-test     :as core-test]
            [clip-test.testutil-cljs :as tu]))


(defn ^:export run
  []
  (core-test/test-ns-hook)
  (tu/print-test-summary))
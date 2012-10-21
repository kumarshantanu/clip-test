(ns clip-test.jvm-test
  (:use [clip-test.testutil
         :only [read-str re-quote throw-msg try-catch error-msg]])
  (:use [clip-test.core
         :only [deftest testing is]]))


(deftest test-success
  (testing "single, success test"
    (is true "success")))


(deftest test-failure
  (testing "single, failure test"
    (is false "failure")))


(deftest test-error
  (testing "error test"
    (is (thrown? RuntimeException
           (throw-msg "foo")) "failure")
    (is (thrown-with-msg? RuntimeException #"foo"
           (throw-msg "foo")) "failure with message")))


(defn test-ns-hook
  []
  (test-success)
  (test-failure)
  (test-error))
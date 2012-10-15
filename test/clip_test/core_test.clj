(ns clip-test.core-test
  (:use [clip-test.testutil;*CLJSBUILD-REMOVE*;-cljs
         :only [;*CLJSBUILD-REMOVE*;RuntimeException
                re-quote throw-msg millis-now sleep]])
  (:use;*CLJSBUILD-REMOVE*;-macros
    [clip-test.core;*CLJSBUILD-REMOVE*;-cljs
     :only [deftest testing is
            ;*CLJSBUILD-REMOVE*;thrown? thrown-with-msg?
            ]]))


(deftest test-success
  (is true)
  (is true "without 'testing' wrapper")
  (testing "is truthy"
    (is true)
    (is true "true"))
  (testing "is ="
    (is (= 10 10))
    (is (= 10 10) "= 10 10"))
  (testing "is thrown?"
    (is (thrown? Throwable
                 (assert (not "Sample message"))))
    (is (thrown? Throwable
                 (assert (not "Sample message"))) "thrown? Throwable"))
  (testing "is thrown-with-msg? (re-quote)"
    (is (thrown-with-msg? RuntimeException (re-quote "Sample message")
                          (throw-msg "Sample message")))
    (is (thrown-with-msg? RuntimeException (re-quote "Sample message")
                          (throw-msg "Sample message"))
        "thrown-with-msg? (re-quote)"))
  (testing "is thrown-with-msg? (regex)"
    (is (thrown-with-msg? RuntimeException #"Sample.*"
                          (throw-msg "Sample message")))
    (is (thrown-with-msg? RuntimeException #"Sample.*"
                          (throw-msg "Sample message"))
        "thrown-with-msg? (regex)")))


(deftest test-fail
  (is false)
  (is false "without 'testing' wrapper")
  (testing "is falsy"
    (is false)
    (is false "false"))
  (testing "is inequal"
    (is (= 2 3))
    (is (= 2 3) "= 2 3")))


(deftest test-error
  (testing "is error"
    (is (throw-msg "Raising error"))
    (is (throw-msg "Raising error") "throw-msg")))


(deftest test-testutil
  (testing "millis-now"
    (is (pos? (millis-now)) "millis-now"))
  (testing "sleep"
    (let [start (millis-now)
        _     (sleep 100)
        stop  (millis-now)]
    (is (>= (- stop start) 100) "sleep"))))


(defn test-ns-hook
  []
  (test-success)
  (test-fail)
  (test-error)
  (test-testutil))
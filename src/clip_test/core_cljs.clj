;*CLJSBUILD-MACRO-FILE*;

(ns clip-test.core-cljs
  (:use [clip-test.internal :only [try-catch error-msg
                                   *test-name* *testing* *assertion*
                                   test-count assert-count ++ fail error]]))


(defmacro deftest
  [the-name & body]
  `(defn ~the-name
     []
     (binding [*test-name* ~(str the-name)]
       (++ test-count)
       (print (str "[Running test] " ~(str the-name)))
       ~@body)))


(defmacro testing
  [msg & body]
  `(binding [*testing* ~msg]
     (print (str "  -> Testing '" ~msg "'"))
     ~@body
     (print (str "  `~~~~~~~~~"))))


(defmacro thrown?
  "set! `try-catch` before calling this, so that the effect is same as:
  `(try ~@body
     (catch ~'js/Error err#
       true))"
  [ex-class & body]
  `(try-catch #(do ~@body false)
              (constantly true)))


(defmacro thrown-with-msg?
  "set! `try-catch` before calling this, so that the effect is same as:
  `(try ~@body
     (catch ~'js/Error err#
       ...))"
  [ex-class msg-regex & body]
  `(try-catch #(do ~@body false)
              (fn [err#]
                (let [r# (if (string? ~msg-regex)
                           (= ~msg-regex (error-msg err#))
                           (boolean (re-find ~msg-regex (error-msg err#))))]
                  (when-not r#
                    (fail *assertion* ~msg-regex err#))
                  true))))


(defmacro is
  ([form] `(is ~form nil))
  ([form msg]
    `(do (++ assert-count)
       (binding [*assertion* ~msg]
         (try-catch #(let [value# ~form]
                       (when-not value#
                         (fail ~msg '~form value#)))
                    (fn [err#]
                      (error ~msg '~form err#)))))))

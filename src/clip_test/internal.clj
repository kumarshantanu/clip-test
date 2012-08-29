(ns clip-test.internal
  "Internal stuff for use by the namespace clip-test.core-cljs. Some of these
  must be re-initialized in a platform-specific way before they can be used.")


(defn try-catch
  "Handler fn that takes 2 args `try-f` (argless fn) and `catch-f` (fn that
  takes the exception object as only arg).
  NOTE: This function should be re-initialized in a platform-specific way."
  [try-f catch-f]
  (assert (not "set! `try-catch` as per documentation")))


(defn error-msg
  "Return the error message from the given platform-specific error object.
  NOTE: This function should be re-initialized in a platform-specific way."
  [err]
  (assert (not "set! `error-message` as per documentation")))


;; ----- BEGIN: CLJS specific stuff -----


(def ^:dynamic *test-name* nil)
(def ^:dynamic *testing*   nil)
(def ^:dynamic *assertion* nil)

(def test-count   (atom 0))
(def assert-count (atom 0))
(def fail-count   (atom 0))
(def error-count  (atom 0))


(defn ++
  [atom]
  (swap! atom inc))


(defn print-msg
  [msg status expected actual]
  (print (str "        " status (when msg (str " in {" msg "}"))))
  (print (str "        expected: " (pr-str expected) "\n"
              "          actual: " (pr-str actual))))


(defn fail
  [msg expected actual]
  (++ fail-count)
  (print-msg msg "FAIL" expected actual))


(defn error
  [msg expected err]
  (++ error-count)
  (print-msg msg "ERROR" expected err))


;; ----- END: CLJS specific stuff -----
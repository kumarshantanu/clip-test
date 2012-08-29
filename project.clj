(defproject clip-test "0.1.0-SNAPSHOT"
  :description "Subset of `clojure.test` for Clojure and ClojureScript"
  :url "https://github.com/kumarshantanu/clip-test"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;; :dependencies [[org.clojure/clojure "1.4.0"]]
  :warn-on-reflection true
  :plugins [[lein-cljsbuild "0.2.6"]]
  :profiles {:jst {:source-paths ["src" "test"]
                   ;; Enable the lein hooks for: clean, compile, test, and jar
                   ;; :hooks [leiningen.cljsbuild]
                   :cljsbuild {:crossovers [clip-test.core-test
                                            clip-test.internal]
                               ;; Command for running the unit tests in CLJS
                               ;;     $ lein cljsbuild test
                               :test-commands {"unit" ["phantomjs"
                                                       "run-tests.js"]}
                               :builds {:test {:source-path "test-cljs"
                                               :compiler
                                               {:output-to "target/clip-test.js"
                                                ;; :optimizations nil
                                                :pretty-print true}}}}}
             :1.2 {:dependencies [[org.clojure/clojure "1.2.1"]]}
             :1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.0-alpha4"]]}}
  :aliases {"all" ["with-profile" "1.2:1.3:1.4:1.5"]
            "dev" ["with-profile" "1.4,jst"]})

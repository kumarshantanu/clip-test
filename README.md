# clip-test

This library is a subset of `clojure.test` for Clojure and ClojureScript. It
lets you write tests (albeit with constraints) that can run on both Clojure and
ClojureScript.

You can also use this library to write JVM-specific and CLJS-specific tests.


## Usage

For ClojureScript testing this library should be used in conjunction with
[lein-cljsbuild](https://github.com/emezeske/lein-cljsbuild).

On Clojars: On Clojars: https://clojars.org/clip-test

Leiningen dependency: `[clip-test "0.2.0"]`

Supported Clojure versions: 1.2, 1.3, 1.4, 1.5

Tested with lein-cljsbuild version: 0.2.8

### Notes:

* Only `deftest`, `testing` and `is` macros are supported.
* `thrown?` and `thrown-with-msg?` are implemented as macros; unlike
  `clojure.test`, there is no multi-method to extend the dispatch on.
* You must implement the `test-ns-hook` function in the test namespace and call
  it externally to run the tests in CLJS.
* Write the tests in the same style as in `test/clip_test/core_test.clj`.
* Invoke the tests in CLJS in a way similar as in `run-tests.js`/`run-tests.html`
  and `test-cljs/clip_test/run_tests.cljs`.
* You need to have PhantomJS (`run-tests.js`) and/or
  Firefox+Firebug (`run-tests.html`) or any browser supporting JavaScript console
  to run the tests in CLJS.
* The namespaces `clip-test.testutil` and `clip-test.testutil-cljs` have common
  functions that provide similar functionality in Clojure and ClojureScript.

### Examples

The following projects use _clip-test_ for CLJS testing:

* [Quiddity](https://github.com/kumarshantanu/quiddity)
* [Basil](https://github.com/kumarshantanu/basil)

## Development of this library

### Running tests

Clean old stuff if required (esp for ClojureScript testing):

```
lein clean
lein dev cljsbuild clean
```

Run the tests (you may see failures and errors - they are expected):

```
lein dev test           # test with latest stable Clojure version
lein dev cljsbuild test # test with ClojureScript (needs `phantomjs` installed)
lein all test           # test with all supported Clojure versions
```

### Building the JAR

Clean old stuff if required (see 'Running tests' section above.) Then run this:

```
lein jar      # create the JAR file
lein install  # install JAR to local Maven repo
```

## Getting in touch

On Twitter: [@kumarshantanu](https://twitter.com/kumarshantanu)

E-mail: kumar(dot)shantanu at gmail.com

## License

Copyright © 2012 Shantanu Kumar

Distributed under the Eclipse Public License, the same as Clojure.

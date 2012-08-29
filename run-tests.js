try {
  phantom.injectJs('target/clip-test.js');
  console.log('Injected target/clip-test.js');
  clip_test.run_tests.run();
} catch (e) {
  console.log('Found exception');
  console.log(e);
  console.log(e.fileName);
  console.log(e.trace);
} finally {
  phantom.exit();
}
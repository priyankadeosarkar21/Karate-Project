function fn() {
  var env = karate.env; // get system property 'karate.env'
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
    testConfig: 'bar'
  }
  if (env == 'dev') {
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'e2e') {
    // customize
  }
  config.myObject = read('classpath:test.json');
  config.myFunction = read('classpath:test.js');
  config.myUtils = karate.call('classpath:utils.feature');
  config.myCommon = read('classpath:common.feature');
  return config;
}
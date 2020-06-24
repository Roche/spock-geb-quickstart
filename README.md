# Spock Geb Quickstart

This project simplifies executing Spock and Geb-based frontend tests. 

## Features

Out of the box:
* HAR file recording (with [BrowserMob Proxy](https://github.com/lightbody/browsermob-proxy)),
* Screenshot capturing,
* A dockerized browser (with [Webdriver testcontainers](https://www.testcontainers.org/modules/webdriver_containers)),

## Getting Started

Run the sample tests:

```
gradlew :spock-geb-quickstart-simple-example:test
gradlew :spock-geb-quickstart-more-examples:acceptanceTests
```

Run tests with a dockerized browser:

```
gradlew :spock-geb-quickstart-simple-example:dockerTest
```

Check the test results:

```
spock-geb-quickstart-simple-example/build/geb-spock-reports/index.html
spock-geb-quickstart-more-examples/build/geb-spock-reports/index.html
```

Read more:
1. Simple introduction: https://craigatk.github.io/spock-geb-intro/#/31
2. Full docs: http://www.gebish.org/manual/current/

## Known issues
By default, the tests will trust any invalid SSL certificates. This will be fixed in a future release.

## Contribution

Everyone is warm welcome to contribute! \
Please make sure to read the [Contributing Guide](CONTRIBUTING.md) and [Code of Conduct](CODE_OF_CONDUCT.md) before making a pull request.

## License

Project is released under [Apache License, Version 2.0](https://opensource.org/licenses/Apache-2.0) license.


# Spock Geb Quickstart

<nobr>[![Build Status](https://github.com/Roche/spock-geb-quickstart/actions/workflows/gradle.yml/badge.svg)](https://github.com/Roche/spock-geb-quickstart/actions/workflows/gradle.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)</nobr>

This project simplifies executing Spock and Geb-based frontend tests. 

## Features

Out of the box:
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
gradlew :spock-geb-quickstart-simple-example:test
```

Check the test results:

```
spock-geb-quickstart-simple-example/build/geb-spock-reports/index.html
spock-geb-quickstart-more-examples/build/geb-spock-reports/index.html
```

View the sample test projects:

1. [Simplest possible project](https://github.com/Roche/spock-geb-quickstart/tree/master/spock-geb-quickstart-simple-example)
2. [A more complicated project, with multiple source sets](https://github.com/Roche/spock-geb-quickstart/tree/master/spock-geb-quickstart-more-examples)

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

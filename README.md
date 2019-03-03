# what-street-what-number
[![Build Status](https://travis-ci.org/l-ray/what-street-what-number.svg?branch=master)](https://travis-ci.org/l-ray/what-street-what-number)
[![codecov](https://codecov.io/gh/l-ray/what-street-what-number/branch/master/graph/badge.svg)](https://codecov.io/gh/l-ray/what-street-what-number)

Converts an address line holding street- and house number information into a JSON format separating both fields.

Prerequisites
-------

- Java 8 or higher
- Apache Maven

Building with Maven
-----

Run Maven 3.x.x in the 'root' directory:

```
mvn clean install
```

As a result, the ```target``` directory will hold an executable JAR.

Run the program
-----
Executing the executable Jar, handing over the source address line will return a serialized JSON object.
```
java -jar addressline-1.0-SNAPSHOT-jar-with-dependencies.jar "Gero-Roessline-Straße 15 b"
```
will return
```
{"street":"Gero-Roessline-Straße","housenumber":"15 b"}
```

# SpaceAPI Parser for Kotlin/Java

This is a parser library written in Kotlin that can be used from Kotlin and
Java.

The parser is quite lenient, so it will ignore unknown fields, should be able
to deal with multiple versions and will sometimes even parse invalid endpoint
JSON. (If you need strict validation, use a JSON schema based validator
instead!)


## API Version Support

This library supports parsing any valid JSON file using the following schema version:

- v0.13
- v14


## Installing

### Gradle

First, ensure that the Bintray repository is added to the gradle config:

```groovy
repositories {
    maven {
        url "https://dl.bintray.com/spaceapi-community/maven"
    }
}
```

(We may at a later point in time submit the package to JCenter.)

Then, add the dependency to your `build.gradle`:

```groovy
implementation 'spaceapi-community:spaceapi-kt:0.1.0'
```


## Usage

### Kotlin

```kotlin
import io.spaceapi.types.decodeFromString

// ...

val parsed = decodeFromString("{...}")
```

### Java

```java
import io.spaceapi.types.Status;
import io.spaceapi.types.StatusKt;

// ...

final Status parsedStatus = StatusKt.decodeFromString("{...}");
```


## Building

To build a JAR file, run:

    ./gradlew build

The resulting JAR files can be found in the `build/libs/` directory.


## Testing

Run the following command to run the unit tests:

    ./gradlew test

## License

This project is licensed under the GNU General Public License v3.0 or later.

    spaceapi-kt
    Copyright (C) 2020 Danilo Bargen

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <https://www.gnu.org/licenses/>.

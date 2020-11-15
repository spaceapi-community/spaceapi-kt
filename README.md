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


## Usage (Kotlin)

```kotlin
import io.spaceapi.fromJson

val parsed = fromJson("{...}")
```


## Building

To build a JAR file, run:

    ./gradlew build

The resulting JAR files can be found in the `build/libs/` directory.


## Testing

Run the following command to run the unit tests:

    ./gradlew test

# SpaceAPI Parser for Kotlin/Java

This is a parser library written in Kotlin that can be used from Kotlin and
Java.

The parser is quite lenient, so it will ignore unknown fields, should be able
to deal with multiple versions and will sometimes even parse invalid endpoint
JSON. (If you need strict validation, use a JSON schema based validator
instead!)

## Testing

    ./gradlew test

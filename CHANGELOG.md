# Changelog

This project follows semantic versioning.

Possible log types:

- `[added]` for new features.
- `[changed]` for changes in existing functionality.
- `[deprecated]` for once-stable features removed in upcoming releases.
- `[removed]` for deprecated features removed in this release.
- `[fixed]` for any bug fixes.
- `[security]` to invite users to upgrade in case of vulnerabilities.

### Unreleased

### v0.6.1 (2024-05-05)

- [fixed] Correctly publish library with Java 8 support. (Version 0.6.0 was
  accidentally published for Java 17+ only.)

### v0.6.0 (2024-05-05)

- [added] Implement `network_traffic` sensor (#22)
- [changed] Serialize and deserialize all URLs as `String` instead of
  `java.net.URL`, because of various problems with the Java URL API, including
  the rejection of perfectly valid URLs (#37)

### v0.5.0 (2023-09-02)

- [changed] Serialize and deserialize timestamp fields as `Instant` (#36)
- [changed] If you're using this library on Android, this requires at least API
  level 26 (Android 8), or a backport of the `java.time` APIs! (#36)

### v0.4.1 (2023-04-25)

- [fixed] Make `spacefed.spacephone` a non-required field (#34)

### v0.4.0 (2023-04-11)

- [changed] Upgrade Gradle to 8
- [changed] Upgrade Kotlin to 1.8
- [changed] Upgrade kotlinx-serialization-json to 1.5
- [added] CI: Test on Java 16 and 18 as well

### v0.3.0 (2021-05-13)

- [changed] Upgrade Kotlin to 1.5
- [changed] Upgrade kotlinx-serialization-json to 1.2.1

### v0.2.1 (2020-11-23)

- [changed] All fields are now marked as @JvmField for direct access from Java

### v0.2.0 (2020-11-22)

- [added] Support for all v0.13 fields (#16)
- [added] Support for all v14 fields (#17)
- [added] Better initial validation when parsing (#12)
- [changed] Reorganized entry point, `SpaceApiParser` is now used (#10)
- [changed] All deserializing exceptions are now wrapped in `ParseError` (#19)

### v0.1.0 (2020-11-15)

Initial release that supports deserializing all the required fields of schema
versions v0.13 and v14.

# Changelog

This project follows semantic versioning.

Possible log types:

- `[added]` for new features.
- `[changed]` for changes in existing functionality.
- `[deprecated]` for once-stable features removed in upcoming releases.
- `[removed]` for deprecated features removed in this release.
- `[fixed]` for any bug fixes.
- `[security]` to invite users to upgrade in case of vulnerabilities.

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

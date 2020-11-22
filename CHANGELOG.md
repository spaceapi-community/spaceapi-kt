# Changelog

This project follows semantic versioning.

Possible log types:

- `[added]` for new features.
- `[changed]` for changes in existing functionality.
- `[deprecated]` for once-stable features removed in upcoming releases.
- `[removed]` for deprecated features removed in this release.
- `[fixed]` for any bug fixes.
- `[security]` to invite users to upgrade in case of vulnerabilities.

### v0.2.0 (2020-11-22)

- [added] Support for all v0.13 fields (#16)
- [added] Support for all v14 fields (#17)
- [added] Better initial validation when parsing (#12)
- [changed] Reorganized entry point, `SpaceApiParser` is now used (#10)
- [changed] All deserializing exceptions are now wrapped in `ParseError` (#19)

### v0.1.0 (2020-11-15)

Initial release that supports deserializing all the required fields of schema
versions v0.13 and v14.

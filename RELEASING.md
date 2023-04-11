# Releasing

Set variables:

    export VERSION=X.Y.Z
    export GPG_KEY=20EE002D778AE197EF7D0D2CB993FF98A90C9AB1

Ensure that `ossrhUsername` and `ossrhPassword` are defined in your
`~/.gradle/gradle.properties` file.

Update version numbers:

    vim -p build.gradle.kts README.md CHANGELOG.md

Build:

    ./gradlew clean build

Add and commit:

    git commit -S${GPG_KEY} -m "Release v${VERSION}"

Publish the library to Sonatype:

    ./gradlew publish

Afterwards, go to https://s01.oss.sonatype.org/#stagingRepositories and:

- Close the repository
- Release the repository

Tag and push:

    git tag -s -u ${GPG_KEY} v${VERSION} -m "Version ${VERSION}"
    git push && git push --tags

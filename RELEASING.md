# Releasing

Set variables:

    export VERSION=X.Y.Z
    export GPG_KEY=EA456E8BAF0109429583EED83578F667F2F3A5FA
    export BINTRAY_USER=...
    export BINTRAY_KEY=...

Update version numbers:

    vim -p build.gradle.kts README.md CHANGELOG.md

Build:

    rm -r build
    ./gradlew build

Add and commit:

    git commit -S${GPG_KEY} -m "Release v${VERSION}"

Publish the library to Bintray:

    ./gradlew bintrayUpload

Tag and push:

    git tag -s -u ${GPG_KEY} v${VERSION} -m "Version ${VERSION}"
    git push && git push --tags

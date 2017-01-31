# mytool
[![Build Status](https://travis-ci.org/hongkailiu/mytool.svg?branch=master)](https://travis-ci.org/hongkailiu/mytool)
[![Coverage Status](https://coveralls.io/repos/github/hongkailiu/mytool/badge.svg?branch=master)](https://coveralls.io/github/hongkailiu/mytool?branch=master)
[![Release](https://jitpack.io/v/hongkailiu/mytool.svg)](https://jitpack.io/#hongkailiu/mytool)

## Build

```sh
$ ./gradlew clean distZip
```

The published version is also available in [JitPack](https://jitpack.io/#hongkailiu/mytool).
For example, download version 0.1.0.

```sh
$ wget https://jitpack.io/com/github/hongkailiu/mytool/0.1.0/mytool-0.1.0.zip
```

## Find orphans

```sh
$ cd build/distributions/
$ unzip mytool-<version>.zip
$ cd mytool-<version>/
$ vi bin/findOrphansR.sh
```

Modify the value of _GIT_FOLDER_ accordingly.

Then run

```sh
$ ./bin/findOrphansR.sh
```

## Run tests

```sh
$ ./gradlew clean test
```
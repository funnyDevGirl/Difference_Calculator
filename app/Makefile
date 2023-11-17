make run-dist:
	./build/install/app/bin/app

.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.4

run:
	./gradlew run

#clean:
#	./gradlew clean

build:
	./gradlew clean
	./gradlew clean build

#install:
#	./gradlew clean install

test-report:
	./gradlew test
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

check-deps:
	./gradlew dependencyUpdates -Drevision=release

build-run: build run

.PHONY: build
.PHONY: test
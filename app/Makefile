make run-dist:
	./build/install/app/bin/app

.DEFAULT_GOAL := build-run

setup:
	gradle wrapper --gradle-version 7.4

clean:
	./gradlew clean

build:
	cd app && ./gradlew clean build

run:
	./gradlew run --args="-h"

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	cd app && ./gradlew jacocoTestReport

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -h

check-updates:
	./gradlew dependencyUpdates

build-run: build run

.PHONY: build
#.PHONY: test
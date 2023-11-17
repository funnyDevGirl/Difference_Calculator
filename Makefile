.DEFAULT_GOAL := build-run

build:
	make -C app build

lint:
	make -C app lint
build-run: build run

.PHONY: build
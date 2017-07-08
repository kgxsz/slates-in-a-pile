# Slates in a Pile

##### A simple template for building d3 enabled presentations - see an example [here](https://slates-in-a-pile.keigo.io).
[![Build Status](https://travis-ci.org/kgxsz/slates-in-a-pile.svg?branch=master)](https://travis-ci.org/kgxsz/slates-in-a-pile)

## Local development setup

- You will need to install [Boot](https://github.com/boot-clj/boot#install).
- For a no frills build: `boot build`. You will find the assets in the `target/` directory.
- To serve those assets: `boot serve`.
- Run `boot dev` to get a development environment going.
- Do `boot repl --client` then `(start-repl)` to start the browser connected repl.

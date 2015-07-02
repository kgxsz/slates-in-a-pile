# Slates in a Pile

#####A simple template for building beautiful d3 enabled presentations - see an example [here](https://kgxsz-slates-in-a-pile.divshot.io).
[![Build Status](https://travis-ci.org/kgxsz/slates-in-a-pile.svg?branch=master)](https://travis-ci.org/kgxsz/slates-in-a-pile)

## Introduction
One day you're making a slide deck for some presentation you're going to give. You have some good data to support your arguments. You want to include it. You use some tool to convey the data in some visual manner. You screenshot it. You add it to your deck.

**Your blurry data is really crumby and everybody hates your presentation.**

Slates in a Pile solves this first-world-problem by giving you a no-frills, light-weight, single-page webapp backed by the power of [d3](http://d3js.org/). Now you can literally have the beauty, and interactivity of d3 in your presentation.

## Disclaimer
The code is minimal, and completely written in Clojure and ClojureScript. There's just enough to get you moving, if you want more features, you have to build it yourself.


## Local development setup

- You will need to install [Boot](https://github.com/boot-clj/boot#install).
- For a no frills build: `boot build`. You will find the assets in the `target/` directory.
- To serve those assets: `boot serve`.
- Run `boot dev` to get a development environment going.
- Do `boot repl --client` then `(start-repl)` to start the browser connected repl.


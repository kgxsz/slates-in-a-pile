# Slates in a Pile

#####A simple template for building beautiful d3 enabled presentations - see an example [here](http://production.slates-in-a-pile.divshot.io/).
[![Build Status](https://travis-ci.org/kgxsz/slates-in-a-pile.svg?branch=master)](https://travis-ci.org/kgxsz/slates-in-a-pile)

## Introduction
As developers, we sometimes need to give presentations. Often, we'd like to convey some data in a visually compelling way, or perhaps include some flavour of interactivity for our audience. The problem is that doing these things with the standard presentation tools out there is difficult and tedious.

Slates in a Pile solves this first-world-problem by giving you a no-frills, light-weight, single-page presentation webapp backed by the power of [d3](http://d3js.org/). Now you can literally have the beauty, and interactivity of d3 in your presentation.


## What's in the box?
The code is completely written in Clojure and ClojureScript. It's backed by [Om](https://github.com/omcljs/om) and the styling is in Clojure, compiled by the [Garden](https://github.com/noprompt/garden) library. The codebase is just enough to a) get you started with what you need, and b) understand completely in a lazy afternoon. Once you have a good grasp of what's going on, it should be pretty simple for you to extend it to build whatever creation you


## Local development setup

- You will need to install [Boot](https://github.com/boot-clj/boot#install).
- For a no frills build: `boot build`. You will find the assets in the `target/` directory.
- To serve those assets: `boot serve`.
- Run `boot dev` to get a development environment going.
- Do `boot repl --client` then `(start-repl)` to start the browser connected repl.

## Core concepts

#### Slates
Slates are analogous to slides. Your presentation is a pile of slates. To add a new slate, you can follow the patterns that exist on other slates. However, there are a few subtle points worth mentioning:

- Don't forget to add it to the `pile` component in `core.cljs`.
- Include your new slate's state in the global state atom in `core.cljs`.

#### State n transitions
A slate can have state. That state is represented by an integer n. this slate state is defined in the `state` atom in `core.cljs`. When you press the left or right arrow key whilst on a slate, you will decrease or increase n. This state transition will cause that slate to invoke both it's `render-state` and `did-update` functions. 

You can use any function of n to generate data on state transitions. You can decorate that data with d3.

It's that simple. Now go Have fun.

# SILLY CARD GAME CA ECM 2414

## Table of contents
* [Introduction](#Introduction)
* [Project Description](#Project-Description)
* [Prerequisites](#Prerequisites)
* [Installation](#Installation)
* [Getting started](#Getting-started)
* [Testing](#Testing)
* [Contributors](#Contributors)
* [Details](#Details)


## Introduction

Simple card game using Java threading, and advanced OOP techniques.
Created using Java, tested using JUnit / Java reflection.


## Project Description

Unlimited number of players dealt a hand of 4 cards. Aim is to get 4 of the same card.
There is a discard pile to the left of each player, and a pickup pile to the right of the player.
Similar rules to GoFish, however players discard card to the bottom of the right deck, and pickup
from the top of the left deck. Players should not hold onto non-preferred denomination of cards
(i.e. Player2 should not hold onto 1's or 4's for example, and should prefer 2's).


## Prerequisites

You must have Java Installed on your machine.
Download the latest version here: https://www.oracle.com/uk/java/technologies/downloads/

You must have a JDK Installed on your machine.
Download the latest version here: https://www.oracle.com/uk/java/technologies/downloads/

## Installation

* Git clone

```Git clone https://github.com/EliotDe/silly-card-game```


## Getting-started

To run from GitHub Clone:

* Change directory to project
```cd <project location>/src ```

* Run CardGame.java
```java CardGame.java```

* Enter number of player
* Enter the absolute path of the file containing the pack of cards. (Pack should contain 8*n cards where n is the number of players!)
* Resources folder: (default.txt = 3 players, default2,txt = 30 players, default3 = 10 players)

To run from executable CardGame.jar file:

* Run CardGame.jar
```java CardGame.jar```
* Enter number of player
* Enter the absolute path of the file containing the pack of cards. (Pack should contain 8*n cards where n is the number of players!)
* Resources folder: (default.txt = 3 players, default2,txt = 30 players, default3 = 10 players)


## Testing

To run from GitHub clone:

* Change directory to project
```cd <project location>/src ```

* Run TestSuite.java
```java -cp "lib/*;out" org.junit.runner.JUnitCore TestSuite```


## Contributors

Yousuf Ahmed,
Eliot Deacon

## Details

* **Authors:** Yousuf Ahmed [Linkedin](https://www.linkedin.com/in/yousufaahmed/), Eliot Deacon [Linkedin](https://www.linkedin.com/in/eliot-deacon/)
* **License:** This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
* **Source:** [GitHub Repository](https://github.com/EliotDe/silly-card-game)

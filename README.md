# board-master
<div align="center">

![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/SverreNystad/board-master/gradle.yml)
![GitHub top language](https://img.shields.io/github/languages/top/SverreNystad/board-master)
![GitHub language count](https://img.shields.io/github/languages/count/SverreNystad/board-master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Project Version](https://img.shields.io/badge/version-0.0.1-blue)](https://img.shields.io/badge/version-0.0.1-blue)

<img src="docs/images/BoardMaster.png" width="50%" alt="BoardMaster" style="display: block; margin-left: auto; margin-right: auto;">
</div>

<details> 
<summary><b>📋 Table of contents </b></summary>

- [board-master](#board-master)
  - [Introduction](#introduction)
    - [What is BoardMaster?](#what-is-boardmaster)
      - [Main-menu:](#main-menu)
      - [TicTacToe-menu:](#tictactoe-menu)
      - [TicTacToe game:](#tictactoe-game)
    - [Minimax Algorithm](#minimax-algorithm)
  - [Setup](#setup)
    - [Prerequisites](#prerequisites)
  - [Installation](#installation)
    - [1. Clone the repository](#1-clone-the-repository)
    - [2. Navigate to the Project Directory:](#2-navigate-to-the-project-directory)
  - [Usage](#usage)
  - [Documentation](#documentation)
  - [Contributors](#contributors)
  - [License](#license)

</details>

## Introduction

### What is BoardMaster?
BoardMaster is a full-stack application designed for players to enjoy various board games against a range of bots, such as RandomMoveAgent, MiniMaxAgent, and others. It offers an immersive gaming experience, combining classic board game fun with modern AI technology.

#### Main-menu:
<div align="center"> 
  <img src="docs/images/BoardMaster_mainMenu.png" width="50%" alt="BoardMaster" style="display: block; margin-left: auto; margin-right: auto;">
</div>

#### TicTacToe-menu:

<div style="display: flex;" align="center">
  <img src="docs/images/BoardMaster_TicTacToe_BotType.png" alt="BoardMaster" style="width: 45%; margin-right: 5px; height: auto; ">
  <img src="docs/images/BoardMaster_TicTacToe_Menu.png" alt="BoardMaster" style="width: 45%; height: auto; ">
</div>

#### TicTacToe game:
<div style="display: flex;" align="center">
  <img src="docs/images/BoardMaster_TicTacToe_PlayerStart.png" alt="BoardMaster" style="width: 45%; margin-right: 5px; height: auto; ">
  <img src="docs/images/BoardMaster_TicTacToe_BotStart.png" alt="BoardMaster" style="width: 45%; height: auto;">
</div>

### Minimax Algorithm
The games this algorithm works great for are what game theorists call deterministic , two-player turn-taking, perfect information, zero-sum games. These games are "fully observable", meaning that you can see everything that is going on in the game. They are also "deterministic", meaning that there is no element of chance involved in the game. There is no dice rolling or card drawing. The game is "zero-sum", meaning that one player's gain is the other player's loss. In other words, if you add up all the gains and losses for each player, they will sum to zero. Finally, the game is "turn-taking", meaning that the players alternate making moves, and "perfect information", meaning that no information is hidden from either player. Chess, checkers, tic-tac-toe, Go, and Othello are all examples of deterministic, two-player, turn-taking, perfect information, zero-sum games. However games that has too many possible moves, such as chess, will take too long to compute and will not be feasible to use this algorithm on.


## Setup
To setup the project, one needs to have all the prerequisites installed. Clone the repository, install the dependencies and build the project. This is described in more detail below.

### Prerequisites
Before setting up BoardMaster, ensure that your system meets the following requirements:

* Ensure that git is installed on your machine. [Download Git](https://git-scm.com/downloads)
* Ensure that Docker is installed on your machine. [Download Docker](https://www.docker.com/products/docker-desktop)


## Installation
Follow these steps to set up BoardMaster on your local machine:

### 1. Clone the repository
```cmd
git clone https://github.com/SverreNystad/board-master.git
```

### 2. Navigate to the Project Directory:
After cloning, move into the BoardMaster project directory:
```cmd
cd board-master
```

## Usage
To start the application locally, follow the steps below:
```bash
docker compose up --build
```

After starting the application, the frontend should be available at [http://localhost:3000/](http://localhost:3000/) and should pop up in your default browser.
The backend should be available at [http://localhost:8080/](http://localhost:8080/).

For the full development setup, see the [Developer Setup](docs/manuals/developer_setup.md) guide.

## Documentation
- [Developer Setup](docs/manuals/developer_setup.md)
- [Testing](docs/manuals/testing.md)
- [API Documentation](docs/manuals/api_documentation.md)


![Alt text](docs/images/backend.png)

## Contributors
<table align="center">
  <tr>
    <td align="center">
        <a href="https://github.com/JonBergland">
            <img src="https://github.com/JonBergland.png?size=100" width="100px;" alt="Jon Bergland"/><br />
            <sub><b>Jon Bergland</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/SverreNystad">
            <img src="https://github.com/SverreNystad.png?size=100" width="100px;"/><br />
            <sub><b>Sverre Nystad</b></sub>
        </a>
    </td>
  </tr>
</table>

## License
Licensed under the [MIT License](LICENSE).


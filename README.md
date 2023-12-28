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
    - [Minimax Algorithm](#minimax-algorithm)
    - [Planned Features](#planned-features)
  - [Setup](#setup)
    - [Prerequisites](#prerequisites)
  - [Setup](#setup-1)
    - [1. Clone the repository](#1-clone-the-repository)
    - [2. Navigate to the Project Directory:](#2-navigate-to-the-project-directory)
    - [3. Build the Project:](#3-build-the-project)
  - [Usage](#usage)
  - [Tests](#tests)
  - [Code Coverage](#code-coverage)
  - [Contributors](#contributors)
  - [License](#license)

</details>

## Introduction


### Minimax Algorithm
The games this algorithm works great for are what game theorists call deterministic , two-player turn-taking, perfect information, zero-sum games. These games are "fully observable", meaning that you can see everything that is going on in the game. They are also "deterministic", meaning that there is no element of chance involved in the game. There is no dice rolling or card drawing. The game is "zero-sum", meaning that one player's gain is the other player's loss. In other words, if you add up all the gains and losses for each player, they will sum to zero. Finally, the game is "turn-taking", meaning that the players alternate making moves, and "perfect information", meaning that no information is hidden from either player. Chess, checkers, tic-tac-toe, Go, and Othello are all examples of deterministic, two-player, turn-taking, perfect information, zero-sum games. However games that has too many possible moves, such as chess, will take too long to compute and will not be feasible to use this algorithm on.

### Planned Features

## Setup
To setup the project, one needs to have all the prerequisites installed. Clone the repository, install the dependencies and build the project. This is described in more detail below.

### Prerequisites
Before setting up BoardMaster, ensure that your system meets the following requirements:
<ul> 

Ensure that git is installed on your machine. [Download Git](https://git-scm.com/downloads)

</ul>
<ul>
  <details> <summary><b> Java JDK 17 or higher (Download from Oracle's website) </b></summary>
  BoardMaster requires Java JDK to be installed. The project is tested with JDK 17, but it should work fine with any version 17 or higher.

  * Java JDK 17 or higher - Java Development Kit is essential for compiling and running Java applications.
    * Download and install it from Oracle's Java JDK Download Page or adopt an open-source JDK like AdoptOpenJDK.
    * After installation, verify the installation by running java -version and ```javac -version``` in your command line or terminal.
  </details>
</ul>
<ul>
  <details> 
  <summary><b> Gradle 8 or higher </b></summary>
  Gradle is used as the build tool for BoardMaster. It automates the process of building, testing, and deploying the application.

  * Gradle 8 or higher - Gradle brings advanced build toolkit to manage dependencies and other aspects of the build process.
    * You can download Gradle from the Gradle Download Page.
    * Alternatively, if you are using a Gradle Wrapper script (gradlew or gradlew.bat), you do not need to manually install Gradle, as the wrapper script will handle the installation for you.
    * To confirm that Gradle is properly installed, run ```gradlew -v``` in your command line or terminal which will display the installed Gradle version.
  </details>
</ul>
Ensure that both Java and Gradle are properly installed and configured in your system's PATH environment variable for seamless execution of BoardMaster.

## Setup
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

### Frontend
Install node package manager
```cmd
npm install
```

Start node package manager
```cmd
npm start
```
### Backend
```cmd
```

### 3. Build the Project:
Inside the project directory, use Gradle to build the project:
```cmd
gradlew build
```
This command compiles the project and downloads all necessary dependencies.


## Usage
To run the project, run the following command in the root directory of the project:
```cmd
gradlew run
```

## Tests
To run all the tests, run the following command in the root directory of the project:
```cmd
gradlew test
```

## Code Coverage
To generate a code coverage report, use the following Gradle command:

```cmd
gradlew test jacocoTestReport
```

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


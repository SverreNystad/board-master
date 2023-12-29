# Understanding the Minimax Algorithm
## What is Minimax?
Minimax is a decision rule used for minimizing the possible loss while maximizing the potential gain in a two-player game or scenario. It's especially prevalent in game theory and artificial intelligence for turn-based games.

## How Minimax Works
The algorithm works by simulating all possible moves in the game, predicting the opponent's responses to those moves, and then selecting the move that maximizes the player's chances of winning (or achieving the best outcome). It's like trying to think a few steps ahead and considering what your opponent might do in response to your moves. 

## Understanding Game Trees
A game tree is a visual representation of all possible moves in a game from a given point. Each node represents a game state, and each branch represents a possible move. Minimax evaluates these nodes to decide the best move.

<img src="https://upload.wikimedia.org/wikipedia/commons/6/6f/Minimax.svg" width="50%" alt="game_tree" style="display: block; margin-left: auto; margin-right: auto;">

## Basic Algorithm
Minimax performs a depth-first search on the game tree. Here's how it works:

1. Start from the current game state as the root node.
2. Expand nodes down to a certain depth.
3. Apply a heuristic to evaluate the utility of leaf nodes.
4. Propagate these values back up the tree to decide the best move.

### Selecting the Best Action
Here's how you select the best action in a game state:

**Pseudocode for action selection:**
```py
def getAction(self, gameState) -> Action:
    """
    Find the best action that the state can do
    Returns the minimax action from the current gameState
    """

    best_action = None
    current_best_value = float('-inf')
    for action in gameState.getLegalActions():
        # Evaluate the action
        value = self.minimax(gameState.generateSuccessor(action), True)
        # Determine if it is the best action
        if value > current_best_value:
            current_best_value = value
            best_action = action

    return best_action
```

### The Minimax Function
This function evaluates the utility of a game state. (AKA goodness of a situation for the current player)

**Pseudocode:**
```py
def minimax(node, isMaximizingPlayer) -> float:
    if node is a terminal node:
        return the heuristic value of node

    if isMaximizingPlayer:
        bestValue = -∞
        for each child of node:
            value = minimax(child, false)
            bestValue = max(bestValue, value)
        return bestValue

    else:
        bestValue = +∞
        for each child of node:
            value = minimax(child, true)
            bestValue = min(bestValue, value)
        return bestValue
```
## Variants of Minimax with optimalizations
### Depth limited Minimax
For games with a large number of possible moves and game states, it's advisable to implement some form of cutoff, like a depth limit or a time constraint, to make the computation more manageable.
```py
def minimax_depth(node, depth, isMaximizingPlayer) -> float:
    if depth == 0 or node is a terminal node:
        return the heuristic value of node

    if isMaximizingPlayer:
        bestValue = -∞
        for each child of node:
            value = minimax_depth(child, depth - 1, false)
            bestValue = max(bestValue, value)
        return bestValue

    else:
        bestValue = +∞
        for each child of node:
            value = minimax_depth(child, depth - 1, true)
            bestValue = min(bestValue, value)
        return bestValue
```

### Alpha-Beta Pruning
Alpha-Beta pruning optimizes Minimax by eliminating branches that don't need to be explored. It uses two values, alpha and beta, to represent the minimum score that the maximizing player is assured and the maximum score that the minimizing player is assured, respectively.

<img src="https://upload.wikimedia.org/wikipedia/commons/9/91/AB_pruning.svg" width="50%" alt="gaxme_tree" style="display: block; margin-left: auto; margin-right: auto;">

**Pseudocode:**
```py
def alphabeta(node, depth, α, β, isMaximizingPlayer) -> float:
    if depth == 0 or node is a terminal node:
        return the heuristic value of node

    if isMaximizingPlayer:
        value = -∞
        for each child of node:
            value = max(value, alphabeta(child, depth - 1, α, β, false))
            α = max(α, value)
            if α >= β:
                break
        return value

    else:
        value = +∞
        for each child of node:
            value = min(value, alphabeta(child, depth - 1, α, β, true))
            β = min(β, value)
            if β <= α:
                break
        return value

```

### Negamax
Negamax is a simplified version of Minimax, based on the principle that max(a, b) = -min(-a, -b). It's used in scenarios where the utility values of positions are precisely opposite for both players.

**Pseudocode:**
```py
def negamax(node, depth, color) -> float:
    if depth == 0 or node is a terminal node:
        return color * the heuristic value of node

    bestValue = -∞
    for each child of node:
        value = -negamax(child, depth - 1, -color)
        bestValue = max(bestValue, value)

    return bestValue
```

### Iterative Deepening in Minimax
Iterative Deepening is an enhancement to the Minimax algorithm that combines the benefits of depth-first and breadth-first search. Here’s how it can be integrated:

#### What is Iterative Deepening?
Iterative Deepening involves performing a series of depth-limited searches, each time increasing the depth limit, until a goal state is found or a specified depth is reached. This approach is beneficial because it retains the memory efficiency of depth-first search while ensuring that the shallowest goal state is found first, like in breadth-first search.

#### Integrating Iterative Deepening with Minimax
In the context of Minimax, Iterative Deepening is used to explore the game tree gradually. At each iteration, the depth of the search is increased. This is particularly useful in games with a vast number of possible moves, as it allows the algorithm to search more efficiently within a time constraint or other resource limitations.

**Pseudocode for Iterative Deepening Minimax:**
Here’s how you might implement Iterative Deepening in a Minimax algorithm:
```py
def iterativeDeepeningMinimax(state, maxDepth) -> Action:
    """
    Performs iterative deepening minimax search up to the specified maxDepth.
    Returns the best action from the root state.
    """
    best_action = None
    for depth in range(1, maxDepth + 1):
        best_action = depthLimitedMinimax(state, depth)
    return best_action

def depthLimitedMinimax(state, depth, isMaximizingPlayer=True) -> float:
    """
    Minimax function with depth limitation.
    """
    if depth == 0 or node is a terminal node:
        return the heuristic value of state

    if isMaximizingPlayer:
        bestValue = float('-inf')
        for child in node.children:
            value = depthLimitedMinimax(child, depth - 1, False)
            bestValue = max(bestValue, value)
        return bestValue

    else:
        bestValue = float('inf')
        for child in node.children:
            value = depthLimitedMinimax(child, depth - 1, True)
            bestValue = min(bestValue, value)
        return bestValue

```

# Understanding the Minimax Algorithm
## What is Minimax?
Minimax is a decision rule used for minimizing the possible loss while maximizing the potential gain in a two-player game or scenario. It's especially prevalent in game theory and artificial intelligence for turn-based games.

## How Minimax Works
The algorithm works by simulating all possible moves in the game, predicting the opponent's responses to those moves, and then selecting the move that maximizes the player's chances of winning (or achieving the best outcome).

## Basic Algorithm
The basic idea of Minimax is to perform a depth-first search algorithm on the game tree, where nodes represent game states. It calculates the value of each state by considering all possible sequences of moves up to a certain depth, then chooses the move that leads to the best possible outcome.

The algorithm works in two parts. First is to find the utility of each possible move and then compare the different moves and take the best one.

The code for selecting the best action is always the same:
**Pseudocode:**
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
        value = self.minimax_depth(gameState.generateSuccessor(action), self.depth, True)
        # Determine if it is the best action
        if value > current_best_value:
            current_best_value = value
            best_action = action

    return best_action
```


**Pseudocode:**
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
## Variants of Minimax
### Alpha-Beta Pruning
Alpha-Beta pruning is an optimization technique for the Minimax algorithm. It reduces the number of nodes evaluated by the minimax algorithm in its search tree, pruning away branches that won't be selected.

Think of alpha and beta as the upper and lower bound for the values.

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

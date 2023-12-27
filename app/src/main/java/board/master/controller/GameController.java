package board.master.controller;

import board.master.view.View;

import board.master.model.Action;
import board.master.model.GameFactory;
import board.master.view.ViewFactory;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.AgentFactory;
import board.master.model.input.InputHandler;

public class GameController {

    private StateHandler model;
    private Agent firstAgent;
    private Agent secondAgent;
    private View view;

    public GameController(String gameName, String firstAgentStrategy, String secondAgentStrategy, InputHandler inputHandler) {
        this.model = GameFactory.getGame(gameName);
        this.view = ViewFactory.getView(gameName, "GUI", this);
        this.firstAgent = AgentFactory.getAgent(firstAgentStrategy, inputHandler);
        this.secondAgent = AgentFactory.getAgent(secondAgentStrategy, inputHandler);
    }

    public void startGame() {
        while (true) {
            // Choose action
            Action action = null;
            if (model.toMove() == 1) {
                action = firstAgent.getAction(model);
            } else {
                action = secondAgent.getAction(model);
            }
            makeMove(action);
        }
    }
    
    public void makeMove(Action action) {
        if (action != null) {
            model.result(action);
            view.updateBoard(model);
            if (model.isTerminal()) {
                // view.printWinner(model);
                System.exit(0);
            }
        }
    }
}
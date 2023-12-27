package board.master.view;


import board.master.controller.GameController;
import board.master.model.StateHandler;
import board.master.model.game.tic_tac_toe.TicTacToe;
import board.master.model.input.GUIInputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITicTacToe implements View {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusBar;
    private TicTacToe game;

    private GameController gameController;
    private GUIInputHandler inputHandler;

    public GUITicTacToe(GameController controller) {
        this.gameController = controller;
        this.inputHandler = new GUIInputHandler();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }

        statusBar = new JLabel("Player 1's turn");
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.getPosition(x, y) == 0) {
                // Action action = new Action(x, y); // Assuming Action takes x, y coordinates
                String action = x + "," + y;
                game.setPosition(x, y, game.toMove());
                buttons[x][y].setText(game.toMove() == 1 ? "X" : "O");
                inputHandler.setInput(action);
                // gameController.makeMove(action);
            }
        }
    }

    public static void main(String[] args) {
        GameController controller = new GameController("TicTacToe", "Player", "Player", new GUIInputHandler());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUITicTacToe(controller);
            }
        });
    }

    @Override
    public void updateBoard(StateHandler state) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateBoard'");
    }
}

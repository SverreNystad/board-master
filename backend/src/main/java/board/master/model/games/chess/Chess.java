package board.master.model.games.chess;

import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Pieces.Piece;
import board.master.model.games.chess.Pieces.Bishop;
import board.master.model.games.chess.Pieces.King;
import board.master.model.games.chess.Pieces.Knight;
import board.master.model.games.chess.Pieces.Pawn;
import board.master.model.games.chess.Pieces.Queen;
import board.master.model.games.chess.Pieces.Rook;
import board.master.model.Action;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Chess implements StateHandler {
    
    private Board board;
    private int toMove;
    private Map<String, Piece> pieces;

    
    public Chess() {
        this.toMove = 1;
        this.board = CreateInitialBoard();
        this.pieces = new HashMap<String, Piece>();
        initializePieces();

        pieces.forEach((key, value) -> {
            this.board.setPosition(value.row, value.column, key);
        } );

    }
    
    private static Board CreateInitialBoard() {
        return new Board(8,8);
    }

    private void initializePieces() { 
        for (int i = 0; i < 8; i++) { // Pawns
            Pawn whitePawn = new Pawn(Color.WHITE, 6, i);
            Pawn blackPawn = new Pawn(Color.BLACK, 1, i);
            this.pieces.put(whitePawn.getSymbol() + i, whitePawn);
            this.pieces.put(blackPawn.getSymbol() + i, blackPawn);
        }

        int[] RookColumns = {0, 7};
        for (int i : RookColumns) { // Rooks
            Piece whiteRook = new Rook(Color.WHITE, 7, i);
            Piece blackRook = new Rook(Color.BLACK, 0, i);
            this.pieces.put(whiteRook.getSymbol() + i, whiteRook);
            this.pieces.put(blackRook.getSymbol() + i, blackRook);
        }

        int[] KnightColumns = {1, 6};
        for (int i : KnightColumns) { // Knights
            Piece whiteKnight = new Knight(Color.WHITE, 7, i);
            Piece blackKnight = new Knight(Color.BLACK, 0, i);
            this.pieces.put(whiteKnight.getSymbol() + i, whiteKnight);
            this.pieces.put(blackKnight.getSymbol() + i, blackKnight);
        }

        int[] BishopColumns = {2, 5};
        for (int i : BishopColumns) { // Bishops
            Piece whiteBishop = new Bishop(Color.WHITE, 7, i);
            Piece blackBishop = new Bishop(Color.BLACK, 0, i);
            this.pieces.put(whiteBishop.getSymbol() + i, whiteBishop);
            this.pieces.put(blackBishop.getSymbol() + i, blackBishop);
        }

        // Queens and Kings
        Piece whiteQueen = new Queen(Color.WHITE, 7, 3);
        Piece blackQueen = new Queen(Color.BLACK, 0, 3);   
        this.pieces.put(whiteQueen.getSymbol(), whiteQueen);
        this.pieces.put(blackQueen.getSymbol(), blackQueen);
        
        Piece whiteKing = new King(Color.WHITE, 7, 4);
        Piece blackKing = new King(Color.BLACK, 0, 4);
        this.pieces.put(whiteKing.getSymbol(), whiteKing);
        this.pieces.put(blackKing.getSymbol(), blackKing);
    }

    private Chess(Board board, int toMove, Map<String, Piece> pieces) {
        Board newBoard = new Board(board.getRows(), board.getColumns());

        /*
        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                newBoard.setPosition(x, y, board.getPosition(x, y));
            }
        }*/
        
        Map<String, Piece> newPieces = new HashMap<String, Piece>();
        pieces.forEach((key, value) -> {
            newPieces.put(key, value.copy());
        } );

        newPieces.forEach((key, value) -> {
            newBoard.setPosition(value.row, value.column, key);
        } );

        this.pieces = newPieces;
        this.board = newBoard;
        this.toMove = toMove;
    }
    
    /**
     * {@inheritDoc}
     */
    public int toMove() {
        // Should be -1 for black, 1 for white
        return this.toMove;
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        for (Piece piece : this.pieces.values()) {
            if (toMove() == 1 && piece.getColor() == Color.BLACK) {
                piece.getValidMoves(this.board).forEach(action -> actions.add(action));
            } else if (toMove() == -1 && piece.getColor() == Color.WHITE) {
                piece.getValidMoves(this.board).forEach(action -> actions.add(action));
            }    
        }
        return actions;
    }

    /**
     * {@inheritDoc}
     * Assumes that the action is valid and that Action is a Move.
     */
    public StateHandler result(Action action) {
        // Make a copy of the board
        Chess newState = new Chess(this.board, this.toMove, this.pieces);


        // Find piece to move
        Move move = (Move) action;
        String currentPos = move.getX();
        Piece toMovePiece = newState.getPiece(currentPos);
        
        // Change its position 
        String newPos = move.getY();
        int toX = Character.getNumericValue(newPos.charAt(0));
        int toY = Character.getNumericValue(newPos.charAt(1));
        if (newState.getPiece(newPos) != null) {
            newState.pieces.remove(newState.getPiece(newPos).getSymbol());
        }
        toMovePiece.move(toX, toY, this.board);

        // Change turn
        newState.toMove *= -1;
        return newState;
    }

    private Piece getPiece(String position) {
        // Position is of from "xy" where x is the row and y is the column
        if (position.length() != 2) {
            throw new IllegalArgumentException("Position must be of length 2");
        }
        int x = Character.getNumericValue(position.charAt(0));
        int y = Character.getNumericValue(position.charAt(1));
        String pieceSymbol = this.board.getPosition(x, y);
        Piece piece = this.pieces.get(pieceSymbol);
        return this.pieces.getOrDefault(pieceSymbol, null);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTerminal() {
        // Check for checkmate
        Piece whiteKing = this.pieces.get("KW");
        Piece blackKing = this.pieces.get("KB");

        boolean whiteKingInCheck = isKingInCheck(whiteKing);
        boolean blackKingInCheck = isKingInCheck(blackKing);

        if (whiteKingInCheck) {
           return isKingInCheckMate(whiteKing);
            
        } else if (blackKingInCheck) {
            return isKingInCheckMate(blackKing);
        } else {
            return false;
        }
    }

    private boolean isKingInCheck(Piece king) {
        String kingPosition = String.valueOf(king.getRow()) +
                String.valueOf(king.getColumn());
        
        List<Action> actions = getActions();

        for (Action action : actions) {
            Move move = (Move) action;
            String newPos = move.getY();

            if (newPos.equals(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean isKingInCheckMate(Piece king) {
        // King is in check
        List<Action> whiteActions = king.getValidMoves(this.board);
        if (whiteActions.isEmpty()) {
            return true;
        }
        for (Action action : whiteActions) {
            Chess newState = (Chess) result(action);
            Piece newKing  = newState.pieces.get(king.getSymbol());

            //if there is a move that gets the king out of check, then it is not checkmate
            if (!newState.isKingInCheck(newKing)) {
                return false;
            }
        }
        // White is in checkmate
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public int utility(int player) {
        // TODO
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public Board getBoard() {
        return this.board;
    }
}

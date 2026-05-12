package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GameTest {
    
    private Game game;
    
    @BeforeEach
    public void setUp() {
        game = new Game();
    }
    
    @Test
    public void testCheckStateInitial() {
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }
    
    @Test
    public void testCheckStateXWinHorizontal() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinHorizontalRow2() {
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[5] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinHorizontalRow3() {
        game.board[6] = 'X';
        game.board[7] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinVertical() {
        game.board[0] = 'X';
        game.board[3] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinVerticalCol2() {
        game.board[1] = 'X';
        game.board[4] = 'X';
        game.board[7] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinVerticalCol3() {
        game.board[2] = 'X';
        game.board[5] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinDiagonal() {
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateXWinDiagonalReverse() {
        game.board[2] = 'X';
        game.board[4] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateOWinHorizontal() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    public void testCheckStateOWinVertical() {
        game.board[0] = 'O';
        game.board[3] = 'O';
        game.board[6] = 'O';
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    public void testCheckStateOWinDiagonal() {
        game.board[0] = 'O';
        game.board[4] = 'O';
        game.board[8] = 'O';
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    public void testCheckStateDraw() {
        char[] drawBoard = {'X','O','X','X','O','O','O','X','O'};
        game.board = drawBoard;
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.DRAW, state);
    }
    
    @Test
    public void testCheckStatePlaying() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }
    
    @Test
    public void testGenerateMovesEmpty() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
    }
    
    @Test
    public void testGenerateMovesSomeFilled() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.board[8] = 'X';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(6, moves.size());
        assertFalse(moves.contains(0));
        assertFalse(moves.contains(4));
        assertFalse(moves.contains(8));
    }
    
    @Test
    public void testGenerateMovesOneMove() {
        game.board[0] = 'X';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(8, moves.size());
        assertFalse(moves.contains(0));
        assertTrue(moves.contains(1));
    }
    
    @Test
    public void testGenerateMovesFull() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = 'X';
        }
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }
    
    @Test
    public void testEvaluatePositionXWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionXWinForPlayerO() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(-Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionOWin() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionOWinForPlayerX() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionDraw() {
        char[] drawBoard = {'X','O','X','X','O','O','O','X','O'};
        game.board = drawBoard;
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(0, value);
    }
    
    @Test
    public void testEvaluatePositionPlaying() {
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }
    
    @Test
    public void testGameConstructor() {
        assertNotNull(game);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(State.PLAYING, game.state);
        assertEquals(9, game.board.length);
    }
    
    @Test
    public void testBoardInitializedEmpty() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }
    
    @Test
    public void testPlayerSymbol() {
        Player p = new Player();
        p.symbol = 'X';
        assertEquals('X', p.symbol);
        p.symbol = 'O';
        assertEquals('O', p.symbol);
    }
    
    @Test
    public void testPlayerMove() {
        Player p = new Player();
        p.move = 5;
        assertEquals(5, p.move);
        p.move = 0;
        assertEquals(0, p.move);
        p.move = -1;
        assertEquals(-1, p.move);
    }
    
    @Test
    public void testPlayerSelected() {
        Player p = new Player();
        p.selected = true;
        assertTrue(p.selected);
        p.selected = false;
        assertFalse(p.selected);
    }
    
    @Test
    public void testPlayerWin() {
        Player p = new Player();
        p.win = true;
        assertTrue(p.win);
        p.win = false;
        assertFalse(p.win);
    }
    
    @Test
    public void testTicTacToeCellCreation() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertNotNull(cell);
        assertEquals(0, cell.getNum());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }
    
    @Test
    public void testTicTacToeCellCreationWithValues() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getCol());
    }
    
    @Test
    public void testTicTacToeCellSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
    }
    
    @Test
    public void testTicTacToeCellSetMarkerDisabled() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertFalse(cell.isEnabled());
    }
    
    @Test
    public void testTicTacToeCellGetRow() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 2);
        assertEquals(2, cell.getRow());
    }
    
    @Test
    public void testTicTacToeCellGetCol() {
        TicTacToeCell cell = new TicTacToeCell(0, 1, 0);
        assertEquals(1, cell.getCol());
    }
    
    @Test
    public void testUtilityPrintCharArray() {
        char[] testBoard = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }
    
    @Test
    public void testUtilityPrintCharArrayFull() {
        char[] testBoard = {'X','O','X','O','X','O','X','O','X'};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }
    
    @Test
    public void testUtilityPrintIntArray() {
        int[] testBoard = {1,2,3,4,5,6,7,8,9};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }
    
    @Test
    public void testUtilityPrintEmptyIntArray() {
        int[] testBoard = {0,0,0,0,0,0,0,0,0};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }
    
    @Test
    public void testUtilityPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        assertDoesNotThrow(() -> Utility.print(moves));
    }
    
    @Test
    public void testUtilityPrintEmptyArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        assertDoesNotThrow(() -> Utility.print(moves));
    }
    
    @Test
    public void testMiniMaxFirstMove() {
        int bestMove = game.MiniMax(game.board, game.player1);
        assertTrue(bestMove >= 1 && bestMove <= 9);
    }
    
    @Test
    public void testMiniMaxWithOneMove() {
        game.board[0] = 'X';
        int bestMove = game.MiniMax(game.board, game.player2);
        assertTrue(bestMove >= 1 && bestMove <= 9);
        assertNotEquals(1, bestMove);
    }
    
    @Test
    public void testMiniMaxWithTwoMoves() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        int bestMove = game.MiniMax(game.board, game.player1);
        assertTrue(bestMove >= 1 && bestMove <= 9);
    }
    
    @Test
    public void testMiniMaxWithThreeMoves() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.board[8] = 'X';
        int bestMove = game.MiniMax(game.board, game.player2);
        assertTrue(bestMove >= 1 && bestMove <= 9);
    }
    
    @Test
    public void testMinMove() {
        int value = game.MinMove(game.board, game.player1);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testMinMoveWithNearlyFullBoard() {
        char[] nearlyFull = {'X','O','X','O','X','O','X','O',' '};
        game.board = nearlyFull;
        int value = game.MinMove(game.board, game.player1);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testMaxMove() {
        int value = game.MaxMove(game.board, game.player2);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testMaxMoveWithNearlyFullBoard() {
        char[] nearlyFull = {'X','O','X','O','X','O','X','O',' '};
        game.board = nearlyFull;
        int value = game.MaxMove(game.board, game.player2);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testMinMoveReturnsWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'O';
        game.symbol = 'X';
        int value = game.MinMove(game.board, game.player2);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testMaxMoveReturnsWin() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.symbol = 'O';
        int value = game.MaxMove(game.board, game.player1);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }
    
    @Test
    public void testCheckStateNoWinAfterMultipleMoves() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }
    
    @Test
    public void testGameStateConstantValues() {
        assertEquals(0, State.PLAYING.ordinal());
        assertEquals(1, State.OWIN.ordinal());
        assertEquals(2, State.XWIN.ordinal());
        assertEquals(3, State.DRAW.ordinal());
    }
    
    @Test
    public void testGameINFConstant() {
        assertEquals(100, Game.INF);
    }
    
    @Test
    public void testNmoveInitialized() {
        assertEquals(0, game.nmove);
    }
    
    @Test
    public void testQCounterIncrements() {
        game.q = 0;
        game.MinMove(game.board, game.player1);
        assertTrue(game.q > 0);
    }
    
    @Test
    public void testSymbolChanges() {
        game.symbol = 'X';
        assertEquals('X', game.symbol);
        game.symbol = 'O';
        assertEquals('O', game.symbol);
    }
}
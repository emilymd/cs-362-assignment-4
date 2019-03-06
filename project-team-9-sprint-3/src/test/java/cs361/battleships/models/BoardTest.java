package cs361.battleships.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInvalidPlacement() {
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testPlaceMinesweeper() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', false));
    }

    @Test
    public void testPlaceBattleship() {
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 1, 'A', false));
    }

    @Test
    public void testPlaceDestroyer() {
        assertTrue(board.placeShip(new Ship("DESTROYER"), 1, 'A', false));
    }

    @Test
    public void testPlaceMinesweeperAtEdge10A() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 10, 'A', false));
    }

    @Test
    public void testPlaceMinesweeperAtEdge10I() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 10, 'I', false));
    }

    @Test
    public void testPlaceMinesweeperAtEdge1I() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'I', false));
    }

    @Test
    public void testPlaceMinesweeperVertically() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
    }

    @Test
    public void testPlaceBattleshipVertically() {
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 1, 'A', true));
    }

    @Test
    public void testPlaceDestroyerVertically() {
        assertTrue(board.placeShip(new Ship("DESTROYER"), 1, 'A', true));
    }


    //@Test
    //public void testAttackInvalidPlacement() {
    //    board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
    //    Result result = board.attack(11, 'A');
    //    assertEquals(AtackStatus.INVALID, result.getResult());
    //}

    @Test
    public void testAttackEmptySquare() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        Result result = board.attack(2, 'E');
        assertEquals(AtackStatus.MISS, result.getResult());
    }

    @Test
    public void testAttackShip() {
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);
        minesweeper = board.getShips().get(0);
        Result result = board.attack(2, 'A');
        assertEquals(AtackStatus.HIT, result.getResult());
        assertEquals(minesweeper, result.getShip());
    }

    @Test
    public void testAttackSameSquareMultipleTimes() {
        Ship minesweeper = new Ship("MINESWEEPER");
        board.placeShip(minesweeper, 1, 'A', true);
        board.attack(1, 'A');
        Result result = board.attack(1, 'A');
        assertEquals(AtackStatus.INVALID, result.getResult());
    }

    @Test
    public void testAttackSameEmptySquareMultipleTimes() {
        Result initialResult = board.attack(1, 'A');
        assertEquals(AtackStatus.MISS, initialResult.getResult());
        Result result = board.attack(1, 'A');
        assertEquals(AtackStatus.INVALID, result.getResult());
    }

    @Test
    public void testSurrender() {
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        var result = board.attack(1, 'A');
        board.attack(2, 'A');
        assertEquals(AtackStatus.SURRENDER, result.getResult());
    }

    @Test
    public void testPlaceMultipleMinesweepers() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 5, 'D', true));
    }

    @Test
    public void testPlaceMultipleBattleships() {
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 1, 'A', true));
        assertFalse(board.placeShip(new Ship("BATTLESHIP"), 5, 'D', true));
    }

    @Test
    public void testPlaceMultipleDestroyers() {
        assertTrue(board.placeShip(new Ship("DESTROYER"), 1, 'A', true));
        assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'D', true));
    }

    @Test
    public void testCantPlaceMoreThan3Ships() {
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true));
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 5, 'D', true));
        assertTrue(board.placeShip(new Ship("DESTROYER"), 6, 'A', false));
        assertFalse(board.placeShip(new Ship(""), 8, 'A', false));

    }

    @Test
    public void testSonarIsOccupiedTrue(){
        board.placeShip(new Ship("MINESWEEPER"), 1, 'A', true);
        assertTrue(board.sonarIsOccupied(1, 'A'));
        assertFalse(board.sonarIsOccupied(4, 'D'));
    }
    @Test
    public void testSonarIsInBounds(){
        assertTrue(board.sonarIsInBounds(4, 'B'));
        assertFalse(board.sonarIsInBounds(15, 'X'));
    }
    @Test
    public void testSonarPulse(){
        board.placeShip(new Ship("BATTLESHIP"), 4, 'D', false);
        board.placeShip(new Ship("MINESWEEPER"), 1, 'E', true);
        board.placeShip(new Ship("DESTROYER"), 6, 'G', false);
        assertTrue(board.sonarPulse(4, 'E'));
    }

}

package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testPlaceMinesweeperHorizontally() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 1, 'A');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testPlaceMinesweeperVertically() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 1, 'A');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testPlaceDestroyerHorizontally() {
        Ship minesweeper = new Ship("DESTROYER");
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        expected.add(new Square(1, 'C'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 1, 'B');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testPlaceDestroyerVertically() {
        Ship minesweeper = new Ship("DESTROYER");
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        expected.add(new Square(3, 'A'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 2, 'A');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testPlaceBattleshipHorizontally() {
        Ship minesweeper = new Ship("BATTLESHIP");
        minesweeper.place('A', 1, false);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(1, 'B'));
        expected.add(new Square(1, 'C'));
        expected.add(new Square(1, 'D'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 1, 'C');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testPlaceBattleshipVertically() {
        Ship minesweeper = new Ship("BATTLESHIP");
        minesweeper.place('A', 1, true);
        List<Square> occupiedSquares = minesweeper.getOccupiedSquares();
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(new Square(1, 'A'));
        expected.add(new Square(2, 'A'));
        expected.add(new Square(3, 'A'));
        expected.add(new Square(4, 'A'));
        assertEquals(expected, occupiedSquares);
        Square captain = new Square( 3, 'A');
        assertEquals(captain, minesweeper.getCaptainsQuarters());
    }

    @Test
    public void testShipOverlaps() {
        Ship minesweeper1 = new Ship("MINESWEEPER");
        minesweeper1.place('A', 1, true);

        Ship minesweeper2 = new Ship("MINESWEEPER");
        minesweeper2.place('A', 1, true);

        assertTrue(minesweeper1.overlaps(minesweeper2));
    }

    @Test
    public void testShipsDontOverlap() {
        Ship minesweeper1 = new Ship("MINESWEEPER");
        minesweeper1.place('A', 1, true);

        Ship minesweeper2 = new Ship("MINESWEEPER");
        minesweeper2.place('C', 2, true);

        assertFalse(minesweeper1.overlaps(minesweeper2));
    }

    @Test
    public void testMinesweeperIsAtLocation() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);

        assertTrue(minesweeper.isAtLocation(new Square(1, 'A')));
        assertTrue(minesweeper.isAtLocation(new Square(2, 'A')));
    }

    //NEW by Emily
    @Test
    public void testDestroyerIsAtLocation(){
        Ship destroyer = new Ship("DESTROYER");
        destroyer.place('A',1,false);

        assertTrue(destroyer.isAtLocation(new Square(1,'A')));
        assertTrue(destroyer.isAtLocation(new Square(1,'B')));
        assertTrue(destroyer.isAtLocation(new Square(1,'C')));
    }

    @Test
    public void testBattleshipIsAtLocation(){
        Ship battleship = new Ship("BATTLESHIP");
        battleship.place('A',1,false);

        assertTrue(battleship.isAtLocation(new Square(1,'A')));
        assertTrue(battleship.isAtLocation(new Square(1,'B')));
        assertTrue(battleship.isAtLocation(new Square(1,'C')));
        assertTrue(battleship.isAtLocation(new Square(1,'D')));
    }

    @Test
    public void testMinesweeperIsNotAtLocation(){
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A',1,true);

        assertFalse(minesweeper.isAtLocation(new Square(3,'B')));
        assertFalse(minesweeper.isAtLocation(new Square(4,'B')));
    }

    @Test
    public void testDestroyerIsNotAtLocation(){
        Ship destroyer = new Ship("DESTROYER");
        destroyer.place('A',1,true);

        assertFalse(destroyer.isAtLocation(new Square(3,'B')));
        assertFalse(destroyer.isAtLocation(new Square(4,'B')));
        assertFalse(destroyer.isAtLocation(new Square(5,'B')));
    }

    @Test
    public void testBattleshipIsNotAtLocation(){
        Ship battleship = new Ship("BATTLESHIP");
        battleship.place('A',1,true);

        assertFalse(battleship.isAtLocation(new Square(3,'B')));
        assertFalse(battleship.isAtLocation(new Square(4,'B')));
        assertFalse(battleship.isAtLocation(new Square(5,'B')));
        assertFalse(battleship.isAtLocation(new Square(6,'B')));
    }

    @Test
    public void testMinesweeperMiss(){
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A',1,true);

        Result result = minesweeper.attack(1,'B');

        assertEquals(AttackStatus.MISS,result.getResult());
        assertNotEquals(minesweeper, result.getShip());
        assertEquals(new Square(1,'B'),result.getLocation());
    }

    @Test
    public void testDestroyerMiss(){
        Ship destroyer = new Ship("DESTROYER");
        destroyer.place('A',1,false);

        Result result = destroyer.attack(5,'A');

        assertEquals(AttackStatus.MISS,result.getResult());
        assertNotEquals(destroyer, result.getShip());
        assertEquals(new Square(5,'A'),result.getLocation());
    }

    @Test
    public void testBattleshipMiss(){
        Ship battleship = new Ship("BATTLESHIP");
        battleship.place('A',1,true);

        Result result = battleship.attack(1,'B');

        assertEquals(AttackStatus.MISS,result.getResult());
        assertNotEquals(battleship, result.getShip());
        assertEquals(new Square(1,'B'),result.getLocation());
    }

    @Test
    public void testMinesweeperHit() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);

        Result result = minesweeper.attack(2, 'A');
        assertEquals(AttackStatus.HIT, result.getResult());
        assertEquals(minesweeper, result.getShip());
        assertEquals(new Square(2, 'A'), result.getLocation());
    }

    @Test
    public void testDestroyerHit() {
        Ship destroyer = new Ship("DESTROYER");
        destroyer.place('A', 1, true);

        Result result = destroyer.attack(1, 'A');
        assertEquals(AttackStatus.HIT, result.getResult());
        assertEquals(destroyer, result.getShip());
        assertEquals(new Square(1, 'A'), result.getLocation());
    }

    @Test
    public void testBattleshipCapHit() {
        Ship battleship = new Ship("BATTLESHIP");
        battleship.place('A', 1, true);
        var result = battleship.attack(3, 'A');
        assertEquals(AttackStatus.HIDDEN, result.getResult());
    }

    @Test
    public void testMinesweeperCapSunk () {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);
        //minesweeper.attack(3,'A');
        var result = minesweeper.attack(1,'A');
        assertEquals(AttackStatus.SUNK, result.getResult());
    }

    @Test
    public void testMinesweeperNotHidden(){
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A',1,true);
        var result = minesweeper.attack(1,'A');
        assertNotEquals(AttackStatus.HIDDEN,result.getResult());
    }

    //End of NEW by Emily

    @Test
    public void testHit() {
        Ship minesweeper = new Ship("BATTLESHIP");
        minesweeper.place('A', 1, true);

        Result result = minesweeper.attack(1, 'A');
        assertEquals(AttackStatus.HIT, result.getResult());
        assertEquals(minesweeper, result.getShip());
        assertEquals(new Square(1, 'A'), result.getLocation());
    }

    @Test
    public void testSink() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);

        Result result = minesweeper.attack(1, 'A');
        minesweeper.attack(2, 'A');

        assertEquals(AttackStatus.SUNK, result.getResult());
        assertEquals(minesweeper, result.getShip());
        assertEquals(new Square(1, 'A'), result.getLocation());
    }

    @Test
    public void testOverlapsBug() {
        Ship minesweeper = new Ship("MINESWEEPER");
        Ship destroyer = new Ship("DESTROYER");
        minesweeper.place('C', 5, false);
        destroyer.place('C', 5, false);
        assertTrue(minesweeper.overlaps(destroyer));
    }

    @Test
    public void testAttackSameSquareTwice() {
        Ship minesweeper = new Ship("MINESWEEPER");
        minesweeper.place('A', 1, true);
        var result = minesweeper.attack(2, 'A');
        assertEquals(AttackStatus.HIT, result.getResult());
        result = minesweeper.attack(2, 'A');
        assertEquals(AttackStatus.INVALID, result.getResult());
    }

    @Test
    public void testEquals() {
        Ship minesweeper1 = new Ship("MINESWEEPER");
        minesweeper1.place('A', 1, true);
        Ship minesweeper2 = new Ship("MINESWEEPER");
        minesweeper2.place('A', 1, true);
        assertTrue(minesweeper1.equals(minesweeper2));
        assertEquals(minesweeper1.hashCode(), minesweeper2.hashCode());
    }

    @Test
    public void testCapHit() {
        Ship destroyer = new Ship("DESTROYER");
        destroyer.place('A', 1, true);
        var result = destroyer.attack(2, 'A');
        assertEquals(AttackStatus.HIDDEN, result.getResult());
    }

    @Test
    public void testCapSunk () {
        Ship battleship = new Ship("BATTLESHIP");
        battleship.place('A', 1, true);
        battleship.attack(3,'A');
        var result = battleship.attack(3,'A');
        assertEquals(AttackStatus.SUNK, result.getResult());
    }
}

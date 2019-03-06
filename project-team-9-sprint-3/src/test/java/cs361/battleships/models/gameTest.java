package cs361.battleships.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class gameTest {

    private Game game;

    @Before
    public void setUp(){ game = new Game(); }

    //TESTING PLACE SHIP

    @Test
    public void testBadShipPlacementXVerticalMineSweeper() {
        Ship ship = new Ship("MINESWEEPER");

        assertFalse(game.placeShip(ship, 15, 'A', true));
    }

    @Test
    public void testBadShipPlacementXHorizontalMineSweeper() {
        Ship ship = new Ship("MINESWEEPER");

        assertFalse(game.placeShip(ship, 15, 'A', false));
    }



    @Test
    public void testBadShipPlacementYHorizontalMineSweeper() {
        Ship ship = new Ship("MINESWEEPER");

        assertFalse(game.placeShip(ship, 5, 'X', false));
    }

    @Test
    public void testBadShipPlacementYVerticalMineSweeper() {
        Ship ship = new Ship("MINESWEEPER");

        assertFalse(game.placeShip(ship, 5, 'X', true));
    }

    @Test
    public void testGoodShipPlacementVerticalMineSweeper(){
        Ship ship = new Ship("MINESWEEPER");

        assertTrue(game.placeShip(ship, 5, 'D', true));

    }

    @Test
    public void testGoodShipPlacementHorizontalMineSweeper(){
        Ship ship = new Ship("MINESWEEPER");

        assertTrue(game.placeShip(ship, 5, 'D', false));

    }

    @Test
    public void testBadShipPlacementXVerticalDESTROYER() {
        Ship ship = new Ship("DESTROYER");

        assertFalse(game.placeShip(ship, 15, 'A', true));
    }

    @Test
    public void testBadShipPlacementXHorizontalDESTROYER() {
        Ship ship = new Ship("DESTROYER");

        assertFalse(game.placeShip(ship, 15, 'A', false));
    }

    @Test
    public void testBadShipPlacementYHorizontalDESTROYER() {
        Ship ship = new Ship("DESTROYER");

        assertFalse(game.placeShip(ship, 5, 'X', false));
    }

    @Test
    public void testBadShipPlacementYVerticalDESTROYER() {
        Ship ship = new Ship("DESTROYER");

        assertFalse(game.placeShip(ship, 5, 'X', true));
    }

    @Test
    public void testGoodShipPlacementVerticalDESTROYER(){
        Ship ship = new Ship("DESTROYER");

        assertTrue(game.placeShip(ship, 5, 'D', true));

    }

    @Test
    public void testGoodShipPlacementHorizontalDESTROYER(){
        Ship ship = new Ship("DESTROYER");

        assertTrue(game.placeShip(ship, 5, 'D', false));

    }

    @Test
    public void testBadShipPlacementXVerticalBATTLESHIP() {
        Ship ship = new Ship("BATTLESHIP");

        assertFalse(game.placeShip(ship, 15, 'A', true));
    }

    @Test
    public void testBadShipPlacementXHorizontalBATTLESHIP() {
        Ship ship = new Ship("BATTLESHIP");

        assertFalse(game.placeShip(ship, 15, 'A', false));
    }

    @Test
    public void testBadShipPlacementYHorizontalBATTLESHIP() {
        Ship ship = new Ship("BATTLESHIP");

        assertFalse(game.placeShip(ship, 5, 'X', false));
    }

    @Test
    public void testBadShipPlacementYVerticalBATTLESHIP() {
        Ship ship = new Ship("BATTLESHIP");

        assertFalse(game.placeShip(ship, 5, 'X', true));
    }

    @Test
    public void testGoodShipPlacementVerticalBATTLESHIP(){
        Ship ship = new Ship("BATTLESHIP");

        assertTrue(game.placeShip(ship, 5, 'D', true));

    }

    @Test
    public void testGoodShipPlacementHorizontalBATTLESHIP(){
        Ship ship = new Ship("BATTLESHIP");

        assertTrue(game.placeShip(ship, 5, 'D', false));

    }
}

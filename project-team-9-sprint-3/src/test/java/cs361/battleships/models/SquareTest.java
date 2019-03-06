package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SquareTest {

    //Assert getters/setters working

    @Test
    public void testGoodGetCol(){
        Square s = new Square(5, 'G');
        char col = s.getColumn();

        assertTrue(col == 'G');

    }

    @Test
    public void testBadGetCol(){
        Square s = new Square(5, 'G');
        char col = s.getColumn();

        assertFalse( col == 'A');
    }


    @Test
    public void testGoodGetRow(){
        Square s = new Square(7, 'F');
        int row = s.getRow();

        assertTrue(row == 7);
    }

    @Test
    public void testBadGetRow(){
        Square s = new Square(7, 'F');
        int row = s.getRow();

        assertFalse(row == 2);

    }

    //Test equals override function
    @Test
    public void testEqualsTrue(){
        Square s = new Square(3, 'G');

        assertTrue(s.equals(s));

    }

    @Test
    public void testEqualsFalse(){
        Square s = new Square(3, 'G');
        Square t = new Square(4, 'B');

        assertFalse(s.equals(t));

    }

    //testing bounds

    @Test
    public void testOutOfBoundsTrueRow(){
        Square s = new Square(11, 'G');

        assertTrue(s.isOutOfBounds());

    }

    @Test
    public void testOutOfBoundsTrueCol(){
        Square s = new Square(3, 'K');

        assertTrue(s.isOutOfBounds());

    }

    @Test
    public void testOutOfBoundsFalse(){
        Square s = new Square(3, 'G');

        assertFalse(s.isOutOfBounds());

    }

    //Test hits

    @Test
    public void testIsHitFalse(){
        Square s = new Square(3, 'H');

        assertFalse(s.isHit());

    }

    @Test
    public void testIsHitTrue(){
        Square s = new Square(3, 'H');
        s.hit();

        assertTrue(s.isHit());

    }

    //Test Captain's quarters

}

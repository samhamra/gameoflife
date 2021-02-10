package gameoflife;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Arrays;


/**
 * Unit test for simple App.
 */
public class ModelTest {

    private Model model;

    private void setUpAllDead() {
        model = new Model();
    }

    private void setUpAllAlive() {
        boolean[][] seed = new boolean[30][30];
        for (boolean[] row : seed) {
            Arrays.fill(row, true);
        }
        model = new Model(30,30, seed);
    }


    @Test
    public void testLivingNeighboursInCenter() {
        setUpAllAlive();
        int livingNeighbours = model.calculateLivingNeighbours(5,5);
        assertEquals(livingNeighbours, 8, "There should be 8 living neighbours in the center");
    }

    @Test
    public void testLivingNeighboursInCorners() {
        setUpAllAlive();
        int livingNeighboursTopLeftCorner = model.calculateLivingNeighbours(0,0);
        int livingNeighboursTopRightCorner = model.calculateLivingNeighbours(0,29);
        int livingNeighboursBottomLeftCorner = model.calculateLivingNeighbours(29,0);
        int livingNeighboursBottomRightCorner = model.calculateLivingNeighbours(29,29);
        assertEquals(livingNeighboursTopLeftCorner, 3, "There should be 3 living neighbours in the top left corner");
        assertEquals(livingNeighboursTopRightCorner, 3, "There should be 3 living neighbours in the top right corner");
        assertEquals(livingNeighboursBottomLeftCorner, 3, "There should be 3 living neighbours in the top right corner");
        assertEquals(livingNeighboursBottomRightCorner, 3, "There should be 3 living neighbours in the top right corner");
    }

    @Test
    public void testNoLivingNeighbours() {
        setUpAllDead();
        int livingNeighboursInCenter = model.calculateLivingNeighbours(3,3);
        int livingNeighboursInCorner = model.calculateLivingNeighbours(29,29);
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the center");
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours the corner");
    }

    @Test
    public void testNextGenerationWithAllDead() {
        setUpAllDead();
        model.nextGeneration();
        int livingNeighboursInCenter = model.calculateLivingNeighbours(3,3);
        int livingNeighboursInCorner = model.calculateLivingNeighbours(29,29);
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the center");
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours the corner");
    }

    @Test
    public void testNextGenerationWithAllAlive() {
        setUpAllAlive();
        model.nextGeneration();
        int livingNeighboursInCorner = model.calculateLivingNeighbours(0,0);
        int livingNeighboursInCenter = model.calculateLivingNeighbours(5,10);
        boolean isCornerAlive = model.getValueAtPos(0,0);
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours in the corner");
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the corner");
        assertEquals(isCornerAlive, true, "The corner cell should still be alive" );

    }


}

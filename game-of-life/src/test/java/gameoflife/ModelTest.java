package gameoflife;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class ModelTest {
    private Model model;

    private void setUpAllDead() {
        model = new Model();
    }

    private void setUpAllAlive() {
        boolean[][] seed = new boolean[Model.DEFAULT_ROWS][Model.DEFAULT_COLUMNS];
        for (boolean[] row : seed) {
            Arrays.fill(row, true);
        }
        model = new Model(Model.DEFAULT_ROWS, Model.DEFAULT_COLUMNS, seed);
    }

    @Test
    public void calculateLivingNeighbours_ShouldBe8ForAllInCenter_WhenAllAlive() {
        setUpAllAlive();
        int livingNeighbours = model.calculateLivingNeighbours(5,5);
        assertEquals(livingNeighbours, 8, "There should be 8 living neighbours in the center");
    }

    @Test
    public void calculateLivingNeighbours_ShouldReturn3ForAllCorners_WhenAllAlive() {
        setUpAllAlive();
        int maxRows = model.getRows();
        int maxColumns = model.getColumns();
        int livingNeighboursTopLeftCorner = model.calculateLivingNeighbours(0,0);
        int livingNeighboursTopRightCorner = model.calculateLivingNeighbours(0, maxColumns-1);
        int livingNeighboursBottomLeftCorner = model.calculateLivingNeighbours(maxRows-1,0);
        int livingNeighboursBottomRightCorner = model.calculateLivingNeighbours(maxRows-1,maxColumns-1);
        assertEquals(livingNeighboursTopLeftCorner, 3, "There should be 3 living neighbours in the top left corner");
        assertEquals(livingNeighboursTopRightCorner, 3, "There should be 3 living neighbours in the top right corner");
        assertEquals(livingNeighboursBottomLeftCorner, 3, "There should be 3 living neighbours in the top right corner");
        assertEquals(livingNeighboursBottomRightCorner, 3, "There should be 3 living neighbours in the top right corner");
    }

    @Test
    public void calculateLivingNeighbours_ShouldBeNone_WhenAllDead() {
        setUpAllDead();
        int livingNeighboursInCenter = model.calculateLivingNeighbours(3,3);
        int livingNeighboursInCorner = model.calculateLivingNeighbours(model.getRows()-1,model.getColumns()-1);
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the center");
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours the corner");
    }

    @Test
    public void nextGeneration_ShouldNotImpactState_WhenAllDead() {
        setUpAllDead();
        model.nextGeneration();
        int livingNeighboursInCenter = model.calculateLivingNeighbours(3,3);
        int livingNeighboursInCorner = model.calculateLivingNeighbours(model.getRows()-1,model.getColumns()-1);
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the center");
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours the corner");
    }

    @Test
    public void nextGeneration_ShouldImpactState_WhenAllAlive() {
        setUpAllAlive();
        model.nextGeneration();
        int livingNeighboursInCorner = model.calculateLivingNeighbours(0,0);
        int livingNeighboursInCenter = model.calculateLivingNeighbours(5,10);
        boolean isCornerAlive = model.getValueAtPos(0,0);
        assertEquals(livingNeighboursInCorner, 0, "There should be no living neighbours in the corner");
        assertEquals(livingNeighboursInCenter, 0, "There should be no living neighbours in the corner");
        assertEquals(isCornerAlive, true, "The corner cell should still be alive" );
    }

    @Test
    public void toggleValueAtPos_ShouldOnlyChangeValueAtPos() {
        setUpAllDead();
        assertEquals(model.getValueAtPos(0, 0), false, "The top left cell should still be dead");
        model.toggleValueAtPos(0, 0);
        assertEquals(model.getValueAtPos(0, 0), true, "The top left cell should now be alive");
        assertEquals(model.getValueAtPos(0,1), false, "Other cells should still be dead");
    }
}

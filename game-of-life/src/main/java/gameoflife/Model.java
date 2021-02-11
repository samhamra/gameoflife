package gameoflife;

public class Model {
    public final static int DEFAULT_ROWS = 30;
    public final static int DEFAULT_COLUMNS = 60;
    private final int rows;
    private final int columns;
    private boolean[][] currentGeneration;

    public Model(int rows, int columns, boolean[][] seed) {
        this.rows = rows;
        this.columns = columns;
        currentGeneration = seed;
    }

    public Model(int rows, int columns) {
        this(rows, columns, new boolean[rows][columns]);
    }

    public Model() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    //Any live cell with two or three live neighbours survives.
    //Any dead cell with three live neighbours becomes a live cell.
    //All other live cells die in the next generation. Similarly, all other dead cells stay dead
    //https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
    //i.e live cells with 0, 1, 4-8 neighbours die in the next generation
    //dead cells with 3 live neighbours becomes a live cell in the next generation
    //All other cells stay unchanged
     public void nextGeneration() {
        boolean[][] nextGeneration = new boolean[rows][columns];
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                int livingNeighbours = calculateLivingNeighbours(i, j);
                if(currentGeneration[i][j] && livingNeighbours!= 2 && livingNeighbours!=3) {
                    nextGeneration[i][j] = false;
                } else if(!currentGeneration[i][j] && livingNeighbours == 3) {
                    nextGeneration[i][j] = true;
                } else{
                    nextGeneration[i][j] = currentGeneration[i][j];
                }
            }
        }
        currentGeneration = nextGeneration;
    }

    int calculateLivingNeighbours(int row, int column) {
        int livingNeighbours = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) continue;
                if(isInBoundsAndAlive(row+i, column+j)) {
                    livingNeighbours++;
                }
            }
        }
        return livingNeighbours;
    }

    private boolean isInBoundsAndAlive(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns && currentGeneration[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean getValueAtPos(int row, int column) {
        return currentGeneration[row][column];
    }

    public void toggleValueAtPos(int row, int column) {
        currentGeneration[row][column] = !currentGeneration[row][column];
    }
}
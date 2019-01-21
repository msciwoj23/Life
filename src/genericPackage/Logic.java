package genericPackage;

public class Logic {

    private final int BOARD_SIZE = 50;
    private final int CUBE_SIDE = 12;


    private boolean[][] mainBoard = new boolean[BOARD_SIZE][BOARD_SIZE];


    public boolean[][] getFirstGneration() {
        int[][] input = {{0, 1}, {1, 1}, {2, 1},
                         {20, 20}, {19, 20}, {21, 20}, {20, 19}, {19, 21},
                         {47, 48}, {48, 48}, {49, 48}};

        for (int[] pair: input) {
            mainBoard[pair[0]][pair[1]] = true;
        }
        return mainBoard;
    }

    public int incrementOrNot(int rowNum,
                              int cellNum,
                              int firstIndex,
                              int secondIndex,
                              int currentCount) {

        int livingNeighboursCount = currentCount;
        if (firstIndex < 0 || firstIndex >= BOARD_SIZE || secondIndex < 0 || secondIndex >= BOARD_SIZE ) {
            return livingNeighboursCount;
        } else if (!(firstIndex == rowNum && secondIndex == cellNum) && mainBoard[firstIndex][secondIndex]) {
            livingNeighboursCount++;
        }
        return livingNeighboursCount;
    }

    public int getLivingNeighbours(int rowNum, int cellNum) {

        int livingNeighboursCount = 0;

        int cellBefore = cellNum - 1;
        int cellAfter = cellNum + 1;
        int rowAbove = rowNum - 1;
        int rowBelow = rowNum + 1;

        for (int i = rowAbove; i <= rowBelow; i++) {
            for (int j = cellBefore; j <= cellAfter ; j++) {
                livingNeighboursCount = incrementOrNot(rowNum, cellNum, i, j, livingNeighboursCount);
            }
        }
        return livingNeighboursCount;
    }

    public boolean decideLife(boolean alive, int livingNeighboursCount) {

        int NEIGHBOURS_TO_BE_BORN = 3;
        int MIN_NEIGHBOURS_TO_LIVE = 2;
        int MAX_NEIGHBOURS_TO_LIVE = 3;

        if (!alive && livingNeighboursCount == NEIGHBOURS_TO_BE_BORN) {
            return true;
        } else  {
            return (alive
                    && livingNeighboursCount >= MIN_NEIGHBOURS_TO_LIVE
                    && livingNeighboursCount <= MAX_NEIGHBOURS_TO_LIVE);
        }
    }

    public boolean[][] getNextGeneration() {

        boolean[][] tempBoard = new boolean[BOARD_SIZE][BOARD_SIZE];

        for (int rowNum = 0; rowNum < mainBoard.length; rowNum++) {
            for (int cellNum = 0; cellNum < mainBoard[rowNum].length ; cellNum++) {
                int livingNeighboursCount = getLivingNeighbours(rowNum,cellNum);
                tempBoard[rowNum][cellNum] = decideLife(mainBoard[rowNum][cellNum], livingNeighboursCount);
            }
        }
        this.mainBoard = tempBoard;
        return mainBoard;
    }

    boolean[][] getNextGeneration(boolean shouldErase) {
        if (shouldErase) {
            this.mainBoard = new boolean[BOARD_SIZE][BOARD_SIZE];
            return mainBoard;
        } else {
            return getNextGeneration();
        }
    }

    boolean[][] getNextGeneration(double x, double y) {

        int coordXtoToggle = (int) x / CUBE_SIDE;
        int coordYtoToggle = (int) y / CUBE_SIDE;
        mainBoard[coordYtoToggle][coordXtoToggle] = !mainBoard[coordYtoToggle][coordXtoToggle];
        return mainBoard;
    }

    void setMainBoard(boolean[][] mainBoard) {
        this.mainBoard = mainBoard;
    }
}

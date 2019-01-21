package genericPackage;

public class Memory {

    private Controller controller;

    private final int BOARD_SIZE = 50;
    private final int BOARD_HISTORY_SIZE = 100;

    private int writePointer = 1;
    private int readPointer = 0;

    private boolean[][][] boardHistory = new boolean[BOARD_HISTORY_SIZE][BOARD_SIZE][BOARD_SIZE];

    Memory(Controller controller) {
        this.controller = controller;
    }

    void addToHistory(boolean[][] boardToAdd) {
        boardHistory[writePointer] = boardToAdd;
        writePointer++;
        if (writePointer == BOARD_HISTORY_SIZE) {
            writePointer = 0;
        }
    }

    boolean[][] getOldBoard(int step) {
        int pastPointer = writePointer - step - readPointer;
        if (pastPointer < 0) {
            pastPointer = BOARD_HISTORY_SIZE + pastPointer;
        }

        boolean[][] boardToReturn = boardHistory[pastPointer];
        writePointer--;
        return boardToReturn;
    }

    public void resetReadPointer() {
        readPointer = writePointer;
    }
}

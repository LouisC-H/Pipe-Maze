import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.round;

public class PipeMap {

    private int rowCount;
    private int colCount;
    private Pipe[][] pipeMatrix;

    private Pipe sPipe;

    private boolean recursionBroken = false;
    private Pipe recursionStorePipe;
    private char recursionStoreDirection;
    private int numRecursionBreaks;

    public PipeMap(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.pipeMatrix = new Pipe[rowCount][colCount];
    }

    public void populateMatrix(String charRow, int rowIndex){
        for (int i = 0; i < charRow.length(); i++) {
            char c = charRow.charAt(i);
            int[] position = new int[]{rowIndex, i};
            Pipe newPipe = new Pipe(c, position, this);
            if (rowIndex != 0){
                newPipe.uNeighbour(pipeMatrix[rowIndex - 1][i]);
            }
            if (i != 0){
                newPipe.lNeighbour(pipeMatrix[rowIndex][i -1]);
            }
            if (c == 'S'){
                sPipe = newPipe;
            }
            pipeMatrix[rowIndex][i] = newPipe;
        }
    }

    public int probePipeS() {
        System.out.println(Arrays.toString(sPipe.getPosition()));
        int numStepsThroughPipe = 0;
        // Up
        if (sPipe.getUConnection() != null){
            numStepsThroughPipe = manageRecursion('u');
            if (numStepsThroughPipe != 0 ){
                return round(numStepsThroughPipe /2);
            }
        }
        // Down
        if (sPipe.getDConnection() != null){
            numStepsThroughPipe = manageRecursion('d');
            if (numStepsThroughPipe != 0 ){
                return round(numStepsThroughPipe /2);
            }
        }

        // Left
        if (sPipe.getLConnection() != null) {
            numStepsThroughPipe = manageRecursion('l');
            if (numStepsThroughPipe != 0) {
                return round(numStepsThroughPipe / 2);
            }
        }

        // No need for right: the pipes for a full loop, so only 3 out of the 4 directions need to be tested.
        return numStepsThroughPipe;
    }

    private int manageRecursion(char direction) {
        int recursionEnd = 0;
        switch (direction) {
            case 'u' -> recursionEnd = sPipe.getUConnection().moveThroughPipes(direction, 0);
            case 'd' -> recursionEnd = sPipe.getDConnection().moveThroughPipes(direction, 0);
            case 'l' -> recursionEnd = sPipe.getLConnection().moveThroughPipes(direction, 0);
            case 'r' -> recursionEnd = sPipe.getRConnection().moveThroughPipes(direction, 0);
            default -> System.out.println("Direction not recognised");
        }
        // while loop allows the recursion to break and be re-launched
        while (recursionBroken){
            recursionBroken = false;
            recursionEnd = recursionStorePipe.moveThroughPipes(recursionStoreDirection, -1);
        }
        System.out.println(recursionEnd);
        System.out.println( numRecursionBreaks);
        return recursionEnd + 5000 * numRecursionBreaks;
    }


    public void breakingRecursion(Pipe pipe, char direction){
        this.recursionBroken = true;
        this.recursionStorePipe = pipe;
        this.recursionStoreDirection = direction;
//        System.out.println(Arrays.toString(pipe.getPosition()));
        numRecursionBreaks++;
    }
}

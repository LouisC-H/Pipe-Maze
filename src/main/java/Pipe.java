import java.lang.reflect.Array;
import java.util.Arrays;

public class Pipe {

    private int[] position;
    private PipeMap pipeMap;

    private boolean isS = false;

    private boolean uValid = false;
    private boolean dValid = false;
    private boolean lValid = false;
    private boolean rValid = false;

    private Pipe uConnection;
    private Pipe dConnection;
    private Pipe lConnection;
    private Pipe rConnection;

    public Pipe(char c, int[] position, PipeMap pipeMap) {
        this.position = position;
        this.pipeMap = pipeMap;
        switch (c) {
            case 'S':
                initialiseS();
                break;
            case '|':
                initialiseVert();
                break;
            case '-':
                initialiseHor();
                break;
            case 'L':
                initialiseL();
                break;
            case 'J':
                initialiseJ();
                break;
            case '7':
                initialise7();
                break;
            case 'F':
                initialiseF();
                break;
            case '.':
                break;
            default:
                System.out.println("Error, character not recognised : " + c);

        }

    }

    private void initialiseS() {
        isS = true;
        uValid = true;
        dValid = true;
        lValid = true;
        rValid = true;
    }

    private void initialiseVert() {
        uValid = true;
        dValid = true;
    }

    private void initialiseHor() {
        lValid = true;
        rValid = true;
    }

    private void initialiseL() {
        uValid = true;
        rValid = true;
    }

    private void initialiseJ() {
        uValid = true;
        lValid = true;
    }

    private void initialise7() {
        dValid = true;
        lValid = true;
    }

    private void initialiseF() {
        dValid = true;
        rValid = true;
    }

    public void uNeighbour(Pipe uNeighbour) {
        if (uValid && uNeighbour.dValid){
            this.uConnection = uNeighbour;
            uNeighbour.dConnection = this;
        }
    }

    public void lNeighbour(Pipe lNeighbour) {
        if (lValid && lNeighbour.rValid){
            this.lConnection = lNeighbour;
            lNeighbour.rConnection = this;
        }
    }

    //Note: direction is the direction moved from to get to this pipe
    public int moveThroughPipes(char direction, int i) {
        System.out.println(Arrays.toString(position));
        i++;
        if (!isS){
            if (i == 5000){
                pipeMap.breakingRecursion(this, direction);
                return i;
            }
            else{
                switch (direction) {
                    case 'u':
                        if (getUConnection() != null){
                            return getUConnection().moveThroughPipes('u', i);
                        } else if (getLConnection() != null){
                            return getLConnection().moveThroughPipes('l', i);
                        } else if (getRConnection() != null){
                            return getRConnection().moveThroughPipes('r', i);
                        } else {
                            return 0;
                        }
                    case 'd':
                        if (getDConnection() != null){
                            return getDConnection().moveThroughPipes('d', i);
                        } else if (getLConnection() != null){
                            return getLConnection().moveThroughPipes('l', i);
                        } else if (getRConnection() != null){
                            return getRConnection().moveThroughPipes('r', i);
                        } else {
                            return 0;
                        }
                    case 'l':
                        if (getUConnection() != null){
                            return getUConnection().moveThroughPipes('u', i);
                        } else if (getDConnection() != null){
                            return getDConnection().moveThroughPipes('d', i);
                        } else if (getLConnection() != null){
                            return getLConnection().moveThroughPipes('l', i);
                        } else {
                            return 0;
                        }
                    case 'r':
                        if (getUConnection() != null){
                            return getUConnection().moveThroughPipes('u', i);
                        } else if (getDConnection() != null){
                            return getDConnection().moveThroughPipes('d', i);
                        } else if (getRConnection() != null){
                            return getRConnection().moveThroughPipes('r', i);
                        } else {
                            return 0;
                        }
                    default:
                        System.out.println("Direction not recognised");
                        return 0;
                }
            }
        } else {
            return i;
        }
    }

    //Getters:
    public boolean isS() {
        return isS;
    }

    public Pipe getUConnection() {
        return uConnection;
    }

    public Pipe getDConnection() {
        return dConnection;
    }

    public Pipe getLConnection() {
        return lConnection;
    }

    public Pipe getRConnection() {
        return rConnection;
    }

    public int[] getPosition(){
        return position;
    }
}






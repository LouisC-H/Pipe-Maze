import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        String filename = "src/main/resources/input.txt";
//        System.out.println(runDay10CodeP1(filename));
        System.out.println(runDay10CodeP2(filename));

    }

    public static int runDay10CodeP1(String filename){

        File inputFile = new File(filename);

        PipeMap pipeMap = initialisePipeMap(inputFile);

        populatePipeMap(inputFile, pipeMap);

        return pipeMap.probePipeS();

    }

    public static PipeMap initialisePipeMap(File inputFile){

        int rowCount = 0, charCount = 0;

        try {
            Scanner lineCounter = new Scanner(inputFile);

            charCount = lineCounter.nextLine().length();
            rowCount ++;

            while (lineCounter.hasNextLine()) {
                rowCount++;
                lineCounter.nextLine();
            }
            lineCounter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new PipeMap(rowCount, charCount);

    }

    private static void populatePipeMap(File inputFile, PipeMap pipeMap){
        try {
            Scanner myReader = new Scanner(inputFile);
            int rowCount = 0;

            while (myReader.hasNextLine()) {
                String charString = myReader.nextLine();
                pipeMap.populateMatrix(charString, rowCount);
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int runDay10CodeP2(String filename){

        File inputFile = new File(filename);

        PipeMap pipeMap = initialisePipeMap(inputFile);

        populatePipeMap(inputFile, pipeMap);

        pipeMap.probePipeS();

        return pipeMap.getInsideArea();

    }
}

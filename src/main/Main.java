package main;

import checker.Checker;
import common.Constants;
import database.Database;
import database.Parser;
import fileio.Input;
import fileio.InputLoader;
import solver.Solver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        createOutput();
        Checker.calculateScore();
    }

    /**
     * This method creates the output directory and both solves the tasks and writes the result.
     */
    public static void createOutput() throws IOException {
        // directory -> ./tests
        File directory = new File(Constants.TESTS_PATH);

        // Creates output directory if it does not exist already.
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
           Files.createDirectories(path);
        }

        // Solve every testX.json and write the result in out_X.json.
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            // Get that "X" from testX.json in testIndex.
            String test = "test";
            String testIndex = file.getName().substring(test.length());
            String filepath = Constants.OUTPUT_PATH + testIndex;
            action(file.getAbsolutePath(), filepath);
        }
    }

    /**
     * This method solves the test found at filePath1 and writes the output at filePath2.
     * @param filePath1
     *      -> filePath1 = input path
     * @param filePath2
     *      -> filePath2 = output path
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Parser parser = new Parser();
        parser.buildDatabase(input);

        Solver solver = new Solver(filePath2);
        solver.solve();

        // Reset the database between tests.
        Database.getDatabase().resetDatabase();
    }
}

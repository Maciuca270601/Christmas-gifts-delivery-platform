package solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import strategy.SortById;
import write.SaveAnnualArray;
import write.SaveSimulationArrays;
import java.io.FileWriter;
import java.io.IOException;

public final class Solver {
    private final SaveSimulationArrays saveSimulationArrays;
    private final String path;

    public Solver(final String path) {
        this.path = path;
        this.saveSimulationArrays = new SaveSimulationArrays();
    }

    /**
     * This method solves the simulation and saves the result in two different objects.
     * -> SaveAnnualArray: a class that stores an array of children for each year.
     * -> SaveSimulationArrays: a class that stores an array of SaveAnnualArray objects.
     */
    public void solve() throws IOException {
        // Solve first year.
        SolveFirstYear solveFirst = new SolveFirstYear();
        solveFirst.solve();

        // Save the result of the first year of simulation.
        SaveAnnualArray saveAnnualArray = new SaveAnnualArray();
        SortById sortId = new SortById();
        Database.getDatabase().sortListOfChildren(sortId);
        saveAnnualArray.addChildren(Database.getDatabase().getListOfChildren());
        this.saveSimulationArrays.addArray(saveAnnualArray);

        // Solve next years.
        SolveNextYears solveNext = new SolveNextYears();
        for (int currYear = 1; currYear <= Database.getDatabase().getNumberOfYears(); currYear++) {
            solveNext.solve(currYear);

            // Save the result of the next years of simulation.
            SaveAnnualArray save = new SaveAnnualArray();
            Database.getDatabase().sortListOfChildren(sortId);
            save.addChildren(Database.getDatabase().getListOfChildren());
            this.saveSimulationArrays.addArray(save);
        }

        // Write the output
        write();
    }

    /**
     * This method writes the saveSimulationArrays object into a jsonfile.
     */
    public void write() throws IOException {
        ObjectMapper mapper =  new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(this.saveSimulationArrays);
        FileWriter writer = new FileWriter(this.path);
        writer.write(jsonInString);
        writer.close();
    }
}

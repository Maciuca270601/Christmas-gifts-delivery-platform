package solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import write.SaveAnnualArray;
import write.SaveSimulationArrays;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
@SuppressWarnings("unchecked")

public class Solver {
    private SaveSimulationArrays saveSimulationArrays;
    private String path;

    public Solver(String path) {
        this.path = path;
        this.saveSimulationArrays = new SaveSimulationArrays();
    }

    public void solve() throws IOException {
        SolveFirstYear solveFirst = new SolveFirstYear();
        solveFirst.solve();

        SaveAnnualArray saveAnnualArray = new SaveAnnualArray();
        saveAnnualArray.addChildren(Database.getDatabase().getListOfChildren());

        this.saveSimulationArrays.addArray(saveAnnualArray);
        SolveNextYears solveNext = new SolveNextYears();
        for (int currYear = 1; currYear <= Database.getDatabase().getNumberOfYears(); currYear++) {
            solveNext.Solve(currYear);
            SaveAnnualArray saveAnnualArray1 = new SaveAnnualArray();
            saveAnnualArray1.addChildren(Database.getDatabase().getListOfChildren());
            this.saveSimulationArrays.addArray(saveAnnualArray1);
        }

        write();
    }

    public void write() throws IOException {
        ObjectMapper mapper =  new ObjectMapper();
        //mapper.writeValue(this.outputFile, this.saveSimulationArrays);
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.saveSimulationArrays);
        FileWriter writer = new FileWriter(this.path);
        writer.write(jsonInString);
        writer.close();
    }
}

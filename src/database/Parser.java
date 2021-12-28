package database;

import fileio.Input;

public class Parser {

    public Parser() {

    }

    public void buildDatabase(Input input) {
        Database.getDatabase().setNumberOfYears(input.getNumberOfYears());
        Database.getDatabase().setSantaBudget(input.getSantaBudget());
        Database.getDatabase().setListOfChildren(input.getInitialData());
        Database.getDatabase().setListOfGifts(input.getSantaGiftList());
        Database.getDatabase().setAnnualChanges(input.getAnnualChanges());
    }
}

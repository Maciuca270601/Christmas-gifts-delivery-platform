package database;

import fileio.Input;

public final class Parser {

    public Parser() {

    }

    /**
     * This method is used to save the entire input in my personal database.
     * @param input
     *      -> receives an input object that contains all the necessary data from the json file.
     */
    public void buildDatabase(final Input input) {
        Database.getDatabase().setNumberOfYears(input.getNumberOfYears());
        Database.getDatabase().setSantaBudget(input.getSantaBudget());
        Database.getDatabase().setListOfChildren(input.getInitialData());
        Database.getDatabase().setListOfGifts(input.getSantaGiftList());
        Database.getDatabase().setAnnualChanges(input.getAnnualChanges());
    }
}

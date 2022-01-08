package solver;

import common.Constants;
import database.Database;
import entities.Child;
import factory.ElfFactory;
import fileio.YearDataInput;
import strategy.SortById;
import strategy.SortStrategy;
import strategy.StrategyFactory;
import utils.Utils;
import visitor.Visitor;
import visitor.AverageScoreVisitor;
import visitor.GiveGiftVisitor;
import visitor.BudgetVisitor;
import visitor.CityScoreVisitor;
import visitor.BonusScoreVisitor;

public final class SolveNextYears {

    /**
     * This method does all the tasks for the next steps of the simulation.
     */
    public void solve(final int currentYear) {

        // Update every child age.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.setAge(child.getAge() + 1);
        }

        // Remove every child that has become a young adult.
        Database.getDatabase().getListOfChildren().removeIf(c -> c.getAge() > Constants.MAX_AGE);

        // Get current changes for the year.
        YearDataInput currChanges = Database.getDatabase().getAnnualChanges().get(currentYear - 1);

        // Update Santa Budget if necessary.
        if (currChanges.getNewSantaBudget() != null) {
            Database.getDatabase().setSantaBudget(currChanges.getNewSantaBudget());
        }

        // Add new children if they are not young adults already.
        Utils.addNewChildren(currChanges);

        // Update existing children from the list if necessary.
        Utils.updateExistingChildren(currChanges);

        // Update gift list if necessary.
        Utils.addNewGifts(currChanges);

        // Sort the lists of gifts before sending them to children.
        Database.getDatabase().sortListOfGifts();

        // Update average Score for every child.
        AverageScoreVisitor averageScore = new AverageScoreVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(averageScore);
        }

        // Set bonus score for every child.
        BonusScoreVisitor bonusScore = new BonusScoreVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(bonusScore);
        }

        // Set city score for every child.
        CityScoreVisitor cityScore = new CityScoreVisitor();
        SortById idSort = new SortById();
        Database.getDatabase().sortListOfChildren(idSort);
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(cityScore);
        }

        // Update budget for every child.
        BudgetVisitor budget = new BudgetVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(budget);
        }

        // Set Black and Pink elf for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            if (child.getElf().equals("black") || child.getElf().equals("pink")) {
                Visitor elfVisitor = ElfFactory.createElf(child.getElf());
                child.accept(elfVisitor);
            }
        }

        // Set strategy for every child.
        String type = Database.getDatabase().getAnnualChanges().get(currentYear - 1).getStrategy();
        SortStrategy strategy = StrategyFactory.createStrategy(type);
        Database.getDatabase().sortListOfChildren(strategy);

        // Give gifts for every child.
        GiveGiftVisitor giveGift = new GiveGiftVisitor();
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.getReceivedGifts().clear();
            child.accept(giveGift);
        }

        // Set yellow elf for every child
        for (Child child: Database.getDatabase().getListOfChildren()) {
            if (child.getElf().equals("yellow")) {
                Visitor elfVisitor = ElfFactory.createElf(child.getElf());
                child.accept(elfVisitor);
            }
        }
    }
}

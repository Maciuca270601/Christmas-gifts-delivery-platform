package solver;

import common.Constants;
import database.Database;
import entities.Child;
import factory.ElfFactory;
import strategy.SortById;
import utils.Utils;
import visitor.*;

public final class SolveFirstYear {

    /**
     * This method does all the tasks for the first step of the simulation.
     */
    public void solve() {
        Visitor averageScore = new AverageScoreVisitor();
        Visitor budget = new BudgetVisitor();
        Visitor giveGift = new GiveGiftVisitor();
        Visitor bonusScore = new BonusScoreVisitor();
        Visitor cityScore = new CityScoreVisitor();
        //ElfFactory factory = new ElfFactory();

        // Remove every child that is over 18 years old.
        Database.getDatabase().getListOfChildren().removeIf(c -> c.getAge() > Constants.MAX_AGE);

        // Check if there are any duplicates in giftPreferences for every child.
        for (Child c: Database.getDatabase().getListOfChildren()) {
            c.setGiftsPreferences(Utils.removeDuplicatesGiftPref(c));
        }

        // Sort the lists of gifts before sending them to children.
        Database.getDatabase().sortListOfGifts();

        // Set average score for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(averageScore);
        }

        // Set bonus score for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(bonusScore);
        }

        // Set city score for every child.
        SortById idSort = new SortById();
        Database.getDatabase().sortListOfChildren(idSort);
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(cityScore);
        }

        // Set Budget for every child.
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
        // Initially in first year the strategy is by id.

        // Give gifts for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            child.accept(giveGift);
        }

        // Set White and Yellow elf for every child.
        for (Child child: Database.getDatabase().getListOfChildren()) {
            if (child.getElf().equals("yellow")) {
                Visitor elfVisitor = ElfFactory.createElf(child.getElf());
                child.accept(elfVisitor);
            }
        }
    }
}

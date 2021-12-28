package visitor;

import entities.Child;
import entities.Gift;
import utils.Utils;

import java.util.ArrayList;

public class GiveGiftVisitor implements Visitor{
    @Override
    public void visit(Child child) {

        // calculates the amount of money spent on the gifts for this child
        Double currentSpendings = 0d;
        ArrayList<String> strings = child.getGiftPreferences();

        for (int i = 0; i < strings.size() && currentSpendings < child.getBudget(); i++)  {
            for (Gift gift: Utils.pickArray(strings.get(i))) {
                if (gift.getPrice() < child.getBudget()) {
                    child.getGifts().add(gift);
                    currentSpendings += gift.getPrice();
                    break;
                }
            }
        }
    }
}

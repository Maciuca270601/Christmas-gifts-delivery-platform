package visitor;

import common.Constants;
import entities.Child;

public final class AverageScoreVisitor implements Visitor {
    @Override
    public void visit(final Child child) {

        if (child.getAge() < Constants.BABY_AGE) {
            child.setAverageScore(Constants.BABY_AVERAGE);
        }
        if (child.getAge() >= Constants.BABY_AGE && child.getAge() < Constants.KID_AGE) {
            Double sum = 0d;
            for (Double x: child.getNiceScoreHistory()) {
                sum += x;
            }
            sum = sum / child.getNiceScoreHistory().size();
            child.setAverageScore(sum);
        }
        if (child.getAge() >= Constants.KID_AGE && child.getAge() <= Constants.TEEN_AGE) {
            double sumOfScores = 0d;
            double sumOfIndexes = 0d;
            int index = 1;
            for (Double x: child.getNiceScoreHistory()) {
                sumOfScores += x * index;
                sumOfIndexes += index;
                index += 1;
            }
            sumOfScores = sumOfScores / sumOfIndexes;
            child.setAverageScore(sumOfScores);
        }
    }
}

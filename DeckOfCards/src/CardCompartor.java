import java.util.Comparator;

public class CardCompartor implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        if (o1.suitValue > o2.suitValue) {
            return 1;
        } else if (o1.suitValue < o2.suitValue) {
            return -1;
        } else {
            if (o1.value > o2.value) {
                return 1;
            } else if (o1.value < o2.value) {
                return -1;
            }
        }
        return 0;
    }
}

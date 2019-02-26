import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Card> deckOfCards = new LinkedList<>();

        for (Card.Suit s : Card.Suit.values()) {
            for (NumberCard.NumRank n : NumberCard.NumRank.values()) {
                deckOfCards.add(new NumberCard(s, n));
            }
            for (FaceCard.FaceRank f : FaceCard.FaceRank.values()) {
                deckOfCards.add(new FaceCard(s, f));
            }
        }
        /*
        Collections.shuffle(deckOfCards);

        for (Card c : deckOfCards) {
            System.out.println(c);
        }

        NumberCard ace4 = new NumberCard(Card.Suit.SPADE, NumberCard.NumRank.ACE);
        NumberCard ace3 = new NumberCard(Card.Suit.HEART, NumberCard.NumRank.ACE);

        System.out.println(ace4.compareTo(ace3));
        */
        CardCompartor c = new CardCompartor();


        Collections.sort(deckOfCards,c);
        Iterator<Card> ca = deckOfCards.iterator();
        while (ca.hasNext()) {
            System.out.println(ca.next());
        }
    }
}


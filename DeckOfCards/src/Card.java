public abstract class Card implements Comparable<Card>{
    static int idCount = 1;
    protected int id;
    protected Suit suit;
    protected int suitValue;
    protected String rank;
    protected int value;
    protected String name;

    protected Card(Suit suit, String rank, int value){
        this.suit = suit;
        this.suitValue = suit.getValue();
        this.rank = rank;
        this.value = value;
        this.name = rank + " of " + suit + "S";
        this.id = idCount;
        idCount++;
    }

    enum Suit {
        SPADE(4), HEART(3), CLUB(2), DIAMOND(1);
        private int value;
        Suit(int value){this.value = value;}
        public int getValue(){return value;}
    }


    public String toString() {
        return name;
    }

    @Override
    public int hashCode(){
        return value*4+suitValue;
    }

    @Override
    public int compareTo(Card o) { ;
        if (this.hashCode() > o.hashCode()){
            return -1;
        }
        else if (this.hashCode() < o.hashCode()){
            return 1;
        }
        return 0;
    }

}

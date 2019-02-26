public class NumberCard extends Card {
    public NumberCard(Suit suit, NumRank rank){
        super(suit, rank.toString(), rank.getValue() );
    }

    enum NumRank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);
        private int value;
        NumRank(int value) {this.value = value;}
        public int getValue(){ return value;}

    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof NumberCard){
            NumberCard other = (NumberCard)o;
            if (this.rank.equals(other.rank) && this.suit.toString().equals(other.suit.toString())){
                return true;
            }
        }
        return false;
    }



}

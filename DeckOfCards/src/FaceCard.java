public class FaceCard extends Card {

    FaceCard(Suit suit, FaceRank rank){
        super(suit, rank.toString(), rank.getValue());
    }

    enum FaceRank{
        JACK(11), QUEEN(12), KING(13);
        private int value;
        FaceRank(int value){this.value = value;}
        public int getValue(){return value;}
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FaceCard) {
            FaceCard other = (FaceCard)o;
            if (this.rank.equals(other.rank) && this.suit.toString().equals(other.suit.toString())){
                return true;
            }
        }
        return false;
    }


}

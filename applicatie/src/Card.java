public class Card implements Comparable<Card>{ 
    /**
     * een CLASS Card als Vergelijking, dat betekend dat wij in dit CLass 2 objecten gaan Vergelijken en
     * ook elke appart!!
     * omdat ons CARD geen implement heeft moesten wij het zelf plaatsen en we kregen dan een error code melding.
     * java gaf een method als hint en we hebben toen op ADD Unimplemented methods geklikt.
     * we kregen dan toen de @override public int compareTo(Card c)
     */
    
    private Suit suit; // is een Suit field en we referen het weer in ons Suit class
     private Rank rank; // is een Rank field en we referen het weer in ons Rank Class

    /**
     *
     * @param suit  een constructor voor suit
     * @param rank  een constructor voor rank
     */
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Copy constructor
     * @param card the card being copied
     */
    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    /**
     *
     * return  De numerieke waarde van de Kaart
     */
    public int getValue(){
        return rank.rankValue;
    }

    /**
     *
     * @return The suit of the Card. en Rank!!
     */
    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    /**
     *
     * @return De kaart als leesbare string weergeven als output
     */
    public String toString(){
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");

    }

    /**
     * hier Vergelijk men de kaart vergelijk met een andere kaart, geef 1 terug als deze hoger is, -1 als deze lager is,
     * voor het geval als we kaarten willen sorteren.
     * @param c bij deze wordt de kaart die wordt vergeleken
     * @return 1 indien groter, -1 indien kleiner, 0 indien gelijk
     */
    @Override
    public int compareTo(Card c) {
        //als deze kaart groter is dan de andere kaart,wat moet dan gebeuren..????
        if(this.getValue() > c.getValue()){
            return 1;
        }
        else if(this.getValue() < c.getValue()){
            return -1;
        }
        else{
            return 0;
        }
    }


}

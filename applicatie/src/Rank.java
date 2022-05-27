public enum Rank { /**Waarom geen CLASS in plaats van ENUM, Omdat ENUM voor Enumeration of te wel opsomming
     en het functioneerd als een list die een Constante Variabels heeft...
     */
    
    ACE("Ace", 11),
    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four",4),
    FIVE("Five",5),
    SIX("Six",6),
    SEVEN("Seven",7),
    EIGHT("Eight",8),
    NINE("Nine",9),
    TEN("Ten",10),
    JACK("Jack",10),
    QUEEN("Queen",10),
    KING("King",10);

    String rankName; // dit is een field voor de Kaart Namen
    int rankValue; // dit is een field voor de kaart Waarde in getal

    //constructor for Enum, each Rank has a name and a value
    Rank(String rankName, int rankValue){
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String toString(){
        return rankName;
    }

}

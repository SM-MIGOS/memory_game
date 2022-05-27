public abstract class Person {
    
    //Active card die hij in zijn handen heeft
    private Hand hand;
    //deze string houd de informatie van de players of van de dealer of van de player
    private String name;

    /**
     * Create a new Person
     */
    public Person(){
        //hier krijgen ze kaarten 
        this.hand = new Hand();
        this.name = "";
    }


    
    public Hand getHand(){
        return this.hand;
    }
    public void setHand(Hand hand){
        this.hand = hand;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    
    public void printHand(){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());
    }

    /**
     * player krijgen kaarten van uit de deck (Automatisch)
     * @param deck 
     * @param discard 
     */
    public void hit(Deck deck, Deck discard){

        //als er geen kaarten meer over zijn op de stapel
        if (!deck.hasCards()) {
            //reload the deck from the discard pile
            deck.reloadDeckFromDiscard(discard);
        }
        //kaarten nemen vanuit de stapel
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        //output van de hand
        this.printHand();
        //pause voor een minuut want de players moeten lezen en effe nadenken wat voor beslissingen ze willen nemen
        Game.pause();

    }

    /**
     * Check if Person has 21 
     * want spel regel zegt als 1 van ze 21 heeft moet de game afsluiten en daarna een new start game van maken. als beide 
     * partijen 21 hebben dan zeg men Push!!!
     * @return True if the Person has 21
     */
    public boolean hasBlackjack(){
        if(this.getHand().calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }

}

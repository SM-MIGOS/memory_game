import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    
    //Een lijst van 52 kaarten
    private ArrayList<Card> deck;


    /**
     * een leeg spel van kaarten opmaak
     */
    public Deck(){ // is een constructor voor Deck die een parameter heeft
        deck = new ArrayList<Card>();
    }

    /**
     *  Constructor, 1ste input parameter voor @param c
     * @param c een copy deck met Collection 
     */
    public Deck(Deck c){
        Collections.copy(this.deck, c.getCards());
    }

    /**
     * het maken van een standard kaartenspel in samenwerking van een boolean, vervolg de 2de parameter voor makeDeck
     * @param makeDeck boolean die gepaard gaan met de 52kaarten en rangen ervan en de totale Waarden ervan
     */
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //dit moet door alle 52 kaarten heen gaan
            for(Suit suit : Suit.values()){
                //daarnaast moet het de volledig pak en rangen gaan van de 52kaarten
                for(Rank rank : Rank.values()){
                    //daarnaast moet er weer een nieuw kaart toegevoegd worden met daarin de Waarde erbij
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }

    /**
     * de 3de parameter voor Card die meegenomen worden voor het toevoegen 
     * @param card 
     */
    public void addCard(Card card){ // hier return hij nergens daarom void voor het plaatsen van kaarten in de deck,
                                    // is een method om in de deck java de kaarten te voegen die voor de main game zal toestaan. 
        deck.add(card);
    }

    /**
     * de 4de parameter voor de lijst van kaarten in deze deck.
     * @param cards an arraylist of cards to be added to this deck
     */
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    /**
     *
     * @return alles waarde van de deck in de string als output geven en nauwkeurig ook
     */
    public String toString(){
        //alles wat we terug gaan roepen in die string
        String output = "";

        for(Card card: deck){
            output += card;
            output += "\n"; //voor elk kaart een nieuw regel
        }
        return output;
    }

    /**
     * willekeurig worden de kaarten van het spel geshuffeld
     */
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }

    /**
     *
     * @return De kaarten zijn  uit de stapel van 52kaarten genomen
     */
    public Card takeCard(){

            
            Card cardToTake = new Card(deck.get(0));
            //Remove the card from the deck
            deck.remove(0);
            //Give the card back
            return cardToTake;

    }

    /**
     *
     * @return een warebewering als de stapel nog kaarten over heeft
     */
    public boolean hasCards(){
        if (deck.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return Het aantal kaarten dat nog in de stapel zit
     */
    public int cardsLeft(){
        return deck.size();
    }

    /**
     *
     * @return de arraylijst met alle kaarten in dit kaartspel
     */
    public ArrayList<Card> getCards() {
        return deck;
    }

    /**
     * deck kan leeg gemaakt worden
     */
    public void emptyDeck(){
        deck.clear();
    }


    /**
     * Neem alle kaarten van een weggegooide stapel en plaats ze geschud in deze stapel.
     *  het oude kaartspel kan gewist worden
     * @param discard - the deck we're getting the cards from
     */
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, er wordt nu een NEW DECK opgemaakt.");
    }



}

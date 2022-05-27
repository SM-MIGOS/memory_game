
/**Game logica interface */

public class Game {
    

    private Deck deck, discarded;

    private Dealer dealer;
    private Player player;
    private int wins, losses, pushes;



    /**
     * Constructor voor onze Game, maakt onze variabelen aan en het spel start hier 
     */
    public Game(){

        //Maak een nieuw kaartspel met 52 kaarten
        deck = new Deck(true);
        //een nieuw kaartspel maken (Deck)
        discarded = new Deck();

        //Player aanmaken genaamd (Dealer en Player)
        dealer = new Dealer();
        player = new Player();


        // het kaartspel wordt geschake en de eerste ronde moet van start gaan
        deck.shuffle();
        startRound();
    }


    /**
     * Start een nieuwe ronde, toon de score, deel kaarten uit, controleer op BlackJack, vraag de speler wat hij wil doen!!
     * let wel de speler bepaald als hij hit of door wilt blijven.!!!!
     */
    private void startRound(){

        //Als dit niet de eerste ronde is, geef dan de score van de players weer en leg hun kaarten terug in de stapel.
        
        if(wins>0 || losses>0 || pushes > 0){
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: "+ losses+ " Pushes: "+pushes);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        // het Controleer of het kaartspel nog minimaal 4 kaarten over heeft om te beginnen
        if(deck.cardsLeft() < 4){
            //hier worden de kaarten weer opgehaald op de stapel kaarten
            deck.reloadDeckFromDiscard(discarded);
        }

        //dealer rkijgt 2 kaarten
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        //de player krijgt ook 2 kaarten standard
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //1ste kaart moet te lezen zijn en de 2de kaart moet niet te zien zijn voor de player
        dealer.printFirstHand();

        //player kaart is wel volledig te zien.
        player.printHand();

        //Controleer of de dealer BlackJack heeft om te starten
        if(dealer.hasBlackjack()){
            //Show the dealer has BlackJack
            dealer.printHand();

            //Controleer of de speler ook BlackJack heeft
            if(player.hasBlackjack()){
                //ronde eindigen met push wooord
                System.out.println("You both have 21 - Push.");
                pushes++;
                //en een nieuwe ronde start voor de dealer en de player
                startRound();
            }
            else{
                System.out.println("Dealer has BlackJack. You lose.");
                dealer.printHand();
                losses++;
                //player lost, start new ronden
                startRound();
            }
        }

        //Controleer of de speler blackjack heeft om te beginnen
        //Als we op dit punt zijn gekomen, weten we al dat de dealer geen blackjack had 
        if(player.hasBlackjack()){
            System.out.println("You have Blackjack! You win!");
            wins++;
            startRound();
        }

        //Laat de speler beslissen wat hij vervolgens gaat doen
        player.makeDecision(deck, discarded);

        //Controleer of ze gepakt zijn waarde van 21 de aantal kaarten opgeteld
        if(player.getHand().calculatedValue() > 21){
            System.out.println("You have gone over 21.");
            losses ++;
            startRound();
        }

        //het is nu dealer zijn beurt om te spelen
        dealer.printHand();
        while(dealer.getHand().calculatedValue()<17){
            dealer.hit(deck, discarded);
        }

        //Controleer wie gewonnen heeft en de  winsten of verliezen optellen of aantonen
        if(dealer.getHand().calculatedValue()>21){
            System.out.println("Dealer busts!!");
            wins++;
        }
        else if(dealer.getHand().calculatedValue() > player.getHand().calculatedValue()){
            System.out.println("You lose!!!!!!.");
            losses++;
        }
        else if(player.getHand().calculatedValue() > dealer.getHand().calculatedValue()){
            System.out.println("!!!!!You win!!!!!.");
            wins++;
        }
        else{
            System.out.println("Push!.");
            pushes++;
        }

        //een heel new start ronde
        startRound();
    }

    /**
     * We doen dit om het spel iets te vertragen (Pause), zodat de player kan
     * lezen en kijken wat er aan de hand is...want er is een hoop van  output in één keer
     */
    public static void pause(){
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


}

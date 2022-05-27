public class Dealer extends Person {
    
    /**
     * Create a new Dealer
     */
    public Dealer(){

        //Name the dealer "Dealer"
        super.setName("Dealer");

    }

    /**
     * de output van de dealer first hand!!
     */
    public void printFirstHand(){
        System.out.println("The dealer's hand looks like this:");
        System.out.println(super.getHand().getCard(0));
        System.out.println("The second card is face down.[Hidden]");
    }


}

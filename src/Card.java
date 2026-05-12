public class Card {

    public int value;
    public String suit;
    public boolean isUp;


    public Card(int pvalue, String psuit, boolean pisUp) {
        value = pvalue;
        suit = psuit;
        isUp = pisUp;
    }

    public void printCard() {

        if (!isUp) {
            System.out.println("Card Face Down");
            return;
        }

        if (value == 0) {
            System.out.println("Ace of " + suit);
        } else if (value == 10) {
            System.out.println("Jack of " + suit);
        } else if (value == 11) {
            System.out.println("Queen of " + suit);
        } else if (value == 12) {
            System.out.println("King of " + suit);
        } else {
            System.out.println((value + 1) + " of " + suit);
        }
    }

    public int geBlackjackValue() {
        if (value == 0){
            return 11;
        } else if (value >= 10) {
            return 10;
        } else {
            return value +1;
        }
    }
}

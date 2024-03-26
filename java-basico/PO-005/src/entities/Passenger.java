package entities;

public class Passenger extends Person {

    private int cardType;

    public int getCardType() {
        return cardType;
    }

    public Passenger() {
        super();
    }

    public Passenger(String name) {
        super(name);
    }

    public Passenger(int cardType) {
        super();
        this.cardType = cardType;
    }

    public Passenger(String name, String cpf, int cardType) {
        super(name, cpf);
        this.cardType = cardType;
    }
}

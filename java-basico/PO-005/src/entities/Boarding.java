package entities;

import java.util.Date;

public class Boarding {
    private Passenger passenger;
    private Stop boardingStop;
    private Passenger cardType;
    private Date boardingDateTime;

    public Boarding(Passenger passenger, Stop boardingStop, Passenger cardType) {
        this.passenger = passenger;
        this.boardingStop = boardingStop;
        this.cardType = cardType;
        this.boardingDateTime = new Date(); // Get the current date and time
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Stop getBoardingStop() {
        return boardingStop;
    }

    public Passenger getCardType() {
        return cardType;
    }

    public Date getBoardingDateTime() {
        return boardingDateTime;
    }
}

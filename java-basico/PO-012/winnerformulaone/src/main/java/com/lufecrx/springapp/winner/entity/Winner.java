package com.lufecrx.springapp.winner.entity;

public class Winner {
    
    private String country;
    private String pilotName;
    private int victories;

    public Winner (String country, String pilotName, int victories) {
        this.country = country;
        this.pilotName = pilotName;
        this.victories = victories;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
}

package com.example.ajare_v2;

public class Succursale {
    private String addresseName;
    private String day;
    private String hour;
    String password;
    String email;
    public Succursale(){

    }
    public Succursale(String addresseName,String day, String hour){
        this.addresseName = "Novigrad " + addresseName;
        this.day = day;
        this.hour= hour;
        email = "admin"+addresseName+"@novigradcorp.com";
        password = addresseName;
    }

    public void setAddresseName(String addresseName) {
        this.addresseName = addresseName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAddresseName() {
        return addresseName;
    }

    public String getHour() {
        return hour;
    }

    public String getDay() {
        return day;
    }
    public String day_hour(){
        return getDay()+" _ "+getHour();
    }
}

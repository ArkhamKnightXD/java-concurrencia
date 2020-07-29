package models;

public class Printer {

    private int number;
    private boolean occupied;


    public Printer(int number) {

        this.number = number +1;
        this.occupied = false;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

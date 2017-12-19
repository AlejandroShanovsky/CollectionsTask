package main.java.solution;

import java.util.ArrayList;
import java.util.Random;

public class Jentelmen extends Listener implements Comparable<Jentelmen> {
    private double lenghtOfMustache;

    public Jentelmen(double lenghtOfMustache) {
        this.lenghtOfMustache = lenghtOfMustache;
    }

    public double getLenghtOfMustache() {
        return lenghtOfMustache;
    }

    public ArrayList<Theater.Cashbox.Ticket> getTickets() {
        return tickets;
    }

    @Override
    void buyTicket() {
        tickets = new ArrayList<>();
        Random random = new Random();
        tickets.add(new Theater.Cashbox.Ticket(random.nextInt(10), random.nextInt(10)));
    }

    @Override
    public int compareTo(Jentelmen o) {
        return lenghtOfMustache > o.lenghtOfMustache ? -1 : lenghtOfMustache == o.lenghtOfMustache ? 0 : 1;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " Mustache : " + lenghtOfMustache + " HashCode : " + this.hashCode();
    }
}

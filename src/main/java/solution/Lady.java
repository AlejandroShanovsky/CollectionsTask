package main.java.solution;

import java.util.ArrayList;
import java.util.Random;

public class Lady extends Listener implements Comparable<Lady> {
    private int countOfBrouch;

    public int getCountOfBrouch() {
        return countOfBrouch;
    }

    public Lady(int countOfBrouch) {
        this.countOfBrouch = countOfBrouch;
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
    public int compareTo(Lady o) {
        return countOfBrouch > o.countOfBrouch ? -1 : countOfBrouch == o.countOfBrouch ? 0 : 1;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " Brounch : " + countOfBrouch + " HashCode : " + this.hashCode();
    }
}
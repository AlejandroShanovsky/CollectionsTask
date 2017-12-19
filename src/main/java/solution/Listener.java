package main.java.solution;

import java.util.ArrayList;

public abstract class Listener {
    ArrayList<Theater.Cashbox.Ticket> tickets;

    public ArrayList<Theater.Cashbox.Ticket> getTickets() {
        return tickets;
    }

    abstract void buyTicket();
}

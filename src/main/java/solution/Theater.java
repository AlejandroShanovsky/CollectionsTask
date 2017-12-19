package main.java.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Theater {
    private static ArrayList<Listener> arrayOfListener = makeArray();
    private static ArrayList<Listener> correctQueue = makeQueue(arrayOfListener);
    private static ArrayList<Row> rows;

    public Theater() {
        rows = new ArrayList<>();
        for (int i = 1; i <= 10; i++) rows.add(new Row(i));
    }

    public void startWatch() {
        Cashbox cashbox = Cashbox.getInstanceOfCashbox(correctQueue);
        cashbox.sellTickets();
        System.out.println("Задача 1.");
        correctQueue.forEach(x -> System.out.println(x.toString()));
        cashbox.makeMap();
        System.out.println("Задача 2.");
        for (Map.Entry entry : cashbox.getCashboxMap().entrySet()) {
            System.out.println("Ticket : " + entry.getKey().toString() + " Listener : "
                    + entry.getValue().toString());
        }
    }

    private static ArrayList<Listener> makeQueue(ArrayList<Listener> arrayOfListener) {
        correctQueue = new ArrayList<>();
        ArrayList<Lady> ladys = new ArrayList<>();
        ArrayList<Jentelmen> jentelmens = new ArrayList<>();

        arrayOfListener.forEach(x -> {
            if (x instanceof Lady) {
                ladys.add((Lady) x);
            } else {
                jentelmens.add((Jentelmen) x);
            }
        });
        ladys.sort((o1, o2) -> Integer.compare(o2.getCountOfBrouch(), o1.getCountOfBrouch()));
        jentelmens.sort(((o1, o2) -> Double.compare(o2.getLenghtOfMustache(), o1.getLenghtOfMustache())));

        correctQueue.addAll(ladys);
        correctQueue.addAll(jentelmens);
        return correctQueue;
    }

    private static ArrayList<Listener> makeArray() {
        arrayOfListener = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(50); i++) {
            arrayOfListener.add(random.nextInt(2) == 1 ? new Jentelmen(Math.round(random.nextDouble() * 9))
                    : new Lady(random.nextInt(10)));
        }
        return arrayOfListener;
    }

    static final class Cashbox {
        private static ArrayList<Listener> queue;
        private static HashMap<Theater.Cashbox.Ticket, Listener> cashboxMap;
        private static ArrayList<Integer> isNumberOfTicket = new ArrayList<>();
        private static Cashbox cashbox;

        private Cashbox(ArrayList<Listener> queue) {
            Cashbox.queue = queue;
        }

        public  HashMap<Ticket, Listener> getCashboxMap() {
            return cashboxMap;
        }

        private static Cashbox getInstanceOfCashbox(ArrayList<Listener> queue) {
            if (cashbox == null) cashbox = new Cashbox(queue);
            return cashbox;
        }

        private static int generatorOfTicketNumber() {
            Random random = new Random();
            int index = random.nextInt(1000000);
            while (isNumberOfTicket.contains(index)) {
                if (!isNumberOfTicket.contains(index)) break;
                return generatorOfTicketNumber();
            }
            isNumberOfTicket.add(index);
            return index;
        }

        private static int makeTicketsPlace(int rowIndex, int placeIndex) {
            Row row = rows.get(rowIndex);
            Row.Place place = row.places.get(placeIndex);
            if (!place.isOccupied) {
                place.isOccupied = true;
                return placeIndex;
            } else {
                for (Row.Place p : row.places) {
                    if (!p.isOccupied) {
                        p.isOccupied = true;
                        placeIndex = p.numberOfPlace;
                        return placeIndex;
                    }
                }
                return placeIndex;
            }
        }

        private void sellTickets() {
            queue.forEach(Listener::buyTicket);
        }

        private HashMap<Theater.Cashbox.Ticket, Listener> makeMap() {
            cashboxMap = new HashMap<>();
            for (Listener aQueue : queue) {
                for (int k = 0; k < aQueue.getTickets().size(); k++) {
                    cashboxMap.put(aQueue.getTickets().get(k), aQueue);
                }
            }


            return cashboxMap;
        }

        public static class Ticket {
            private int numberOfTicket;
            private int row;
            private int place;

            public Ticket(int row, int place) {
                this.numberOfTicket = generatorOfTicketNumber();
                this.row = row;
                this.place = makeTicketsPlace(row, place);
            }

            @Override
            public String toString() {
                return "Ticket{" +
                        "numberOfTicket=" + numberOfTicket +
                        ", row=" + row +
                        ", place=" + place +
                        '}';
            }
        }

    }

    static final class Row {
        private ArrayList<Place> places;
        private int numberOfRow;

        public Row(int numberOfRow) {
            this.numberOfRow = numberOfRow;
            places = new ArrayList<>();
            for (int i = 1; i <= 10; i++) places.add(new Place(i));
        }

        static final class Place {
            private int numberOfPlace;
            private boolean isOccupied;

            public Place(int numberOfPlace) {
                this.numberOfPlace = numberOfPlace;
            }
        }
    }
}

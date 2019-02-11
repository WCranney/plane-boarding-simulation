import java.util.Random;

public class Simulation {

    public static int N_PLANE_ROWS = 10;
    public static int N_PLANE_COLS = 3;
    public static int N_PASSENGERS = N_PLANE_ROWS * (N_PLANE_COLS-1);

    public static void main(String[] args) {

        // plane lands
        Plane plane = new Plane(N_PLANE_ROWS, N_PLANE_COLS);

        // all passengers are waiting at the gate
        Passenger[] passengers = new Passenger[N_PASSENGERS];
        int p = 0;
        for (int r = 0; r < N_PLANE_ROWS; r++) {
            for (int c = 0; c < N_PLANE_COLS; c++) {
                if (c == N_PLANE_COLS/2) continue;
                passengers[p++] = new Passenger(r, c);
            }
        }

        // passengers passengers board
        while (!allPassengersSeated(passengers)) {
            run(plane, passengers);
        }
    }

    public static boolean allPassengersSeated(Passenger[] passengers) {
        for (Passenger p : passengers) {
            if (!p.isSitting()) return false;
        }
        return true;
    }

    public static void run(Plane plane, Passenger[] passengers) {

        int aisle = N_PLANE_COLS/2;
        for (int r = 0; r < N_PLANE_ROWS; r++) {

            System.out.println(r);
            Cell c = plane.getCell(r, aisle);
            Passenger p = plane.getCell(r, aisle).getPassenger();
            if (p == null) {
                continue;
            }
            
            // if boarding ...
            if (p.isBoarding()) {
                // ... might have just arrived at assigned row 
                if (p.atTicketedRow()) {
                    p.stowLuggage();
                // ... or the row might still be ahead
                } else {
                    Cell toCell = plane.getCell(p.getTicketRow(), aisle);
                    p.move(toCell);
                }
            }

            // if just stowed luggage, ready to sit
            if (p.isStowing()) {
                Cell toCell = plane.getCell(p.getTicketRow(), p.getTicketCol());
                p.move(toCell);
            }
        }

        // if entrance is free, then board
        Cell entrance = plane.getCell(0, N_PLANE_COLS/2);
        if (!entrance.isOccupied()) {
            passengers = shuffle(passengers);
            for (Passenger p : passengers) {
                 if (p.isWaiting()) {
                     p.move(entrance);
                     break;
                 }
            }
        }
    }

    public static Passenger[] shuffle(Passenger[] passengers) {
        Random rnd = new Random();
        for (int swapfrom = passengers.length - 1; swapfrom > 0; swapfrom--) {
            int swapto = rnd.nextInt(swapfrom + 1);
            Passenger passenger = passengers[swapfrom];
            passengers[swapfrom] = passengers[swapto];
            passengers[swapto] = passenger;
        }
        return passengers;
    }
}

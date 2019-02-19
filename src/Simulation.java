import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Simulation {

    public static int N_PLANE_ROWS = 10;
    public static int N_PLANE_COLS = 7;
    public static int N_PASSENGERS = N_PLANE_ROWS * (N_PLANE_COLS-1);

    public static void main(String[] args) {
    	

        // plane lands
    	System.out.println("ANNOUNCEMENT: the plane has landed");
        Plane plane = new Plane(N_PLANE_ROWS, N_PLANE_COLS);

        // all passengers are waiting at the gate
        System.out.println("ANNOUNCEMENT: passengers assembled and waiting");
        Passenger[] passengers = new Passenger[N_PASSENGERS];
        int p = 0;
        for (int r = 0; r < N_PLANE_ROWS; r++) {
            for (int c = 0; c < N_PLANE_COLS; c++) {
                if (c == N_PLANE_COLS/2) continue;
                passengers[p++] = new Passenger(r, c);
            }
        }
        
    	World w = new World(plane);
    	
        // passengers passengers board
        while (!allPassengersSeated(passengers)) {
            run(plane, passengers, w);
        }
                
    }

    public static boolean allPassengersSeated(Passenger[] passengers) {
        for (Passenger p : passengers) {
            if (!p.isSitting()) return false;
        }
        return true;
    }

    public static void run(Plane plane, Passenger[] passengers, World w) {
    	
        int aisle = N_PLANE_COLS/2;
        for (int r = N_PLANE_ROWS - 1; r >= 0; r--) {

            Passenger p = plane.getCell(r, aisle).getPassenger();
            if (p == null) {
                continue;
            }
            
            // passenger state before moving
            System.out.println("BEFORE: " + p.toString() + " is at " + p.getLoc().toString());
            
            // if boarding ...
            if (p.isBoarding()) {
                // ... might have just arrived at assigned row 
                if (p.atTicketedRow()) {
                    p.stowLuggage();
                // ... or the row might still be ahead
                } else {
                    Cell toCell = plane.getCell(r + 1, aisle);
                    if (!toCell.isOccupied()) {
                    	p.move(toCell);
                    }
                }
            }

            // if just stowed luggage, ready to sit
            else if (p.isStowing()) {
                Cell toCell = plane.getCell(p.getTicketRow(), p.getTicketCol());
                p.sit(toCell);
            }
            
            // passenger state after moving
            System.out.println("AFTER: " + p.toString() + " is at " + p.getLoc().toString());
        }

        // if entrance is free, then board
        Cell entrance = plane.getCell(0, N_PLANE_COLS/2);
        if (!entrance.isOccupied()) {
            passengers = shuffle(passengers);
            for (Passenger p : passengers) {
                 if (p.isWaiting()) {
                     p.board(entrance);
                     break;
                 }
            }
        }
        
        // wait a second to sync with render
        try {
        	TimeUnit.SECONDS.sleep(1);
        } catch(Exception e) {
        	// no exception handling
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

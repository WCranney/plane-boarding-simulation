public class Passenger {

    private class Ticket {

        private int row;
        private int col;

        public Ticket(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row; 
        }

        public int getCol() {
            return col;
        }
    }

    private enum State {
        WAITING, BOARDING, STOWING, SITTING
    }

    private Cell loc;
    private Ticket ticket;
    private State state;

    public Passenger(int row, int col) {
        this.ticket = new Ticket(row, col);
        this.loc = null;
        this.state = Passenger.State.WAITING;
    }

    public Cell getLoc() {
        return loc;
    }

    public int getTicketRow() {
        return ticket.getRow(); 
    }

    public int getTicketCol() {
        return ticket.getCol(); 
    }

    public void move(Cell from, Cell to) {
        from.setPassenger(null);
        to.setPassenger(this);
        this.loc = to;
    }

    public void move(Cell to) {
        if (this.loc != null) {  this.loc.setPassenger(null); }
        to.setPassenger(this);
        this.loc = to;
    }
    
    public void board(Cell to) {
    	move(to);
    	this.state = Passenger.State.BOARDING;
    }
    
    public void sit(Cell to) {
    	move(to);
    	this.state = Passenger.State.SITTING;
    }

    public boolean isWaiting() {
        return this.state == Passenger.State.WAITING;    
    }

    public boolean isBoarding() {
        return this.state == Passenger.State.BOARDING;
    }

    public boolean isStowing() {
        return this.state == Passenger.State.STOWING;
    }

    public boolean isSitting() {
        return this.state == Passenger.State.SITTING;
    }

    public boolean atTicketedRow() {
        if (this.loc == null) { return false; }
        return this.loc.getRow() == this.getTicketRow();
    }

    public void stowLuggage() {
        this.state = State.STOWING;
    }
    
    public String toString() {
    	return String.format("Passenger(%d,%d)[%s]",
    			ticket.getRow(), ticket.getCol(), state.toString());
    }
}

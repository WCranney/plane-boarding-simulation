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

    private Cell loc;
    private Ticket ticket;

    public Passenger(int row, int col) {
        this.ticket = new Ticket(row, col);
        this.loc = null;
    }

    public Cell getLoc() {
        return loc;
    }

    public Ticket getTicket() {
        return ticket; 
    }

    public void move(Cell from, Cell to) {
        from.setPassenger(null);
        to.setPassenger(this);
        this.loc = to;
    }
}

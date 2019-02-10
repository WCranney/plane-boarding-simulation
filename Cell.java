public class Cell {

    int row;
    int col;
    private Passenger passenger;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.passenger = null; 
    }

    public Passenger getPassenger() {
        return passenger; 
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger; 
    }
}

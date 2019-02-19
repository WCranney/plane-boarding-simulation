public class Cell {

    int row;
    int col;
    private Passenger passenger;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.passenger = null; 
    }

    public int getRow() {
        return row; 
    }

    public int getCol() {
        return col; 
    }

    public Passenger getPassenger() {
        return passenger; 
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger; 
    }

    public boolean isOccupied() {
        return this.passenger != null;
    }
    
    public String toString() {
    	return String.format("Cell(%d, %d)", this.row, this.col);
    }
}

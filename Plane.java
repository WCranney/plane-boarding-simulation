public class Plane {

    public int nrows;
    public int ncols;
    public Cell[][] grid;

    public Plane(int nrows, int ncols) {
        this.nrows = nrows;
        this.ncols = ncols;
        this.grid = new Cell[nrows][ncols];
    }

    public Cell getCell(int r, int c) {
        System.out.println("asiubfiusadbfo");
        return grid[r][c];
    }
}

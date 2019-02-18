public class Plane {

    public int nrows;
    public int ncols;
    public Cell[][] grid;

    public Plane(int nrows, int ncols) {
        this.nrows = nrows;
        this.ncols = ncols;
        this.grid = initializeGrid(nrows, ncols);
    }

    public Cell getCell(int r, int c) {
        return grid[r][c];
    }

    private Cell[][] initializeGrid(int nrows, int ncols) {
        Cell[][] grid = new Cell[nrows][ncols]; 
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncols; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
        return grid;
    }
}

package main

import()

type Passenger struct {
    row int
    col int
    status string
}

func NewPassenger(row int, col int) *Passenger {
    p := new(Passenger)
    p.row = row
    p.col = col
    p.status = "standing"
    return p
}

func (p Passenger) GetState() string {
    return p.status
}

type Cell struct {
    occupant Passenger
    kind string
}

func NewCell(kind string) *Cell {
    c := new(Cell)
    c.kind = kind
    return c
}

type Grid struct {
    nrows int
    ncols int
    grid [][]Cell
}

func NewGrid(nrows int, ncols int) *Grid {
    g := new(Grid)

    g.nrows =  nrows
    g.ncols = ncols

    g.grid = make([][]Cell, nrows)
    for i := range g.grid {
        g.grid[i] = make([]Cell, ncols)
    }

    for r := 0; r < nrows; r++ {
        for c := 0; c < ncols; c++ {
            kind := "seat"
            if (c == ncols/2) { kind = "aisle" }
            g.grid[r][c] = *NewCell(kind)
        }
    }

    return g
}

func AllPassengersSeated(passengers []Passenger) bool {
    for _, p := range passengers {
        if (p.GetState() != "seated") {
            return false
        }
    }
    return true
}

func main() {

    // initialize plane - 27 rows, 7 cols (3 seats left/right and middle aisle)
    plane := NewGrid(27, 7)

    // initizlize some set of passengers
    passengers := make([]Passenger, 3)
    passengers[0] = *NewPassenger(27,1)
    passengers[1] = *NewPassenger(7,2)
    passengers[2] = *NewPassenger(5,2)


    // while not all passengers seated
    for !AllPassengersSeated(passengers) {

        // shuffle passengers here

        for _, p := range passengers {
            state := p.GetState()
            switch state {
            case "waiting":
                // FOLLOW ON HERE
            }
        }
    }

    // shuffle passengers
    // for each passenger in passengers
    // if not boarded, and can board, board
    // if boarded, and not at row, and can move forward, move forward
    // if boarded, and at row, and not stowed, stow
    // if boarded, and at row, and stowed, sit

    _ = passengers
    _ = plane
}

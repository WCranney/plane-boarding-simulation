package main

import()


type Plane struct {
    Nrows int
    Ncols int
    Fuselage [][]Cell
}

func NewPlane(nrows int, ncols int) *Plane {
    p := new(Plane)
    p.Nrows =  nrows
    p.Ncols = ncols

    p.Fuselage = make([][]Cell, nrows)
    for i := range p.Fuselage {
        p.Fuselage[i] = make([]Cell, ncols)
    }

    for r := 0; r < nrows; r++ {
        for c := 0; c < ncols; c++ {
            t := Seat
            if (c == ncols/2) { t = Aisle }
            p.Fuselage[r][c] = *NewCell(t)
        }
    }

    return p
}

func (p Plane) CanBoard() bool {
    return p.Fuselage[0][p.Ncols/2].Passenger == nil
}


type CellType string

const(
    Seat CellType = "SEAT"
    Aisle CellType = "AISLE"
)

type Cell struct {
    Passenger *Passenger
    Type CellType
}

func NewCell(t CellType) *Cell {
    c := new(Cell)
    c.Type = t
    return c
}

func (c Cell) IsOccupied() bool {
    return c.Passenger != nil
}


type PassengerState string

const(
    Waiting = "WAITING"
    Boarded = "BOARDED"
    Stowing = "STOWING"
    Seated = "SEATED"
)

type Passenger struct {
    Row int
    Col int
    State string
    Cell *Cell
}

func NewPassenger(row int, col int) *Passenger {
    p := new(Passenger)
    p.Row = row
    p.Col = col
    p.State = Waiting
    return p
}

func (p Passenger) MoveTo(to Cell) {
    if (p.Cell != nil) {
        p.Cell.Passenger = nil
    }
    to.Passenger = &p
    p.Cell = &to
}


func AllPassengersSeated(passengers []Passenger) bool {
    for _, p := range passengers {
        if (p.State != Seated) { return false }
    }
    return true
}


func main() {

    plane := *NewPlane(27, 7)

    passengers := make([]Passenger, 3)
    passengers[0] = *NewPassenger(27,1)
    passengers[1] = *NewPassenger(7,2)
    passengers[2] = *NewPassenger(5,2)

    for !AllPassengersSeated(passengers) {

        // shuffle passengers here

        // run through each of the aisle rows
        c := plane.Ncols/2
        for r := plane.Nrows-1; r >= 0; r-- {

            // if the row's occupied
            if (plane.Fuselage[r][c].Passenger != nil) {
                p := plane.Fuselage[r][c].Passenger

                switch p.State {

                case Waiting:
                    if (p.Row == r) {
                        p.State = Stowing
                    } else {
                        p.MoveTo(plane.Fuselage[r+1][c])
                    }

                case Stowing:
                    p.MoveTo(plane.Fuselage[p.Row][p.Col])

                }
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

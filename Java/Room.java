package centralScotlandCollegeNamespace;

import java.io.Serializable;

abstract class Room implements Serializable {

    private String name;
    private int amountOfSeats;
    private boolean isAvailable;

    public Room(String name, int amountOfSeats, boolean isAvailable)
    {
        this.name = name;
        this.amountOfSeats = amountOfSeats;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        if (isAvailable)
        {
        return "Room " + name + " has " + amountOfSeats + " breakout chairs and isn\'t booked!\n";
        }else {
            return "Room " + name + " has " + amountOfSeats + " breakout chairs and is booked!\n";
        }
    }
}

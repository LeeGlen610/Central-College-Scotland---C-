package centralScotlandCollegeNamespace;

import java.io.Serializable;

public class Meeting extends Room implements Serializable {

    public Meeting(String name, int amountOfSeats, boolean isAvailable) {
        super(name, amountOfSeats, isAvailable);
    }

}

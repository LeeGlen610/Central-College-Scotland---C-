package centralScotlandCollegeNamespace;

import java.io.Serializable;

public class Conference extends Room implements Serializable {

    private boolean smartboardAvailable;

    public Conference(String name, int amountOfSeats, boolean isAvailable, boolean smartboardAvailable)
    {
        super(name, amountOfSeats, isAvailable);
        this.smartboardAvailable = smartboardAvailable;
    }

    public boolean isSmartboardAvailable() {
        return smartboardAvailable;
    }

    public void setSmartboardAvailable(boolean smartboardAvailable) {
        this.smartboardAvailable = smartboardAvailable;
    }

    @Override
    public String toString() {
        if (smartboardAvailable) {
            return super.toString() + " It also has a smartboard available.\n";
        } else {
            return super.toString() + " It doesn't have a smartboard available.\n";

        }
        }
}

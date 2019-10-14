package centralScotlandCollegeNamespace;

import java.io.Serializable;

public class ComputerLab extends Conference implements Serializable {

    private boolean printerAvailable;
    private int amountOfComputers;

    public ComputerLab(String name, int amountOfSeats, boolean isAvailable, boolean smartboardAvailable, boolean printerAvailable, int amountOfComputers)
    {
        super(name, amountOfSeats, isAvailable, smartboardAvailable);
        this.printerAvailable = printerAvailable;
        this.amountOfComputers = amountOfComputers;
    }

    public boolean isPrinterAvailable() {
        return printerAvailable;
    }

    public void setPrinterAvailable(boolean printerAvailable) {
        this.printerAvailable = printerAvailable;
    }

    public int getAmountOfComputers() {
        return amountOfComputers;
    }

    public void setAmountOfComputers(int amountOfComputers) {
        this.amountOfComputers = amountOfComputers;
    }

    @Override
    public String toString() {
        if (printerAvailable) {
            return super.toString() + " It also has a printer available and there is " + amountOfComputers + " computers inside!\n";
        } else {
            return super.toString() + " It doesn't have a printer available and there is " + amountOfComputers + " computers inside!\n";
        }
        }
}

package centralScotlandCollegeNamespace;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Booking implements Serializable {

    private Date dateOfOccupation;
    private Date startingTime;
    private int durationOfRoomUse;
    private Room bookedRoom;
    private Client bookedClient;

    public Booking(Date dateOfOccupation, Date startingTime, int durationOfRoomUse, Room bookedRoom, Client bookedClient) throws ParseException {
        this.dateOfOccupation = dateOfOccupation;
        this.startingTime = startingTime;
        this.durationOfRoomUse = durationOfRoomUse;
        this.bookedRoom = bookedRoom;
        this.bookedClient = bookedClient;
    }

    public Date getDateOfOccupation() {
        return dateOfOccupation;
    }

    public void setDateOfOccupation(Date dateOfOccupation) {
        this.dateOfOccupation = dateOfOccupation;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public int getDurationOfRoomUse() {
        return durationOfRoomUse;
    }

    public void setDurationOfRoomUse(int durationOfRoomUse) {
        this.durationOfRoomUse = durationOfRoomUse;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public Client getBookedClient() {
        return bookedClient;
    }

    public void setBookedClient(Client bookedClient) {
        this.bookedClient = bookedClient;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm aa");
        String date = dateFormat.format(dateOfOccupation);
        String time = timeFormat.format(startingTime);
        if (durationOfRoomUse > 1)
        {
            return "The client " + bookedClient.getName() + " has booked room " + bookedRoom.getName() + " for " + durationOfRoomUse + " hours starting on " + date + " at " + time;
        }
        else {
            return "The client " + bookedClient.getName() + " has booked room " + bookedRoom.getName() + " for " + durationOfRoomUse + " hour starting on " + date + " at " + time;
        }
    }
}

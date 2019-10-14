package centralScotlandCollegeNamespace;

import java.awt.print.Book;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Model implements Serializable {
    private static final long serialVersionUID = 1l;
    private Map<Integer, Client> clientMap = new HashMap<>();
    private Map<Integer, Room> roomMap = new HashMap<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void populateClients()
    {
        ArrayList<Client> clients = new ArrayList<>();

        clients.add(new Client("Lee Glen", "558656@learn.forthvalley.ac.uk", "07548835839"));
        clients.add(new Client("Vincent Ritchie", "VincentRitchie@teleworm.us", "07916365123"));
        clients.add(new Client("Paulina Bruce", "PaulinaBruce@jourrapide.com", "07060888103"));
        clients.add(new Client("Rhianna Russell","RhiannaRussell@armyspy.com","07886172991"));
        clients.add(new Client("Shane Brown","ShaneBrown@dayrep.com","07032367603"));

        for (int i = 0;i < clients.size(); i++)
        {
            clientMap.put(i+1, clients.get(i));
        }
    }

    public void populateRooms()
    {
        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Meeting("006", 12,true));
        rooms.add(new ComputerLab("008", 10, true, true, true, 18));
        rooms.add(new ComputerLab("011", 0, true, true, true, 20));
        rooms.add(new ComputerLab("013", 0, true, false, true, 6));
        rooms.add(new ComputerLab("014", 2, true, true, true, 18));
        rooms.add(new ComputerLab("015", 10, true, true, true, 18));
        rooms.add(new ComputerLab("017", 10, true, true, true, 18));
        rooms.add(new Conference("108", 20, true, true));
        rooms.add(new ComputerLab("120", 0, true, true, true, 18));
        rooms.add(new ComputerLab("301", 6, true, true, true, 18));

        for(int i = 0; i < rooms.size(); i++)
        {
            roomMap.put(i+1,rooms.get(i));
        }
        populateBookings();
    }

    private void populateBookings()
    {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            bookings.add(new Booking(dateFormat.parse("06/10/2019"), timeFormat.parse("10:30"), 1, changeRoomAvailability(roomMap.get(5)), clientMap.get(5)));
            bookings.add(new Booking(dateFormat.parse("20/03/2019"), timeFormat.parse("11:00"), 2, changeRoomAvailability(roomMap.get(10)), clientMap.get(2)));
            bookings.add(new Booking(dateFormat.parse("05/04/2019"), timeFormat.parse("12:00"), 3, changeRoomAvailability(roomMap.get(7)), clientMap.get(3)));
        } catch (ParseException e)
        {
            System.out.println("Error - Invalid Date!");
        }
    }

    public boolean checkDetails(String customerName, String emailAddress)
    {
        boolean isTrue = false;
        for (int i = 0; i < clientMap.size(); i++)
        {
            if (customerName.equalsIgnoreCase(clientMap.get(i+1).getName()) && emailAddress.equals(clientMap.get(i+1).getEmailAddress()))
            {
                isTrue = true;
            }
        }
        return isTrue;
    }

    public Room changeRoomAvailability(Room room)
    {
        if (room.isAvailable()) {
            room.setAvailable(false);
        } else {
            room.setAvailable(true);
        }
        return room;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public void addRoom(Room room)
    {
        roomMap.put(roomMap.size(), room);
    }

    public void addClient(Client client)
    {
        clientMap.put(clientMap.size(), client);
    }

    public ArrayList<Room> getRoom()
    {
        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomMap.size(); i++) {
            rooms.add(roomMap.get(i+1));
        }
        return rooms;
    }

    public Client getClient(String clientName, String emailAddress)
    {
        for (int i = 0; i < clientMap.size(); i++) {
            if (clientName.equalsIgnoreCase(clientMap.get(i+1).getName()) && emailAddress.equals(clientMap.get(i+1).getEmailAddress())){
                return clientMap.get(i+1);
            }
        }
        return new Client();
    }

    public ArrayList<Client>getClient(){
        ArrayList<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientMap.size(); i++){
            clients.add(clientMap.get(i+1));
        }
        return clients;
    }

    public void removeClient(String clientName, String emailAddress)
    {
        for (int i = 0; i < clientMap.size(); i++)
        {
            if (clientName.equalsIgnoreCase(clientMap.get(i+1).getName()) && emailAddress.equals(clientMap.get(i+1).getEmailAddress())){
                clientMap.remove(i+1);
            }
        }
    }

    public ArrayList<Booking> getBookings()
    {
        return bookings;
    }

    public void removeBooking(String nameOfClient, String clientEmailAddress)
    {
        for (Booking booking:
             bookings) {
            if (booking.getBookedClient().getName().equalsIgnoreCase(nameOfClient) && booking.getBookedClient().getEmailAddress().equals(clientEmailAddress)) {
                bookings.remove(booking);
            }
        }
    }

}

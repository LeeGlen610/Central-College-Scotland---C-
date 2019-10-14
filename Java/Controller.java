package centralScotlandCollegeNamespace;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    //Initialise variables
    private Model model = new Model();
    private View view = new View();
    private String nameOfClient;
    private String clientEmailAddress;

    //Will check to see if this is a new customer or an already existing customer
    public void checkDetails() {
        boolean shouldPopulate = false; //Initialise variable
        //Will check if a file already exists
        try {
            shouldPopulate = loadFromFile();
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }//END Try/Catch
        //If no file exists data gets populated
        if (!shouldPopulate) {
            model.populateClients();
            model.populateRooms();
        }//END IF
        //This checks to see if the customer is a new customer or an existing one
        while (true) {
            try {
                String input = view.displayChoiceBox("Is This A New Client?", "Yes", "No");
                if (input.equalsIgnoreCase("Yes")) {
                    storeClientDetails();
                    menu();
                } else if (input.equalsIgnoreCase("No")) {
                    //Gets information about client - Full name, Email - and checks it to see if it exists
                    nameOfClient = view.displayInputBox("Enter The Client's Full Name:");
                    clientEmailAddress = view.displayInputBox("Enter Client's E-Mail:");
                    boolean checker = model.checkDetails(nameOfClient, clientEmailAddress);
                    while (!checker) {
                        view.displayMessageBox("Incorrect Name or Email!");
                        checker = model.checkDetails(view.displayInputBox("Enter Client's Full Name:"), view.displayInputBox("Enter Client's E-Mail:"));
                    }//END WHILE
                    menu();
                } else {
                    view.displayMessageBox("Invalid Option!");
                }//END IF/ELSE
            } catch (NullPointerException e) {
                System.exit(0);
            }//END Try/Catch
        }//END WHILE
    }//END METHOD checkDetails

    //Provides the menu options for the admin
    private void menu() {
        try {
            //Will output the choices the admin can pick
            while (true) {
                int input = Integer.parseInt(view.displayInputBox("Please Select A Choice:\n1 - Book Room\n2 - Update Booking\n3 - Cancel Booking\n4 - Store Room Details\n" +
                        "5 - Update Room Details\n6 - Update Client Details\n7 - Remove Client\n8 - Generate Report\n0 - Exit"));
                switch (input) {
                    case 1:
                        bookRoom();
                        break;
                    case 2:
                        updateBooking();
                        break;
                    case 3:
                        cancelBooking();
                        break;
                    case 4:
                        storeRoomDetails();
                        break;
                    case 5:
                        updateRoomDetails();
                        break;
                    case 6:
                        updateClientDetails();
                        break;
                    case 7:
                        removeClientDetails();
                        break;
                    case 8:
                        generateReport();
                        break;
                    case 0:
                        //Will save the information to a text file
                        try {
                            saveFile();
                        } catch (IOException e) {
                            System.out.println("File Can't Save!");
                        }//END Try/Catch
                        System.exit(0);
                    default:
                        view.displayMessageBox("You have Entered A Wrong Number!");
                        break;
                }//END SWITCH
            }//END WHILE
            //Validation for not entering a number or canceling the alert box
        } catch (NumberFormatException e) {
            if (e.getMessage().equals("null")) {
                System.exit(0);
            }//END IF
            view.displayMessageBox("Invalid Number!");
            menu();
        }//END Try/Catch
    }//END METHOD menu

    //Finds the room from the clients requirements
    private Room getRoomBeingBooked() {
        //Initialise variables
        StringBuilder builder = new StringBuilder();
        int roomBreakoutChairs = -1;
        boolean smartboardAvailable = false;
        boolean printerAvailable = false;
        int computersAvaliable = -1;
        ArrayList<Room> rooms = model.getRoom();
        int input;
        boolean gottenRequirements = false;
        //Displays menu
        while (true) {
            input = Integer.parseInt(view.displayInputBox("Choose Option - Be Specific When Entering Data:\n1 - Number of Breakout Chairs Needed\n2 - Need A Printer In Room\n3 - Need A SmartBoard In Room\n4 - Number of Computers Needed\n5 - Display Rooms Based On Requirements\n0 - Finished Entering Requirements"));
            switch (input) {
                case 1:
                    roomBreakoutChairs = validateNumber("Enter Number of Breakout Chairs Needed:");
                    break;
                case 2:
                    printerAvailable = !printerAvailable;
                    break;
                case 3:
                    smartboardAvailable = !smartboardAvailable;
                    break;
                case 4:
                    computersAvaliable = validateNumber("Enter Number of Computers Needed:");
                    break;
                case 5:
                    //Will display the rooms that are available with clients data
                    if (roomBreakoutChairs == -1 || computersAvaliable == -1) {
                        view.displayMessageBox("Not Enough Data Has Been Required!");
                    } else {
                        builder.delete(0, builder.length());
                        builder.append("Found Rooms:\n");
                        for (Room room :
                                rooms) {
                            if (room instanceof ComputerLab) {
                                if (roomBreakoutChairs == room.getAmountOfSeats() && printerAvailable == ((ComputerLab) room).isPrinterAvailable() && smartboardAvailable == ((ComputerLab) room).isSmartboardAvailable()
                                        && computersAvaliable == ((ComputerLab) room).getAmountOfComputers() && room.isAvailable()) {
                                    builder.append(room.toString()).append("\n");
                                }//END IF
                            } else if (room instanceof Meeting) {
                                if (roomBreakoutChairs == room.getAmountOfSeats() && room.isAvailable() && computersAvaliable == 0 && !printerAvailable && !smartboardAvailable) {
                                    builder.append(room.toString()).append("\n");
                                }//END IF
                            } else if (room instanceof Conference) {
                                if (roomBreakoutChairs == room.getAmountOfSeats() && room.isAvailable() && !printerAvailable && smartboardAvailable == ((Conference) room).isSmartboardAvailable() && computersAvaliable == 0) {
                                    builder.append(room.toString()).append("\n");
                                }//END IF
                            }//END IF/ELSE
                        }//END FOREACH
                        if (builder.length() == "Found Rooms:\n".length()) {
                            view.displayMessageBox("No Room Found!");
                        } else {
                            view.displayMessageBox(builder.toString());
                        }//END IF/ELSE
                    }//END IF/ELSE
                    break;
                case 0:
                    gottenRequirements = true;
                    break;
                default:
                    view.displayMessageBox("Not A Choice!");
                    break;
            }//END SWITCH
            //Checks to see if the user has put in a number into these variables
            if (gottenRequirements) {
                if (roomBreakoutChairs == -1 || computersAvaliable == -1) {
                    view.displayMessageBox("Data Hasn't Been Entered For Either Breakout Chairs or Computers");
                    gottenRequirements = false;
                } else {
                    break;
                }//END IF/ELSE
            }//END IF
        }//END WHILE
        //Initialise more variables
        Room bookedRoom = null;
        String roomName = "";
        //Searches for the room name
        while (true) {
        //Asks admin for the room name
        try {
            roomName = view.displayInputBox("Enter Name of Room Being Booked:");
        } catch (NullPointerException e) {
            menu();
        }//END Try/Catch
            //Looks through rooms to find the room
            for (Room room :
                    rooms) {
                if (room.getName().equals(roomName)) {
                    bookedRoom = room;
                }//END IF
            }//END FOREACH
            if (bookedRoom == null) {
                view.displayMessageBox("Room Not Found!");
            } else {
                break;
            }//END IF/ELSE
        }//END WHILE
        //Returns room if found
        return bookedRoom;
    }//END Method getRoomBeingBooked

    private void bookRoom() {
        //Initialise Variables
        Room bookedRoom = getRoomBeingBooked();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date dateOfOccupation;
        Date startingTime;
        //Asks for when the room will booked on and where will
        while (true) {
            try {
                String date = view.displayInputBox("Enter Date of Occupation - dd/mm/yyyy");
                dateOfOccupation = dateFormat.parse(date);
                if (dateOfOccupation.before(new Date())) {
                    view.displayMessageBox("Date Has Already Passed!");
                } else {
                    break;
                }//END IF/ELSE
            } catch (ParseException e) {
                view.displayMessageBox("Not a Date!");
            }//END Try/Catch
        }//END WHILE
        while (true) {
            try {
                String time = view.displayInputBox("Enter Time of Occupation: hh:mm");
                if (time.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$")) {//Found Regular Expression From https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s06.html
                    startingTime = timeFormat.parse(time);
                    break;
                } else { //asks about college end time - Prob not a 24hour college
                    view.displayMessageBox("Not A Time!");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }//END Try/Catch
        }//END WHILE
        int durationOfRoomUse = validateNumber("Enter The Length of Time The Room WIll Be Occupied For In Hours:");
        while (true) {
            if (durationOfRoomUse > 9) {
                view.displayMessageBox("Can't Book A Room For Longer Than 8 Hours!");
                durationOfRoomUse = validateNumber("Enter The Length of Time The Room WIll Be Occupied For In Hours:");
            } else {
                break;
            }//END IF/ELSE
        }//END WHILE
        try {
            model.addBooking(new Booking(dateOfOccupation, startingTime, durationOfRoomUse, model.changeRoomAvailability(bookedRoom), model.getClient(nameOfClient, clientEmailAddress)));
        } catch (ParseException e) {
            e.printStackTrace();
        }//END Try/Catch
    }//END Method bookRoom

    private void updateBooking() {
        try {
            ArrayList<Booking> bookings = model.getBookings();
            for (Booking booking :
                    bookings) {
                if (booking.getBookedClient().getName().equalsIgnoreCase(nameOfClient) && booking.getBookedClient().getEmailAddress().equals(clientEmailAddress)) {
                    int input = Integer.parseInt(view.displayInputBox("Choose Option\n1 - Update Date of Occupation\n2 - Update Starting Time\n3 - Update Duration of Room Use\n4 - Room Change\n0 - Exit"));
                    switch (input) {
                        case 1:
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date dateOfOccupation;
                            while (true) {
                                try {
                                    String date = view.displayInputBox("Enter Date of Occupation - dd/mm/yyyy");
                                    dateOfOccupation = dateFormat.parse(date);
                                    if (Calendar.getInstance().after(dateOfOccupation)) {
                                        view.displayMessageBox("Date Has Already Passed!");
                                    } else {
                                        booking.setDateOfOccupation(dateOfOccupation);
                                        break;
                                    }//END IF/ELSE
                                } catch (ParseException e) {
                                    view.displayMessageBox("Not a Date!");
                                }//END Try/Catch
                            }//END WHILE
                        case 2:
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                            Date startingTime;
                            while (true) {
                                try {
                                    String time = view.displayInputBox("Enter Time of Occupation: hh:mm");
                                    if (time.matches("/^[0-1]?[0-9]|(2[0-3]):[0-5]?[0-9]$/")) {//Found Regular Expression From https://regex101.com/library/jG7fB6
                                        startingTime = timeFormat.parse(time);
                                        booking.setStartingTime(startingTime);
                                        break;
                                    } else {
                                        view.displayMessageBox("Not A Time!");
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }//END Try/Catch
                            }//END WHILE
                        case 3:
                            int durationOfRoomUse = validateNumber("Enter The Length of Time The Room WIll Be Occupied For In Hours:");
                            while (true) {
                                if (durationOfRoomUse > 9) {
                                    view.displayMessageBox("Can't Book A Room For Longer Than 8 Hours!");
                                    durationOfRoomUse = validateNumber("Enter The Length of Time The Room WIll Be Occupied For In Hours:");
                                } else {
                                    break;
                                }//END IF/ELSE
                            }//END WHILE
                        case 4:
                            Room bookedRoom = getRoomBeingBooked();
                            booking.setBookedRoom(bookedRoom);
                            break;
                        case 0:
                            menu();
                    }//END SWITCH
                }//END IF
            }//END FOREACH
        } catch (NullPointerException e) {
            menu();
        }//END Try/Catch
    }//END METHOD updateBooking

    private int validateNumber(String message) {
        int number = 0;
        boolean validation = false;
        while (!validation) {
            try {
                number = Integer.parseInt(view.displayInputBox(message));
                if (!(number >= 0)) {
                    view.displayMessageBox("Number Can't Be Lower Than Zero!");
                } else {
                    validation = true;
                }//END IF/ELSE
            } catch (NumberFormatException e) {
                if (e.getMessage().equals("null")) {
                    menu();
                }//END IF
                view.displayMessageBox("Invalid Number!");
            }//END Try/Catch
        }//END WHILE
        return number;
    }//END METHOD updateBooking

    private void cancelBooking() {
        model.removeBooking(nameOfClient, clientEmailAddress);
    } //END METHOD cancelBooking

    private void storeRoomDetails() {
        String name = view.displayInputBox("Enter Name of Room:");
        int amountOfSeats = validateNumber("Enter Number of Breakout Seats:");
        boolean validation = false;
        while (!validation) {
            int input = Integer.parseInt(view.displayInputBox("Enter Type of Room\n1 - Conference Room\n2 - Computer Lab\n3 - Meeting Room"));
            switch (input) {
                case 1:
                    model.addRoom(new Conference(name, amountOfSeats, true, true));
                    validation = true;
                    break;
                case 2:
                    int computers = validateNumber("Enter Number of Computers In Room:");
                    String availability = view.displayChoiceBox("Enter Yes or No If Room Has A Smartboard:", "Yes","No");
                    boolean smartboardAvailable;
                    smartboardAvailable = availability.equalsIgnoreCase("Yes");
                    availability = view.displayChoiceBox("Enter Yes or No If Room Has A Printer:", "Yes", "No");
                    boolean printerAvailable;
                    printerAvailable = availability.equalsIgnoreCase("Yes");
                    model.addRoom(new ComputerLab(name, amountOfSeats, true, smartboardAvailable, printerAvailable, computers));
                    validation = true;
                    break;
                case 3:
                    model.addRoom(new Meeting(name, amountOfSeats, true));
                    validation = true;
                    break;
                default:
                    view.displayMessageBox("You have Entered A Wrong Number!");
                    break;
            }//END SWITCH
        }//END WHILE
    }//END METHOD storeRoomDetails

    private void updateRoomDetails() {
        ArrayList<Room> rooms = model.getRoom();
        String roomName = view.displayInputBox("Enter Room Name:");
        for (Room room :
                rooms) {
            if (room.getName().equals(roomName)) {
                if (room instanceof Conference) {
                    Conference conference = (Conference) room;
                    int input = Integer.parseInt(view.displayInputBox("Choose Option\n1 - Update Room Name\n2 - Update Number of Breakout Seats\n3 - Update Availability of Room\n" +
                            "4 - Update Smartboard Availability "));
                    switch (input) {
                        case 1:
                            conference.setName(view.displayInputBox("Enter New Name:"));
                            break;
                        case 2:
                            conference.setAmountOfSeats(Integer.parseInt(view.displayInputBox("Enter New Number of Breakout Seats:")));
                            break;
                        case 3:
                            updateRoomAvailability(conference);
                            break;
                        case 4:
                            checkSmartboardAvailability(conference);
                            break;
                        default:
                            view.displayMessageBox("You have Entered A Wrong Number!");
                    }//END SWITCH
                } else if (room instanceof Meeting) {
                    int input = Integer.parseInt(view.displayInputBox("Choose Option\n1 - Update Room Name\n2 - Update Number of Breakout Seats\n3 - Update Availability of Room"));
                    switch (input) {
                        case 1:
                            room.setName(view.displayInputBox("Enter New Name:"));
                            break;
                        case 2:
                            room.setAmountOfSeats(validateNumber("Enter New Number of Breakout Seats:"));
                            break;
                        case 3:
                            updateRoomAvailability(room);
                            break;
                        default:
                            view.displayMessageBox("You have Entered A Wrong Number!");
                    }//END SWITCH
                } else if (room instanceof ComputerLab) {
                    ComputerLab computerLab = (ComputerLab) room;
                    int input = Integer.parseInt(view.displayInputBox("Choose Option\n1 - Update Room Name\n2 - Update Number of Breakout Seats\n3 - Update Availability of Room\n" +
                            "4 - Update Smartboard Availability\n5 - Update Printer Availability\n6 - Update Number of Computers"));
                    switch (input) {
                        case 1:
                            room.setName(view.displayInputBox("Enter New Name:"));
                            break;
                        case 2:
                            room.setAmountOfSeats(validateNumber("Enter New Number of Breakout Seats:"));
                            break;
                        case 3:
                            updateRoomAvailability(computerLab);
                            break;
                        case 4:
                            checkSmartboardAvailability(computerLab);
                            break;
                        case 5:
                            boolean checker = false;
                            while (!checker) {
                                String isAvailable = view.displayInputBox("Enter Yes If Printer Is Available Otherwise Enter No If Printer Isn\'t Available:");
                                if (isAvailable.equalsIgnoreCase("Yes")) {
                                    computerLab.setPrinterAvailable(true);
                                    checker = true;
                                } else if (isAvailable.equalsIgnoreCase("No")) {
                                    computerLab.setPrinterAvailable(false);
                                    checker = false;
                                } else {
                                    view.displayMessageBox("Error! Please Re-Enter Your Answer!");
                                }//END IF/ELSE
                            }//END WHILE
                            break;
                        case 6:
                            computerLab.setAmountOfComputers(validateNumber("Enter New Number of Computers:"));
                            break;
                        default:
                            view.displayMessageBox("You have Entered A Wrong Number!");
                    }//END SWITCH
                    break;
                }//END IF/ELSE
            }//END IF
        }//END FOREACH
    }//END METHOD updateRoomDetails

    private void updateRoomAvailability(Room room) {
        boolean checker = false;
        while (!checker) {
            String isAvailable = view.displayInputBox("Enter Yes If Room Is Available Otherwise Enter No If Room Isn\'t Available:");
            if (isAvailable.equalsIgnoreCase("Yes")) {
                room.setAvailable(true);
                checker = true;
            } else if (isAvailable.equalsIgnoreCase("No")) {
                room.setAvailable(false);
                checker = true;
            } else {
                view.displayMessageBox("Error! Please Re-Enter Your Answer!");
            }//END IF/ELSE
        }//END WHILE
    }//END updateRoomAvailability

    private void checkSmartboardAvailability(Conference conference) {
        boolean checker = false;
        while (!checker) {
            String isAvailable = view.displayInputBox("Enter Yes If Smartboard Is Available Otherwise Enter No If Smartboard Isn\'t Available:");
            if (isAvailable.equalsIgnoreCase("Yes")) {
                conference.setSmartboardAvailable(true);
                checker = true;
            } else if (isAvailable.equalsIgnoreCase("No")) {
                conference.setSmartboardAvailable(false);
                checker = false;
            } else {
                view.displayMessageBox("Error! Please Re-Enter Your Answer!");
            }//END IF/ELSE
        }//END WHILE
    }//END checkSmartboardAvailability

    private void storeClientDetails() {
        try {
            ArrayList<Client> clients = model.getClient();
            boolean validator = false;
            String clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
            String clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
            while (!validator) {
                if (clientFirstName.equals("") || clientLastName.equals("")) {
                    view.displayMessageBox("You've Entered Nothing!");
                    clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                    clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                } else if (clientFirstName.length() > 30 || clientLastName.length() > 30) {
                    view.displayMessageBox("You've Entered Either A First Name or Last Name Longer Than 30!");
                    clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                    clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                } else {
                    validator = true;
                }//END IF/ELSE
            }//END WHILE
            String clientName = clientFirstName.substring(0, 1).toUpperCase() + clientFirstName.substring(1) + " " + clientLastName.substring(0, 1).toUpperCase() + clientLastName.substring(1);
            validator = false;
            String emailAddress = view.displayInputBox("Enter New Client E-mail Address:");
            while (!validator) {
                for (Client client :
                        clients) {
                    if (client.getEmailAddress().equals(emailAddress)) {
                        view.displayMessageBox("E-mail Already Assigned To A Client!");
                        emailAddress = view.displayInputBox("Re-Enter New E-Mail Address");
                    }//END IF
                }//END FOREACH
                if (emailAddress.matches("^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) { //Gotten This Regular Expression From My Web Development Class.
                    validator = true;
                } else {
                    view.displayMessageBox("Invalid E-Mail Address!");
                    emailAddress = view.displayInputBox("Re-Enter New E-Mail Address");
                }//END IF/ELSE
            }//END WHILE
            validator = false;
            String phoneNumber = view.displayInputBox("Enter New Client Phone Number:");
            while (!validator) {
                for (Client client :
                        clients) {
                    if (phoneNumber.equals(client.getContactNumber())) {
                        view.displayMessageBox("Contact Number Already Assigned To A Client!");
                        phoneNumber = view.displayInputBox("Re-Enter New Contact Number");
                    }//END IF
                }//END FOREACH
                if (phoneNumber.matches("(?:^\\([0]?[1-9]{2}\\)|^[0]?[1-9]{2}[-\\s]?)[9]?[1-9]\\d{3}[-\\s]?\\d{4}$")) { //Got This Regular Expression From This Website - https://regexr.com/37juu
                    validator = true;
                } else {
                    view.displayMessageBox("Invalid Phone Number!");
                    phoneNumber = view.displayInputBox("Re-Enter Client Phone Number:");
                }//END IF/ELSE
            }//END WHILE
            model.addClient(new Client(clientName, emailAddress, phoneNumber));
        } catch (NullPointerException e) {
            System.exit(0);
        }//END Try/Catch
    }//END METHOD storeClientDetails

    private void updateClientDetails() {
        Client client = model.getClient(nameOfClient, clientEmailAddress);
        int input = Integer.parseInt(view.displayInputBox("Choose Option\n1 - Client's Name\n2 - Client's E-mail Address\n3 - Client's Phone Number\n0 - Exit"));
        switch (input) {
            case 1:
                String chosen = view.displayChoiceBox("Is The First Name Changed or Last Name Changed or Both?", "First Name", "Last Name", "Both");
                String clientFirstName;
                String clientLastName;
                String clientFullName = client.getName();
                switch (chosen) {
                    case "First Name":
                        clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                        while (true) {
                            if (clientFirstName.equals("")) {
                                view.displayMessageBox("Error - No Name Entered!");
                                clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                            } else if (clientFirstName.length() > 30) {
                                view.displayMessageBox("Error - Name Longer Than 30!");
                                clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                            } else {
                                int space = clientFullName.indexOf(" ");
                                String lastName = clientFullName.substring(space);
                                clientFullName = clientFirstName.substring(0, 1).toUpperCase() + clientFirstName.substring(1) + " " + lastName;
                                client.setName(clientFullName);
                                break;
                            }//END IF/ELSE
                        }//END WHILE
                        break;
                    case "Last Name":
                        clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                        while (true) {
                            if (clientLastName.equals("")) {
                                view.displayMessageBox("Error - No Name Entered!");
                                clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                            } else if (clientLastName.length() > 30) {
                                view.displayMessageBox("Error - Name Longer Than 30!");
                                clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                            } else {
                                int space = clientFullName.indexOf(" ");
                                String firstName = clientFullName.substring(0, space);
                                clientFullName = firstName + " " + clientLastName.substring(0, 1).toUpperCase() + clientLastName.substring(1);
                                client.setName(clientFullName);
                                System.out.println(clientFullName);
                                break;
                            }//END IF/ELSE
                        }//END WHILE
                        break;
                    case "Both":
                        clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                        clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                        while (true) {
                            if (clientFirstName.equals("") || clientLastName.equals("")) {
                                view.displayMessageBox("Error - No Name Entered!");
                                clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                                clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                            } else if (clientFirstName.length() > 30 || clientLastName.length() > 30) {
                                view.displayMessageBox("Error - First/Last Name Is Longer Than 30");
                                clientFirstName = view.displayInputBox("Enter New Client's First Name:").trim();
                                clientLastName = view.displayInputBox("Enter New Client's Last Name:").trim();
                            } else {
                                clientFullName = clientFirstName.substring(0, 1).toUpperCase() + clientFirstName.substring(1) + " " + clientLastName.substring(0, 1).toUpperCase() + clientLastName.substring(1);
                                client.setName(clientFullName);
                                break;
                            }//END IF/ELSE
                        }//END WHILE
                        break;
                }//END SWITCH
                break;
            case 2:
                String emailAddress = view.displayInputBox("Enter Client's New E-Mail Address:");
                while (true) {
                    if (emailAddress.matches("^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) { //Gotten This Regular Expression From My Web Development Class.
                        client.setEmailAddress(emailAddress);
                        break;
                    } else {
                        view.displayMessageBox("Invalid E-Mail Address!");
                        emailAddress = view.displayInputBox("Re-Enter New E-Mail Address");
                    }//END IF/ELSE
                }//END WHILE
                break;
            case 3:
                String phoneNumber = view.displayInputBox("Enter Client's New Phone Number:");
                while (true) {
                    if (phoneNumber.matches("(?:^\\([0]?[1-9]{2}\\)|^[0]?[1-9]{2}[-\\s]?)[9]?[1-9]\\d{3}[-\\s]?\\d{4}$")) { //Got This Regular Expression From This Website - https://regexr.com/37juu
                        client.setContactNumber(phoneNumber);
                        break;
                    } else {
                        view.displayMessageBox("Invalid Phone Number!");
                        phoneNumber = view.displayInputBox("Re-Enter New Phone Number:");
                    }//END IF/ELSE
                }
                break;
            case 0:
                menu();
        }//END SWITCH
    }//END updateClientDetails

    private void removeClientDetails() {
        model.removeClient(nameOfClient, clientEmailAddress);
        view.displayMessageBox("Client Removed!");
        System.exit(0);
    }//END METHOD removeClientDetails

    private void generateReport() {
        String tempMessage;
        StringBuilder message = new StringBuilder();
        String input = view.displayChoiceBox("List The Bookings By Room or By Client?", "By Room", "By Client");
        if (input.equalsIgnoreCase("By Room")) {
            message.append("Bookings Listed By Room:\n");
            for (Booking booking : model.getBookings()) {
                if (!booking.getBookedRoom().isAvailable()) {
                    tempMessage = booking.getBookedRoom().toString();
                    message.append(tempMessage).append("\n");
                }//END IF
            }//END FOREACH
            view.displayMessageBox(message.toString());
        }//END IF
        if (input.equalsIgnoreCase("By Client")) {
            message.append("Bookings Listed By Client:\n");
            for (Booking booking : model.getBookings()) {
                tempMessage = booking.getBookedClient().getName() + " has booked room " + booking.getBookedRoom().getName();
                message.append(tempMessage).append("\n");
            }//END FOREACH
            view.displayMessageBox(message.toString());
        }//END IF
    }//END METHOD generateReport

    private boolean loadFromFile() throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream("Central Scotland College.txt");
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            model = (Model) objectInput.readObject();
            objectInput.close();
        } catch (ClassNotFoundException e) {
            System.out.println("File Not Found!");
        }//END Try/Catch
        return true;
    }//END METHOD loadFromFile

    private void saveFile() throws IOException {
        FileOutputStream inputStream = new FileOutputStream("Central Scotland College.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(inputStream);
        outputStream.writeObject(model);
        outputStream.close();
    }//END METHOD saveFile

}


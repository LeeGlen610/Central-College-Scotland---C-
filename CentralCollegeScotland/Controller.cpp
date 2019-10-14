#include "Controller.h"

int main() {

	Controller* controller = new Controller();
	controller->checkDetails();

	return 0;
}

/*bool Controller::loadFromFile(void)
{
	std::stringstream ss;
	cereal::BinaryInputArchive iarchive(ss);
	iarchive(model);
	return true;
}

void Controller::saveFromFile(void)
{
	std::stringstream ss;
	cereal::BinaryOutputArchive outputArch(ss);
	outputArch(model);
}*/


void Controller::checkDetails(void)
{
	bool shouldPopulate = false;
	/*try {
		shouldPopulate = loadFromFile();
	}
	catch (exception e) {
		cout << e.what();
	}*/
	while (true)
	{
		try {
			string input;
			cout << "Is this A New Client?" << endl;
			cin >> input;
			if (input.compare("Yes"))
			{
				storeClientDetails();
				menu();
			}
			else if (input.compare("No")) {
				cout << "Enter The Client's Full Name:" << endl;
				cin >> nameOfClient;
				cout << "Enter Client's E-Mail:" << endl;
				cin >> clientEmailAddress;
				bool checker = model.checkDetails(nameOfClient, clientEmailAddress);
				while (!checker) {
					cout << "Incorrect Name or Email!";
					checker = model.checkDetails(nameOfClient, clientEmailAddress);
				}
				menu();
			}
			else
			{
				cout << "Invalid Option!";
			}
		}
		catch (exception e) {
			exit(0);
		}
	}
}

void Controller::menu() {
	try {
		while (true) {
			int input;
			cout << "Please Select A Choice:\n1 - Book Room\n2- Update Booking\n3- Cancel Booking\n4 - Store Room Details\n5- Update Room Details\n6 0 Update Client Details\n7 - Remove Client\n8 - Generate Report\n0 - Exit";
			cin >> input;
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
				exit(0);
			default:
				cout << "You Have Entered A Wrong Number!" << endl;
				break;
			}
		}
	}
	catch (exception e) {
		cout << e.what();
		exit(1);
	}
}

Room* Controller::getRoomBeingBooked() {
	int roomBreakoutChairs = -1;
	bool smartboardAvailable = false;
	bool printerAvailable = false;
	int computersAvaliable = -1;
	vector<Room*> rooms = model.getRoom();
	int input;
	bool gottenRequirements = false;
	while (true) {
		cout << "Choose Option - Be Specific When Entering Data:\n1 - Number of Breakout Chairs Needed\n2 - Need A Printer In Room\n3 - Need A SmartBoard In Room\n4 - Number of Computers Needed\n5 - Display Rooms Based On Requirements\n0 - Finished Entering Requirements";
		cin >> input;
		switch (input)
		{
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
			if (roomBreakoutChairs == -1 || computersAvaliable == -1) {
				cout << "Not Enough Data Has Been Required!";
			}
			else {
				int counter = 0;
				for (Room* room : rooms) {
					if (dynamic_cast<ComputerLab*>(room) != nullptr ){
						if (roomBreakoutChairs == room->getAmountOfSeats() && printerAvailable == ((ComputerLab*)room)->isPrinterAvailable() && smartboardAvailable == ((ComputerLab*)room)->isSmartboardAvaliable() && computersAvaliable == ((ComputerLab*)room)->getAmountOfComputers() && room->isAvaliable()) {
							cout << room->toString() << endl;
							counter++;
						}
					} else if (dynamic_cast<Meeting*>(room) != nullptr) {
						if (roomBreakoutChairs == room->getAmountOfSeats() && room->isAvaliable() && computersAvaliable == 0 && !printerAvailable && !smartboardAvailable) {
							cout << room->toString() << endl;
							counter++;
						}
					}
					else if (dynamic_cast<Conference*>(room) != nullptr) {
						if (roomBreakoutChairs == room->getAmountOfSeats() && room->isAvaliable() && !printerAvailable && smartboardAvailable == ((Conference*)room)->isSmartboardAvaliable() && computersAvaliable == 0) {
							cout << room->toString() << endl;
							counter++;
						}
					}
				}
				if (counter == 0) {
					cout << "No Room Found!" << endl;
				}
			}
			break;
		case 0:
			gottenRequirements = true;
		default:
			cout << "Not A Choice!" << endl;
			break;
		}
		if (gottenRequirements) {
			if (roomBreakoutChairs == -1 || computersAvaliable == -1) {
				cout << "Data Hasn't Been Entered For Either Breakout Chairs or Computers" << endl;
				gottenRequirements == false;
			}
			else {
				break;
			}
		}
	}
	Room* bookedRoom;
	string roomName = "";
	while (true) {
		try {
			cout << "Enter Name of Room Being Booked:" << endl;
			cin >> roomName;
		}
		catch (exception e) {
			menu();
		}
		for (Room* room : rooms){
			if (room->getName().compare(roomName)) {
				bookedRoom = room;
			}
		}
		if (bookedRoom == nullptr) {
			cout << "Room Not Found!" << endl;
		}
		else if (roomName.compare("0")) {
			menu();
		}
		else {
			break;
		}
	}
	return bookedRoom;
}
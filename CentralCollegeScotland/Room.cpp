#include "Room.h"
using namespace std;

Room::Room()
{
	name = "";
	amountOfSeats = 0;
	isAvailable = false;
}

Room::Room(std::string theName, int seats, bool avaliableOrNot) {
	name = theName;
	amountOfSeats = seats;
	isAvailable = avaliableOrNot;
}

Room::~Room(void) {
	cout << "Room Deleted!";
}

std::string Room::getName(void) {
	return name;
}

void Room::setName(std::string theName) {
	name = theName;
}

int Room::getAmountOfSeats(void) {
	return amountOfSeats;
}

void Room::setAmountOfSeats(int amountSeats) {
	amountOfSeats = amountSeats;
}

bool Room::isAvaliable(void) {
	return isAvailable;
}

void Room::setAvailable(bool Availability) {
	isAvailable = Availability;
}

std::string Room::toString(void)
{
	ostringstream buffer;
	if (isAvailable == true) {
		buffer << "The Name of Room is: " << name << " and the amount of seats is " << amountOfSeats << " and is currently available!" << endl;
		return buffer.str();
	}
	else {
		buffer << "The Name of Room is: " << name << " and the amount of seats is " << amountOfSeats << " and is currently unavailable!" << endl;
		return buffer.str();
	}
}



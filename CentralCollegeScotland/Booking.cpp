
#include "Booking.h"

Booking::Booking(time_t theDate, time_t time, int theDuration, Room theRoom, Client theClient)
{
	dateOfOccupation = theDate;
	startingTime = time;
	durationOfRoomUse = theDuration;
	bookedRoom = theRoom;
	bookedClient = theClient;
}

Booking::~Booking(void)
{
	cout << "Deleted Booking Object!" << endl;
}

time_t Booking::getDateOfOccupation(void)
{
	return dateOfOccupation;
}

void Booking::setDateOfOccupation(time_t theDateOccupation)
{
	dateOfOccupation = theDateOccupation;
}

time_t Booking::getStartingTime(void)
{
	return startingTime;
}

void Booking::setStartingTime(time_t time)
{
	startingTime = time;
}

int Booking::getDurationOfRoomUse(void)
{
	return durationOfRoomUse;
}

void Booking::setDurationOfRoomUse(int theDuration)
{
	durationOfRoomUse = theDuration;
}

Room Booking::getBookedRoom(void)
{
	return bookedRoom;
}

void Booking::setBookedRoom(Room theRoom)
{
	bookedRoom = theRoom;
}

Client Booking::getBookedClient(void)
{
	return bookedClient;
}

void Booking::setBookedClient(Client theClient)
{
	bookedClient = theClient;
}

#ifndef BOOKING_H
#define BOOKING_H

#include <iostream>
#include <ctime>
#include "Client.h"
#include "Room.h"

using namespace std;

class Booking {
private:
	time_t dateOfOccupation;
	time_t startingTime;
	int durationOfRoomUse;
	Room bookedRoom;
	Client bookedClient;
public:
	Booking(time_t theDate, time_t time, int theDuration, Room theRoom, Client theClient);
	~Booking(void);
	time_t getDateOfOccupation(void);
	void setDateOfOccupation(time_t theDateOccupation);
	time_t getStartingTime(void);
	void setStartingTime(time_t time);
	int getDurationOfRoomUse(void);
	void setDurationOfRoomUse(int theDuration);
	Room getBookedRoom(void);
	void setBookedRoom(Room theRoom);
	Client getBookedClient(void);
	void setBookedClient(Client theClient);
};

#endif // !BOOKING_H
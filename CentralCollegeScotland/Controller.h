#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "Model.h"
#include "Room.h"
#include "ComputerLab.h"
#include "Meeting.h"
#include "Conference.h"
#include "cereal/archives/binary.hpp"
#include <iostream>

using namespace std;

class Controller
{
private:
	Model model;
	std::string  nameOfClient;
	std::string clientEmailAddress;
	void menu(void);
	Room* getRoomBeingBooked();
	void bookRoom(void);
	void updateBooking(void);
	int validateNumber(string); 
	void cancelBooking(void);
	void storeRoomDetails(void);
	void updateRoomDetails(void);
	void udateRoomAvailability(Room theRoom);
	void checkSmartboardAvailability(Conference theConference);
	void storeClientDetails(void);
	void updateClientDetails(void);
	void removeClientDetails(void);
	void generateReport(void);
	//bool loadFromFile(void);
	//void saveFromFile(void);
public:
	void checkDetails(void);
};

#endif // !CONTROLLER_H
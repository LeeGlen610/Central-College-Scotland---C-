#ifndef MODEL_H
#define MODEL_H

#include <iostream>
#include <map>
#include <vector>
#include "Client.h"
#include "Booking.h"
#include "Room.h"

using namespace std;

class Model
{
private:
	std::map<int, Client> clientMap;
	std::map<int, Room> roomMap;
	std::vector<Booking> bookings;
	void populateBookinds(void);
	std::map<int, Client> getClientMap(void);
	void setClientMap(std::map<int, Client> clients);
public:
	void populateClients(void);
	void populateRooms(void);
	bool checkDetails(std::string clientName, std::string emailAddress);
	void changeRoomAvailability(Room theRoom);
	void addBooking(Booking theBooking);
	void addRoom(Room theRoom);
	void addClient(Client theClient);
	std::vector<Room*> getRoom(void);
	Client getClient(std::string clientName, std::string emailAddress);
	std::vector<Client> getClient(void);
	void removeClient(std::string clientName, std::string emailAddress);
	std::vector<Booking> getBookings(void);
	void removeBooking(std::string clientName, std::string emailAddress);
	friend std::istream& operator >> (std::istream& in, Model& obj);
};

#endif // !MODEL_H
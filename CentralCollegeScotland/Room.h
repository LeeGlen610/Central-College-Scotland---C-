#ifndef ROOM_H
#define ROOM_H
#include <iostream>
#include <iomanip>
#include <sstream>

using namespace std;

class Room {
private:
	std::string name;
	int amountOfSeats;
	bool isAvailable;
public:
	Room();
	Room(std::string theName, int seats, bool avaliableOrNot);
	virtual ~Room(void);
	std::string getName(void);
	void setName(std::string theName);
	int getAmountOfSeats(void);
	void setAmountOfSeats(int amountSeats);
	bool isAvaliable(void);
	void setAvailable(bool Avaliability);
	virtual std::string toString(void);
};


#endif // !ROOM_H

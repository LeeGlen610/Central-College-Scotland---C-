#include "Conference.h"

Conference::Conference(void)
{
	this->setName("");
	this->setAmountOfSeats(0);
	this->setAvailable(false);
	smartboardAvaliable = false;
}

Conference::Conference(string name, int amountOfSeats, bool isAvaliable, bool smartboard)
{
	this->setName(name);
	this->setAmountOfSeats(amountOfSeats);
	this->setAvailable(isAvaliable);
	smartboardAvaliable = smartboard;
}

Conference::~Conference(void)
{
	cout << "Deleted Conference Object!" << endl;
}

bool Conference::isSmartboardAvaliable(void)
{
	return smartboardAvaliable;
}

void Conference::setSmartboardAvaliable(bool avaliablity)
{
	smartboardAvaliable = avaliablity;
}

std::string Conference::toString(void)
{
	ostringstream buffer;
	buffer << Room::toString() << endl;
	return buffer.str();
}

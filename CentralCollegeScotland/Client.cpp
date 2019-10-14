
#include "Client.h"

Client::Client(void)
{
	name = "";
	emailAddress = "";
	contactNumber = "";
}

Client::Client(string theName, string email, string number)
{
	name = theName;
	emailAddress = email;
	contactNumber = number;
}

Client::~Client(void)
{
	cout << "Deleted Client Object!" << endl;
}

string Client::getName(void)
{
	return name;
}

void Client::setName(string nam)
{
	name = nam;
}

string Client::getEmailAddress(void)
{
	return emailAddress;
}

void Client::setEmailAddress(string email)
{
	emailAddress = email;
}

string Client::getContactNumber(void)
{
	return contactNumber;
}

void Client::setContactNumber(string number)
{
	contactNumber = number;
}


#ifndef CLIENT_H
#define CLIENT_H

#include <iostream>;


using namespace std;

class Client {
private:
	string name;
	string emailAddress;
	string contactNumber;
public:
	Client(void);
	Client(string theName, string email, string number);
	~Client(void);
	string getName(void);
	void setName(string nam);
	string getEmailAddress(void);
	void setEmailAddress(string email);
	string getContactNumber(void);
	void setContactNumber(string number);
};

#endif // !
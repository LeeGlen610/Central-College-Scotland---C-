#ifndef CONFERENCE_H
#define CONFERENCE_H
#include "Room.h"
class Conference :
	public Room
{
private:
	bool smartboardAvaliable;
public:
	Conference(void);
	Conference(string name, int amountOfSeats, bool isAvaliable, bool smartboard);
	virtual ~Conference(void);
	bool isSmartboardAvaliable(void);
	void setSmartboardAvaliable(bool avaliablity);
	virtual std::string toString(void);
};

#endif;
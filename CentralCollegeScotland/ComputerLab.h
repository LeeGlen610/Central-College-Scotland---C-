#ifndef COMPUTERLAB_H
#define COMPUTERLAB_H
#include "Conference.h"
class ComputerLab :
	public Conference
{
private:
	bool printerAvailable;
	int amountOfComputers;
public:
	ComputerLab(string name, int amountOfSeats, bool isAvailable, bool smartboardAvailable, bool printerAvailable, int amountOfComputers);
	~ComputerLab(void);
	bool isPrinterAvailable(void);
	void setPrinterAvailable(bool printersAvailability);
	int getAmountOfComputers(void);
	void setAmountOfComputers(int computers);
};

#endif // !COMPUTERLAB_H
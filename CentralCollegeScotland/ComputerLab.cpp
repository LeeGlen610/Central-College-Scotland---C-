#include "ComputerLab.h"

ComputerLab::ComputerLab(string name, int amountOfSeats, bool isAvailable, bool smartboardAvailable, bool printerAvailable, int amountOfComputers)
{
	this->setName(name);
	this->setAmountOfSeats(amountOfSeats);
	this->setAvailable(isAvailable);
	this->setSmartboardAvaliable(smartboardAvailable);
	this->printerAvailable = printerAvailable;
	this->amountOfComputers = amountOfComputers;
}

ComputerLab::~ComputerLab(void)
{
	cout << "Deleted Computer Lab" << endl;
}

bool ComputerLab::isPrinterAvailable(void)
{
	return printerAvailable;
}

void ComputerLab::setPrinterAvailable(bool printersAvailability)
{
	printerAvailable = printersAvailability;
}

int ComputerLab::getAmountOfComputers(void)
{
	return amountOfComputers;
}

void ComputerLab::setAmountOfComputers(int computers)
{
	amountOfComputers = computers;
}

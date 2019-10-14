
#include "Conference.h"

int main() {

	Conference* room = new Conference("Hello", 21, true, true);
	
	cout << room->toString();

	return 0;
}
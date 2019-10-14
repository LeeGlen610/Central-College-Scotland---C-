#include "Model.h"

std::map<int, Client> Model::getClientMap(void)
{
	return clientMap;
}

void Model::setClientMap(std::map<int, Client> clients)
{
	clientMap = clients;
}


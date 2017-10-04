#include <iostream>
#include <fstream>
#include <list>
#include <cstring>

using namespace std;

int main(int argc, char *argv[]) {
	char filename[50];
	if(argc < 2) {
		cout << "Entre com o nome do arquivo: ";
		cin >> filename;
	} else {
		strcpy(filename, argv[1]);
	}
	
	ifstream file;
	file.open(filename);
	if(file.is_open()) {
		int n;
		file >> n;
		list<int> lista[n];
		int size, vertice;
		for(int i = 0; i < n; ++i) {
			file >> size;
			for(int j = 0; j < size; ++j) {
				file >> vertice;
				lista[i].push_back(vertice);
			}
		}
		
		file.close();
		
		for(int i = 0; i < n; ++i) {
			cout << "g(" << i << ") = " << lista[i].size() << endl;
		}
		
	} else {
		cout << "Unable to open file :(" << endl;
	}
	return 0;
}
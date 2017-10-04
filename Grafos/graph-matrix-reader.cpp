#include <iostream>
#include <fstream>
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
		bool matriz[n][n];
		
		file.ignore();
		file.read((char*)&matriz[0][0], n*n);
		file.close();
		
		cout << "graph:" << endl;
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				if(matriz[i][j]) {
					cout << "o ";
				} else {
					cout << ". ";
				}
			}
			cout << endl;
		}
		
	} else {
		cout << "Unable to open file :(" << endl;
	}
	
	
	return 0;
}
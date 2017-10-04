#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

bool realIsConnected(bool **matriz, int n, bool conectados[], bool novos[]) {
	bool haNovos = false;
	for(int i = 0; i < n; ++i) {
		if(novos[i]) {
			haNovos = true;
			break;
		}
	}
	if(!haNovos) {
		bool isConnected = true;
		for(int i = 0; i < n; ++i) {
			if(!conectados[i]) {
				isConnected = false;
				break;
			}
		}
		return isConnected;
	} else {
		bool proximos[n];
		for(int i = 0; i < n; ++i) {
			proximos[i] = false;
		}
		for(int i = 0; i < n; ++i) {
			if(novos[i]) {
				for(int j = 0; j < n; ++j) {
					if(matriz[i][j] && !conectados[j]) {
						proximos[j] = conectados[j] = true;
					}
				}
			}
		}
		return realIsConnected(matriz, n, conectados, proximos);
	}
}

bool isConnected(bool **matriz, int n) {
	bool conectados[n], novos[n];

	conectados[0] = true;
	for(int i = 1; i < n; ++i) {
		conectados[i] = false;
	}

	novos[0] = true;
	for(int i = 1; i < n; ++i) {
		novos[i] = false;
	}

	return realIsConnected(matriz, n, conectados, novos);
}

int main(int argc, char *argv[]) {
	char filename[50];
	if(argc < 2) {
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

		bool **matriz_dinamica;
		matriz_dinamica = new bool *[n];
		for(int i = 0; i < n; ++i) {
			matriz_dinamica[i] = new bool [n];
			for(int j = 0; j < n; ++j) {
				matriz_dinamica[i][j] = matriz[i][j];
			}
		}

		if (isConnected(matriz_dinamica, n)) {
			cout << "O grafo é conexo." << endl;
		} else {
			cout << "O grafo não é conexo." << endl;
		}

	} else {
		cout << "Unable to open file :(" << endl;
	}

	return 0;
}

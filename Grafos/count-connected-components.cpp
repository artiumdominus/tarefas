#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int realCountConnectedComponents(bool **matriz, int n, bool conectados[], bool novos[]) {
	bool haNovos = false;
	for(int i = 0; i < n; ++i) {
		if(novos[i]) {
			haNovos = true;
			break;
		}
	}
	if(!haNovos) {
		bool wasAll = true;
		int i;
		for(i = 0; i < n; ++i) {
			if(!conectados[i]) {
				wasAll = false;
				break;
			}
		}
		if(wasAll) {
			return 1;
		} else {
			conectados[i] = novos[i] = true;
			return realCountConnectedComponents(matriz, n, conectados, novos) + 1;
		}
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
		return realCountConnectedComponents(matriz, n, conectados, proximos);
	}
}

int countConnectedComponents(bool **matriz, int n) {
	bool conectados[n], start[n];

	conectados[0] = true;
	for(int i = 1; i < n; ++i) {
		conectados[i] = false;
	}

	start[0] = true;
	for(int i = 1; i < n; ++i) {
		start[i] = false;
	}

	return realCountConnectedComponents(matriz, n, conectados, start);
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

		cout << "O grafo possui " << countConnectedComponents(matriz_dinamica, n)
			<< " componentes conexas." << endl;

	} else {
		cout << "Unable to open file :(" << endl;
	}

	return 0;
}

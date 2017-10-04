#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <cstring>

using namespace std;

int main() {
	char rotulo[50], resposta;
	cout << "Entre com o rótulo do arquivo: ";
	cin >> rotulo;
	
	do {
		cout << "Matriz (m) ou Lista (l) ?: ";
		cin >> resposta;
		if(resposta != 'm' && resposta != 'l') {
			cout << "A resposta deve ser 'm' ou 'l'.";
		}
	} while(resposta != 'm' && resposta != 'l');
	
	if(resposta == 'm') {
		strcat(rotulo, ".matriz.dat");
	} else if (resposta == 'l') {
		strcat(rotulo, ".lista.dat");
	}
	
	ofstream file;
	file.open(rotulo);
	if(file.is_open()) {
		int n;
		const int mil = 1000;
		cout << "Entre com o número de vértices: ";
		cin >> n;

		bool matriz[n][n];

		float densidade;
		do { 
			cout << "Entre com a densidade: ";
			cin >> densidade;
			if(densidade < 0 || densidade > 1) {
				cout << "A densidade deve ser um valor entre 0 e 1." << endl;
			}
		} while (densidade < 0 || densidade > 1);
		
		srand(time(NULL));
		for(int i = 0; i < n; ++i) {
			matriz[i][i] = false;
			for(int j = i + 1; j < n; ++j) {
				matriz[i][j] = matriz[j][i] = rand() % mil < mil * densidade;
			}
		}
		
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
		
		file << n << " ";
		if(resposta == 'm') {
			file.write((char*)&matriz[0][0], n*n);
			cout << "Matriz de adjacência gerada em " << rotulo << endl;
		} else if(resposta == 'l') {
			int sum;
			for(int i = 0; i < n; ++i) {
				sum = 0;
				for(int j = 0; j < n; ++j) {
					sum += matriz[i][j];
				}
				file << sum << " ";
				for(int j = 0; j < n; ++j) {
					if(matriz[i][j]) {
						file << j << " ";
					}
				}
			}
			cout << "Lista de adjacência gerado em " << rotulo << endl;
		}
		
		file.close();
		
	} else {
		cout << "Unable to open file :(";
	}
	
	return 0;
}

#include <iostream>
#include <fstream>

using namespace std;

int main() {
    int s = 8;
    int n = s*s;
    bool matriz[n][n];
    int i, i1, i2, j, j1, j2;
    
    for(i = 0; i < n; ++i) {
        for(j = 0; j < n; ++j) {
            matriz[i][j] = false;
        }
    }
    
    for(i1 = 0; i1 < s; ++i1) {
        for(j1 = 0; j1 < s; ++j1) {
            for(i2 = 0; i2 < s; ++i2) {
                for(j2 = 0; j2 < s; ++j2) {
                    if( ((i2 == i1-1 || i2 == i1+1) && (j2 == j1-2 || j2 == j1+2)) ||
                        ((i2 == i1-2 || i2 == i1+2) && (j2 == j1-1 || j2 == j1+1)) ) {
                    i = 8*i1 + j1;
                    j = 8*i2 + j2;
                    matriz[i][j] = true;    
                    }
                }
            }
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
    
    ofstream file;
    file.open("horse.matriz.dat");
    if(file.is_open()) {
        file << n << " ";
        file.write((char*)&matriz[0][0], n*n);
        file.close();
        cout << "Matriz de adjacência salva em \"cavalo.matriz.dat\"" << endl;
    } else {
        cout << "Falha em abrir o arquivo." << endl;
    }
    return 0;
}

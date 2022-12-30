#include "adsBeispiele.h"

//Pfad: Blatt7/Aufgabe1/MemoryTest.h Blatt7/Aufgabe1/adsBeispiele.h
// Blatt7/Aufgabe1/adsBeispiele.c Blatt7/Aufgabe1/CUnit.h
// Blatt7/Aufgabe1/CUnit.c Blatt7/Aufgabe1/simpleMain.c
// Blatt7/Aufgabe2/vector.c Blatt7/Aufgabe2/vector.h

unsigned long int factorial(unsigned int x){
    return x<=1 ? 1:x* factorial(x-1);
}

void eratosthenes(bool* array, unsigned int length){
    unsigned int i,j,c;

    for(i = 0; i < length; ++i){
        array[i] = true;
    }

    for(c = 2; c < length; ++c){
        if(array[c-2]){
            for(j = c; j*c < length + 2; ++j){
                array[(j*c) - 2] = false;
            }
        }
    }
}

int mystery(int x, int z){
    if (x==0) {
        return 0;
    }
    if (x<0) {
        return mystery(-x, -z);
    }
    return mystery(x-1, z)+z;
}


unsigned int ggt(unsigned int a, unsigned int b) {
    if (b==0) {
        return a;
    }
    else {
    return ggt(b, a%b);
    }
}

void matrixMult(double** a,double** b,double** c,unsigned int n){
    int i,j,k;
    for (i = 0; i < n; ++i) {
        for (j = 0;j < n; ++j) {
            c[i][j] = 0;
        }
    }

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            for (k = 0; k < n; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
}
#include "adsBeispiele.h"
#include <stdio.h>

int main(int argc, char** args){
    printf("Fakultät von 5: %lu\n",factorial(5));

    bool ar [10] = {false,false,false,false,false,false,false,false,false,false};
    eratosthenes(ar,10);
    printf("11 ist %s Primzahl.\n",ar[9]?"eine":"keine");

    printf("mystery(10,3) = %d\n",mystery(10,3));

    printf("ggt(10,4) = %d\n",ggt(10,4));

    double a0 [] = {1,2,3};
    double a1 [] = {4,1,0};
    double a2 [] = {5,2,-3};
    double* a []= {a0,a1,a2};

    double b0 [] = {2,1,-3};
    double b1 [] = {-1,0,4};
    double b2 [] = {1,2,-4};
    double* b [] = {b0,b1,b2};

    double c0 [] = {0,0,0};
    double c1 [] = {0,0,0};
    double c2 [] = {0,0,0};
    double* c [] = {c0,c1,c2};
    matrixMult(a,b,c,3);

    printf("Position c[0][1] = %f\n",c[0][1]);

    return 0;
}


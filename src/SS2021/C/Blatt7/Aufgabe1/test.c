#include "CUnit.h"
#include "adsBeispiele.h"
#include <stdio.h>

int main(int argc, char** args){
  printHeader();

  testStart("fac1");
  assertIntEq("Fakultät von 5",120,factorial(5));
  testEnd();

  testStart("fac2");
  assertIntEq("Fakultät von 0",1,factorial(0));
  testEnd();

  testStart("fac3");
  assertIntEq("Fakultät von 1",1,factorial(1));
  testEnd();

  testStart("eratosthenes 1");
  bool ar [10] = {false,false,false,false,false,false,false,false,false,false}; 
  eratosthenes(ar,10); 
  assertTrue("array[0] muss true sein, denn 2 ist Primzahl",ar[0]);
  assertTrue("array[1] muss true sein, denn 3 ist Primzahl",ar[1]);
  assertTrue("array[2] muss false sein, denn 4 ist keine Primzahl",!ar[2]);
  assertTrue("array[3] muss true sein, denn 5 ist Primzahl",ar[3]);
  assertTrue("array[4] muss false sein, denn 6 ist keine Primzahl",!ar[4]);
  assertTrue("array[5] muss true sein, denn 7 ist Primzahl",ar[5]);
  assertTrue("array[6] muss false sein, denn 8 ist keine Primzahl",!ar[6]);
  assertTrue("array[7] muss false sein, denn 9 ist keine Primzahl",!ar[7]);
  assertTrue("array[8] muss false sein, denn 10 ist keine Primzahl",!ar[8]);
  assertTrue("array[9] muss true sein, denn 11 ist Primzahl",ar[9]);
  testEnd();

  testStart("eratosthenes 2");
  bool ar2 [100];
  eratosthenes(ar2,100);
  assertTrue("array[95] muss true sein, denn 97 ist eine Primzahl",ar2[95]);
  assertTrue("array[96] muss false sein, denn 98 ist keine Primzahl",!ar2[96]);
  assertTrue("array[97] muss false sein, denn 99 ist keine Primzahl",!ar2[97]);
  assertTrue("array[98] muss false sein, denn 100 ist keine Primzahl",!ar2[98]);
  assertTrue("array[99] muss true sein, denn 101 ist eine Primzahl",ar2[99]);
  testEnd();
  

  
  testStart("mystery1");
  assertIntEq("mystery(10,3)",30,mystery(10,3));
  testEnd();
  testStart("mystery2");
  assertIntEq("mystery(10,-3)",-30,mystery(10,-3));
  testEnd();
  testStart("mystery3");
  assertIntEq("mystery(0,-3)",-0,mystery(0,-3));
  testEnd();
  testStart("mystery4");
  assertIntEq("mystery(10,0)",-0,mystery(10,0));
  testEnd();
  testStart("mystery5");
  assertIntEq("mystery(-10,3)",-30,mystery(-10,3));
  testEnd();
  testStart("mystery6");
  assertIntEq("mystery(-10,-3)",30,mystery(-10,-3));
  testEnd();

  testStart("ggt1");
  assertIntEq("ggt(10,4)",2,ggt(10,4));
  testEnd();

  testStart("ggt2");
  assertIntEq("ggt(4,10)",2,ggt(4,10));
  testEnd();

  testStart("ggt3");
  assertIntEq("ggt(0,10)",10,ggt(0,10));
  testEnd();

  testStart("ggt4");
  assertIntEq("ggt(51,99)",3,ggt(51,99));
  testEnd();

  testStart("ggt5");
  assertIntEq("ggt(1,1)",1,ggt(1,1));
  testEnd();
  
  testStart("matrixMult");
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

  assertIntEq("Position c[0][0] falsch nach Matrix-Multiplikation",3,c[0][0]);
  assertIntEq("Position c[0][1] falsch nach Matrix-Multiplikation",7,c[0][1]);
  assertIntEq("Position c[0][2] falsch nach Matrix-Multiplikation",-7,c[0][2]);
  assertIntEq("Position c[1][0] falsch nach Matrix-Multiplikation",7,c[1][0]);
  assertIntEq("Position c[1][1] falsch nach Matrix-Multiplikation",4,c[1][1]);
  assertIntEq("Position c[1][2] falsch nach Matrix-Multiplikation",-8,c[1][2]);
  assertIntEq("Position c[2][0] falsch nach Matrix-Multiplikation",5,c[2][0]);
  assertIntEq("Position c[2][1] falsch nach Matrix-Multiplikation",-1,c[2][1]);
  assertIntEq("Position c[2][2] falsch nach Matrix-Multiplikation",5,c[2][2]);
  
  testEnd();
  
  printResults();
  printStorage();
  
  printFoot();
  return 0;
}
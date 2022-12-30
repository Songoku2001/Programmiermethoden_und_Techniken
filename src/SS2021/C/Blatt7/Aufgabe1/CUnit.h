#ifndef __CUnit__H__
#define __CUnit__H__

#include <stdbool.h>
//#include "String.h"


#define assertTrue(m,v)(assertTrueLine(__LINE__,m,v))
#define assertIntEq(m,v1,v2)(assertIntEqLine(__LINE__,m,v1,v2))
// #define assertStringEq(m,v)(assertStringEqLine(__LINE__,m,v))

extern unsigned int errorcount;
extern unsigned int testcount;

bool assertTrueLine(int line,char* message, bool val);
bool assertIntEqLine(int line,char* message, int expected, int found);
// bool assertStringEqLine(int line,char* expected,String this);

void printHeader();
void printFoot();
void printResults();
void printStorage();

void testStart(char* name);
void testEnd();
#endif
#include "CUnit.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "MemoryTest.h"


unsigned int errorcount = 0;
unsigned int testcount = 0;
unsigned int speicherAlloc=0;
unsigned int speicherFree=0;

bool assertTrueLine(int line,char* message, bool val){
    testcount++;
    if (!val){
      errorcount++;
      printf("    <failure message=\"true expected\" type=\"AssertionFailedError\">\n");
      printf("       (%d) fail: %s\n",line,message);
      printf("    </failure>\n");
      fflush(stdout);
    }
    return val;
}

bool assertIntEqLine(int line,char* message, int expected, int found){
  testcount++;
  if (expected!=found){
      errorcount++;
      printf("    <failure type=\"AssertionFailedError\">\n");
      printf("      (%d) fail: %s, expected [%d] found [%d]\n",line,message,expected,found);
      printf("    </failure>\n");
      fflush(stdout);
      return false;
  }
  return true;
}


bool assertStringEqLine(int line,char* message, char* expected,char* this){
  testcount++;
  bool result = strcmp(expected,this)==0;
  if (!result){
    errorcount++;
    printf("    <failure type=\"AssertionFailedError\">\n");
    printf("      (%d) fail: %s, expected [<![CDATA[%s]]>] but found [<[CDATA[%s]>]\n",line,message,expected,this);
    printf("    </failure>\n");
    fflush(stdout);
  }
  return result;
}

bool alCmp(AL this,AL that,EQ eq){
  if (this.size!=that.size) return false;
  int i;
  for (i=0;i<this.size;i++)if (!eq(alGet(this,i),alGet(that,i))) return false;
  return true;
}

void alPrint(AL this,Print p){
  printf("[");
  int i;
  for(i=0;i<this.size;i++){
    if(i>0) printf(", ");
    p(alGet(this,i));
  }
  printf("]");
}

bool assertALEqLine(int line,char* message,AL expected,AL this,EQ eq, Print p){
  testcount++;
  bool result = alCmp(expected,this,eq);
  if (!result){
    errorcount++;
    printf("    <failure type=\"AssertionFailedError\">\n");
    printf("      (%d) fail: %s, expected ",line,message);
    alPrint(expected,p);
    printf(" but found ");
    alPrint(this,p);
    printf("\n");
    printf("    </failure>\n");
    fflush(stdout);
  }
  return result;
}


void printHeader(){
  printf("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
  printf("<testsuite>\n"); 
}

void printResults(){
  printf("  <results errors=\"%d\" failures=\"%d\" tests=\"%d\"/>\n",0,errorcount,testcount);
}

void printStorage(){
  printf("  <storage allocated=\"%d\" free=\"%d\" diff=\"%d\"/>\n",speicherAlloc,speicherFree,speicherAlloc-speicherFree);
}
void printFoot(){
  printf("</testsuite>");
  fflush(stdout);
}

void testStart(char* name){
  printf("  <testcase name=\"%s\">\n",name);
  fflush(stdout);
}

void testEnd(){
  printf("  </testcase>\n");
  fflush(stdout);
}
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
  }
  return val;
}

bool assertIntEqLine(int line,char* message, int expected, int found){
  testcount++;
  if (expected!=found){
    errorcount++;
    printf("    <failure message=\"expected:&lt;%d&gt; but was:&lt;%d&gt;\" type=\"AssertionFailedError\">\n",expected,found);
    printf("      (%d) fail: %s, expected %d found %d\n",line,message,expected,found);
    printf("    </failure>\n");
    return false;
  }
  return true;
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
}

void testStart(char* name){
  printf("  <testcase name=\"%s\">\n",name);
}

void testEnd(){
  printf("  </testcase>\n");
}
/*
bool assertStringEqLine(int line,char* expected,String this){
  testcount++;
  char* out = malloc(sizeof(_char)* (this.length+25));
  sprintf(out,"{length=%d;data=\"%s\"}",this.length,this.data);  
  bool result = strcmp(expected,out)==0;
  if (!result){
    errorcount++;
    printf("(%d)  fail: expected %s  but found %s\n",line,expected,out);
  }
  free(out);
  return result;
  }*/
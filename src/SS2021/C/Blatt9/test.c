#include "MemoryTest.h"

#include "CUnit.h"
#include "AL.h"
#include <stdio.h>

bool eqIntObject(Object o1,Object o2){
  int* i1 = (int*)o1;
  int* i2 = (int*)o2;
  return *i1==*i2;
}

int cmpIntObject(Object o1,Object o2){
  int* i1 = (int*)o1;
  int* i2 = (int*)o2;
  return *i1-*i2;
}

void printIntObject(Object o){
  int* i = (int*)o;
  printf("%d ",*i);
}

int is[]={1,2,3,4,5,6,7,8,9};
int js[]={9,8,7,6,5,4,3,2,1};

AL xs;
AL ys;

void initXs(){
  xs = alNew();
  int i;
  for (i=0;i<9;i++)
    alAdd(&xs,is+i);
}
void initYs(){
  ys = alNew();
  int i;
  for (i=0;i<9;i++)
    alAdd(&ys,js+i);
}

bool fuenferTeiler(Object o){
  int* i =(int*)o;
  return *i % 5 == 0;
}
bool zehnerTeiler(Object o){
  int* i =(int*)o;
  return *i % 10 == 0;
}

int main(int argc, char** args){
  printHeader();

  testStart("get 1");
  initXs();
  int* i1 = alGet(xs,5);
  assertIntEq("get holt falsches Elenent", 6, *i1);
  testEnd();
  alDelete(xs);

  testStart("remove 1");
  initXs();
  ys = alNew();
  alRemove(&xs,0);
  int i;
  for (i=1;i<9;i++)
    alAdd(&ys,is+i);
  assertALEq("remove des ersten Elements falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("remove 2");
  initXs();
  ys = alNew();
  alRemove(&xs,8);

  for (i=0;i<8;i++)
    alAdd(&ys,is+i);
  assertALEq("remove des letzten Elements falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("remove 3");
  initXs();
  ys = alNew();
  alRemove(&xs,5);

  for (i=0;i<9;i++)
    if (i!=5)alAdd(&ys,is+i);
  assertALEq("remove des sechsten Elements falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);


  testStart("addALL 1");
  initXs();
  ys = alNew();
  alAddAll(&xs,ys);
  assertALEq("addAll mit leerer Liste falsch",xs,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("addALL 2");
  initXs();
  ys = alNew();
  alAddAll(&ys,xs);
  assertALEq("addAll auf leerer Liste falsch",xs,ys,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("addALL 3");
  initXs();
  ys = alNew();
  alAddAll(&xs,xs);
  for (i=0;i<9;i++) alAdd(&ys,is+i);
  for (i=0;i<9;i++) alAdd(&ys,is+i);

  assertALEq("addAll falsch",xs,ys,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);


  testStart("containWith");
  initXs();
  assertTrue("sollte 5 teilbare Zahl enthalten",alContainsWith(xs,fuenferTeiler));
  testEnd();
  alDelete(xs);

  testStart("containWith");
  initXs();
  assertTrue("sollte keine 10 teilbare Zahl enthalten",!alContainsWith(xs,zehnerTeiler));
  testEnd();
  alDelete(xs);

  testStart("containWith");
  xs = alNew();
  assertTrue("leere Liste sollte keine 5 teilbare Zahl enthalten",!alContainsWith(xs,fuenferTeiler));
  testEnd();
  alDelete(xs);
 
  testStart("sublist 1");
  initXs();
  AL r = alSublist(xs,0,10);
  assertALEq("sublist falsch",xs,r,eqIntObject,printIntObject);
  alDelete(r);
  r = alSublist(xs,0,100);
  assertALEq("sublist falsch",xs,r,eqIntObject,printIntObject);
  alDelete(r);
  r = alSublist(xs,2,1);
  ys = alNew();
  alAdd(&ys,is+2);
  assertALEq("sublist falsch",ys,r,eqIntObject,printIntObject);
  testEnd();
  alDelete(r);
  alDelete(ys);
  alDelete(xs);

  testStart("sublist 2");
  xs = alNew();
  r = alSublist(xs,0,10);
  assertALEq("sublist falsch",xs,r,eqIntObject,printIntObject);
  testEnd();
  alDelete(r);
  alDelete(xs);
  
  testStart("sublist 3");
  initXs();
  r = alSublist(xs,4,3);
  ys = alNew();
  for (i=4;i<7;i++)alAdd(&ys,is+i);
  assertALEq("sublist falsch",ys,r,eqIntObject,printIntObject);
  testEnd();
  alDelete(r);
  alDelete(xs);
  alDelete(ys);


  testStart("startsWith 1");
  initXs();
  ys = alNew();
  assertTrue("jede Liste startet mit leerer Liste!",alStartsWith(xs,ys,eqIntObject));  
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("startsWith 2");
  initXs();
  assertTrue("jede Liste startet mit sich selbst!",alStartsWith(xs,xs,eqIntObject));  
  testEnd();
  alDelete(xs);

  testStart("startsWith 3");
  initXs();
  ys = alNew();
  for (i=0;i<7;i++)alAdd(&ys,is+i);
  assertTrue("Präfix nicht erkannt",alStartsWith(xs,ys,eqIntObject));  
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("startsWith 4");
  initXs();
  ys = alNew();
  assertTrue("die leere Liste hat nur sich als Präfix",!alStartsWith(ys,xs,eqIntObject));  
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("startsWith 5");
  initXs();
  initYs();
  assertTrue("fehlerhafter Präfix erkannt",!alStartsWith(ys,xs,eqIntObject));  
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("startsWith 6");
  ys = alNew();
  assertTrue("jede Liste startet mit leerer Liste!",alStartsWith(ys,ys,eqIntObject));  
  testEnd();
  alDelete(ys);


  testStart("reverse 1");
  initXs();
  initYs();
  alReverse(&xs);
  assertALEq("reverse falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);


  testStart("reverse 2");
  xs = alNew();
  ys = alNew();

  alReverse(&xs);
  assertALEq("reverse falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);


  testStart("sortBy 1");
  initXs();
  initYs();
  alSortBy(&ys,cmpIntObject);
  assertALEq("sortiert falsch",xs,ys,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);


  testStart("sortBy 2");
  initXs();
  ys = alNew();
  for (i=0;i<9;i++)alAdd(&ys,is+i);
 
  alSortBy(&xs,cmpIntObject);
  assertALEq("sortiert falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  testStart("sortBy 3");
  xs = alNew();
  ys = alNew();
  alSortBy(&xs,cmpIntObject);
  assertALEq("sortiert falsch",ys,xs,eqIntObject,printIntObject);
  testEnd();
  alDelete(xs);
  alDelete(ys);

  printResults();
  printStorage();
  
  printFoot();
  return 0;
}
#ifndef AL__H__
#define AL__H__

#include "MemoryTest.h"
#include <stdbool.h>

typedef void* Object;

typedef Object E;

typedef bool Predicate(Object);
typedef bool EQ(Object,Object);
typedef int Comparator(Object,Object);   


typedef struct{
  unsigned int size;
  unsigned int capacity;
  E* store;
}AL;

AL alNew();
void alDelete(AL this);
void alAdd(AL* this, E e);
void alEnlargeStore(AL* this);
void alForEach(AL this, void f(E));
E alFold(AL this, E start, void op(E,E));

E alGet(AL this, int i);
void alRemove(AL* this, int i);
void alAddAll(AL* this, AL that);
bool alContainsWith(AL this,Predicate pred);
AL alSublist(AL this, int i, int l);
bool alStartsWith(AL this,AL that,EQ eq);  
bool alEndsWith(AL this,AL that,EQ eq);
void alReverse(AL* this);
void alInsert(AL* this,int i, E e);
void alSortBy(AL* this,Comparator cmp);


#endif
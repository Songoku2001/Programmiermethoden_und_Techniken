#include "AL.h"
#include <stdlib.h>

AL alNew(){
    AL this;
    this.size = 0;
    this.capacity = 5;
    this.store = (E*)malloc(this.capacity*sizeof(E));
    return this;
}

void alDelete(AL this){
    free(this.store);
}

void alAdd(AL* this, E e){
    if (this->size>=this->capacity){
        alEnlargeStore(this);
    }
    this->store[this->size++] = e;
}

void alEnlargeStore(AL* this){
    E* newStore = (E*)malloc((5+this->capacity)*sizeof(E));
    unsigned int i;
    for (i=0;i<this->capacity;i++) newStore[i] = this->store[i];
    this->capacity += 5;
    free(this->store);
    this->store = newStore;
}

void alForEach(AL this, void f(E)){
    unsigned int i;
    for (i=0;i<this.size;i++) f(this.store[i]);
}

E alFold(AL this, E start, void op(E,E)){
    unsigned int i;
    for (i=0;i<this.size;i++)
        op(start,this.store[i]);
    return start;
}


/*Aufgaben*/
E alGet(AL this, int i) {
    return this.store[i];
}

void alRemove(AL* this, int i) {
    if(i>=this->size){
        return;
    }
    unsigned int index;
    for (index=i; index < this->size; index++) {
        this->store[index] = this->store[index + 1];
    }
    this->size--;
}

void alAddAll(AL* this, AL that) {
    int i;

    for(i=0; i<that.size; i++) {
        alAdd(this, that.store[i]);
    }
}



bool alContainsWith(AL this,Predicate pred) {
    int i;

    for(i=0; i<this.size; i++) {
        if(pred(this.store[i])) {
            return true;
        }
    }
    return false;
}

AL alSublist(AL this, int i, int l) {
    unsigned int k = i+l > this.size ? this.size - i : l;
    AL result = alNew();
    int index;

    for(index=0; index < k; index++){
        alAdd(&result,this.store[index+i]);
    }
    return result;
}

bool alStartsWith(AL this,AL that,EQ eq) {
    if (that.size > this.size) {
        return false;
    }
    int i;
    for (i=0; i<that.size; i++) {
        if (!eq(alGet(this,i),alGet(that,i))) {
            return false;
        }
    }
    return true;
}

bool alEndsWith(AL this,AL that,EQ eq) {
    if(that.size<this.size) {
        return false;
    }

    int i;
    for(i=0; i<that.size; i++) {
        if(!eq(alGet(this, this.size-that.size+i), alGet(that, i))) {
            return false;
        }
    }
    return true;
}


void alReverse(AL* this) {
    unsigned int i;

    for(i=0; i<this->size/2; i++) {
        E tmp = this->store[i];
        this->store[i] = this->store[this->size-1-i];
        this->store[this->size-1-i] = tmp;
    }
}


void alInsert(AL *this, int i, E e) {
    if (i<0) {
        return;
    }
    if (i>=this->size) {
        alAdd(this, e);
    }

    int j;
    for (j = this->size; j>i; j--) {
        this->store[j] = this->store[j - 1];
    }
    this->store[i] = e;
    this->size++;
}



void alSortBy(AL* this,Comparator cmp) {
    int i,j;
    E tmp;

    for(i=1; i<this->size; i++) {
        for(j=0; j<this->size-i; j++) {
            if(cmp(this->store[j], this->store[j+1])>0) {
                tmp = this->store[j];
                this->store[j] = this->store[j+1];
                this->store[j+1] = tmp;
            }
        }
    }
}
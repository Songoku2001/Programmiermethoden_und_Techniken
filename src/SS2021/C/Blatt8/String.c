#include "String.h"
#include <stdlib.h>

String mkStringFromChars(_char* str){
    sizetype i;
    for (i=0; str[i]!='\0' ; i++) {}          //iterieren bis ich die länge des Strings habe

    String result = mkStringForLength(i);   //das i dann in der methode aufrufen
    for (i=0; i<result.length; i++) {
        result.data[i] = str[i];
    }
    return result;
}

String mkStringForLength(sizetype l){                   //die länge auf dem heap festlegen
    String result;
    result.length = l;                                //sizetype ist die länge unseres Strings
    result.data = malloc((l+1) * sizeof(_char));  //speicherplatz auf dem heap festlegen
    if (result.data == NULL) {                      //damit es zu keinem Überlauf kommt
        abortWithError("NoMemory");
    }
    result.data[l] = '\0';
    return result;
}

void strDelete(String this){
    free(this.data); //liegt auf dem Heap
}

String strFromBool(bool b){
    if(b) {
        return mkStringFromChars("true");
    }
    else {
        return mkStringFromChars("false");
    }
}

String strFromInt(int i){
    String result;
    if(i==0) {
        result = mkStringFromChars("0");
        return result;
    }
    bool negativ = false;
    sizetype j=0, k=0;

    if (i<0) {
        negativ = true;
    }
    if(negativ) {
        i=-i;
    }
    for(k=i; k>0; k=k/10, j++) {}

    if(negativ) {
        j++;
    }
    result = mkStringForLength(j);
    for (j--, k= i; k > 0; k=k/10, j--){
        result.data[j] = '0'+k % 10;
    }
    if (negativ){
        result.data[0] = '-';
    }
    return result;
}

void strPrintLn(String this){
    printf("{length=%d;data=\"%s\"}\n", this.length, this.data);
}

String strAppend(String this,String that){
    String result = mkStringForLength(this.length + that.length);
    sizetype i;

    for(i=0; i<this.length; i++) {
        result.data[i] = this.data[i];
    }
    for(i=0; i<that.length; i++) {
        result.data[i+this.length] = that.data[i];
    }

    return result;
}

String strAppendInt(String this,int i){
    String tmp = strFromInt(i);
    String result = strAppend(this, tmp);
    strDelete(tmp); //tmp löschen weil man das nicht mehr benutzen kann --> toter Speicher --> innere lokale variable
    return result;
}

String strReverse(String this){
    String result = mkStringForLength(this.length);
    sizetype i, index=this.length-1;

    for(i=0; i<this.length; i++) {
        result.data[i] = this.data[index--];
    }
    return result;
}

String strMap(String this,_char f(_char)) {
    String result = mkStringForLength(this.length);
    sizetype i;

    for(i=0; i<this.length; i++) {
        result.data[i] = f(this.data[i]);
    }
    return result;
}

String strToLowerCase(String this){
    String result = mkStringForLength(this.length);
    sizetype i;

    for(i=0; i<this.length; i++) {
        if (this.data[i] >= 'A' && this.data[i] <= 'Z') {
            result.data[i] = this.data[i] + 32;
        }
        else {
            result.data[i] = this.data[i];
        }
    }
    return result;
}

String strToUpperCase(String this){
    String result = mkStringForLength(this.length);
    sizetype i;

    for(i=0; i<this.length; i++) {
        if(this.data[i]>='a' && this.data[i]<='z') {
            result.data[i] = this.data[i] - 32;
        }
        else {
            result.data[i] = this.data[i];
        }
    }
    return result;
}

String strSubstring(String this,sizetype from, sizetype to){
    String result = mkStringForLength(to-from);
    sizetype i, index=0;

    for(i=from; i<to; i++) {
        result.data[index++] = this.data[i];
    }
    return result;
}

String strTrim(String this){
    String result = mkStringForLength(this.length);
    sizetype i;

    for (i=0; i<this.length; i++) {
        if(this.data[i] == '\t' || this.data[i] == '\n' || this.data[i] == ' ') {
            continue;
        }
        result.data[i] = this.data[i];
    }
    return result;
}

Ord strCompare(String this,String that){
    sizetype i;

    for(i=0; i<this.length; i++) {
        if (this.data[i]<that.data[i]) {
            return lt;
        }
        if(this.data[i]>that.data[i]) {
            return gt;
        }
    }
    if (this.length>that.length) {
        return gt;
    }
    if (this.length<that.length) {
        return lt;
    }
    return eq;
}

bool strStartsWith(String this,String prefix){
    sizetype i;

    if(prefix.length>this.length) {
        return false;
    }

    for(i=0; i<prefix.length; i++) {
        if(this.data[i] != prefix.data[i]) {
            return false;
        }
    }
    return true;
}

bool strEndsWith(String this,String suffix){
    sizetype i;

    for(i=0; i<suffix.length; i++) {
        if (this.data[this.length-suffix.length+i]!=suffix.data[i]) {
            return false;
        }
    }
    return true;
}

String strReplace(String this, _char oldChar, _char newChar){
    String result = mkStringForLength(this.length);
    sizetype i;
    for (i = 0; i < this.length; i++){
        if (this.data[i] == oldChar){
            result.data[i] = newChar;
        }
        else{
            result.data[i] = this.data[i];
        }
    }
    return result;
}
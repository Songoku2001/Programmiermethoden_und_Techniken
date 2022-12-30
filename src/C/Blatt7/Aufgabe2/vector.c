#include "vector.h"
#include <math.h>

/** Modifiziert den ersten Parameter durch Multiplikation 
beider Komponenten mit einem Skalar.*/
void multMod(struct Vector* this, double that){
    this->x *= that;
    this->y *= that;
    this->z *= that;
}

/** Berechnet einen neuen Vektor durch Multiplikation mit
    einem Skalar. */
struct Vector mult(struct Vector this, double that){
    struct Vector result;

    result.x = this.x * that;
    result.y = this.y * that;
    result.z = this.z * that;

    return result;
}

/** Addiert komponentenweise den zweiten Vektor auf den ersten.*/
void addMod(struct Vector* this, struct Vector that){
    this->x += that.x;
    this->y += that.y;
    this->z += that.z;
}

/** Berechnet einen neuen Vektor durch Addition zweier
    Vektoren. */
struct Vector add(struct Vector this, struct Vector that){
    struct Vector result;

    result.x = this.x + that.x;
    result.y = this.y + that.y;
    result.z = this.z + that.z;

    return result;
}

/** Berechnet das Skalarprodukt für zwei Vektoren. */
double dotProduct(struct Vector this, struct Vector that){
    double result;

    result = this.x*that.x + this.y*that.y + this.z*that.z;

    return result;
}

/** Berechnet das Kreuzprodukt für zwei Vektoren. */
struct Vector crossProduct(struct Vector this, struct Vector that){
    struct Vector result;

    result.x = this.y*that.z - this.z*that.y;
    result.y = this.z*that.x - this.x*that.z;
    result.z = this.x*that.y - this.y*that.x;

    return result;
}

/** Berechnet den Betrag eines Vektors. */
double norm(struct Vector v){
    double result = 0;

    result = sqrt(pow(v.x, 2) + pow(v.y, 2) + pow(v.z, 2));

    return result;
}

/** Berechnet den Abstand eines Punktes zu einer Geraden. */
double distance(struct PunktRichtungsGleichung gl,struct Vector p){
    struct Vector v;
    v = add(p, mult(gl.punkt, -1));
    v = crossProduct(v, gl.richtung);
    return norm(v) / norm(gl.richtung);
}

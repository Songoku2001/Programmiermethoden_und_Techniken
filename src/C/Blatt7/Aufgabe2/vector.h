#ifndef VECTOR__H
#define VECTOR__H

#include "C:\Users\minha\CLionProjects\Pmt\Blatt7\Aufgabe1/MemoryTest.h"
#include <stdbool.h>

struct Vector {
  double x;
  double y;
  double z;
};

/** Modifiziert den ersten Parameter durch Multiplikation 
beider Komponenten mit einem Skalar.*/
void multMod(struct Vector* this, double that);

/** Berechnet einen neuen Vektor durch Multiplikation mit
    einem Skalar. */
struct Vector mult(struct Vector this, double that);

/** Addiert komponentenweise den zweiten Vektor auf den ersten.*/
void addMod(struct Vector* this, struct Vector that);

/** Berechnet einen neuen Vektor durch Addition zweier
    Vektoren. */
struct Vector add(struct Vector this, struct Vector that);
 
/** Berechnet das Skalarprodukt für zwei Vektoren. */
double dotProduct(struct Vector this, struct Vector that);

/** Berechnet das Kreuzprodukt für zwei Vektoren. */
struct Vector crossProduct(struct Vector this, struct Vector that);

/** Berechnet den Betrag eines Vektors. */
double norm(struct Vector v);

/** Stellt eine Gerade als Punktrichtungsgleichung dar. */
struct PunktRichtungsGleichung {
  struct Vector punkt;
  struct Vector richtung;
};

/** Berechnet den Abstand eines Punktes zu einer Geraden. */
double distance(struct PunktRichtungsGleichung gl,struct Vector p);
#endif
#pragma once

#include <stddef.h>

// Defintion of the OPACO type
typedef struct _Lista Lista;
typedef struct Nodo Nodo;
// Allocates the Lista
Lista* Lista_new();


// Deallocates the Lista 
void Lista_free(Lista* array);

// Returns the number of elements currently stored in the Lista 
size_t Lista_size(Lista* array);

// Returns the capacity of the Lista 
size_t Lista_capacity(Lista* array);

// Returns 1 if array is empty, 0 otherwise
int Lista_empty(Lista* array);

// Returns the element of array at the specified position
void* Lista_at(Lista* array, size_t position);

// Insert element in the Lista 
void Lista_insertail(Lista* array, void* element);
void Lista_insert_at(Lista* array, void* element, size_t position);

// Delete element in the Lista 
void Lista_removetail(Lista* array);
void Lista_remove_at(Lista* array, size_t position);


//iterator

typedef void** iterator;
iterator cont_begin(Lista* a);
iterator cont_end(Lista* a);
iterator cont_next(iterator i);
void* cont_getelem(iterator i);
int valid_it(iterator i, Lista* a);
void free_it(iterator i);




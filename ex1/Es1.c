#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Es1.h"

//
struct _Lista {
  void ** array;
  size_t size;
  size_t capacity;
};


Lista* Lista_new(){
  size_t capacity = 1;
  Lista* result = (Lista*) malloc(sizeof(Lista));
  result-> array = (void**) malloc(sizeof(capacity));
  result-> size = 0;
  result-> capacity = capacity;
  
  
  return result;
}


size_t Lista_size(Lista* array){
  return array-> size;
}

size_t Lista_capacity(Lista* array){
  return array-> capacity;
}

int Lista_empty(Lista* array) {
  return array->size == 0;
}

void Lista_free(Lista* array) {
  free(array->array);
  free(array);
  return;
}

void* Lista_at(Lista* array, size_t position) {
  if(position >= array->size ) {
    fprintf(stderr, "Array index (%ld) out of bounds (0:%ld)\n", position, array->size);
    exit(EXIT_FAILURE);
  }
  return array->array[position];
}

void Lista_check_and_realloc(Lista* array) {
  if( array->capacity > array->size )
    return;
  array->capacity *= 2;
  array->array = realloc(array->array, sizeof(void*) * array->capacity);
}

void Lista_insertail(Lista* array, void* element){
  Lista_check_and_realloc(array);
  array->array[array->size] = element;
  array->size++;
}

void Lista_insert_at(Lista* array, void* element, size_t position){
  if(array->capacity > position){
      Lista_check_and_realloc(array);
      if(position == array->size)
          Lista_insertail(array,element);
      else if(position >= 0 && position <= array->size) {
          int i = array->size;
          for(i; i >= position; i--)
              array->array[i] = array->array[i-1];
          array->array[position] = element;
          array->size++;
      }
      else 
          printf("indice amaro negativo :(");
      
  } else 
      printf("indice amaro :(");
}

void Lista_removetail(Lista* array){

  free(Lista_at(array, array->size-1));
  array->size += -1;
}

void Lista_remove_at(Lista* array, size_t position){
  
  if(position == array->size-1)
          Lista_removetail(array);
  else if (position >= 0 && position < array->size){
      int i = position+1;
      for(i; i < array->size; i++)
          array->array[i-1] = array->array[i];
      
      array->size--;
  }
  else 
      printf("indice amaro :(");
      
  
}

iterator cont_begin(Lista* a){
  return &(a->array[0]); 
}
iterator cont_end(Lista* a){
  return &(a->array[a->size]);
}
iterator cont_next(iterator i){
  return ++i;
}
void* cont_getelem(iterator i){
  return *i;
}

int valid_it(iterator i, Lista* a){
  if(i >= cont_begin(a) && i < cont_end(a))
    return 1;
  else
    return 0;
}

void free_it(iterator i){
  free(i);
}











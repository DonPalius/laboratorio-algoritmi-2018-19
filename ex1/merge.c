#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Es1.h"

//
/** Merge algorithm implementation that takes a compare function, 
  *  a new list to store the result and two ordered list with two relative iterators as input
  */
    
void merge (int (*compare)(void*,void*), iterator it1, iterator it2, Lista * result, Lista* lista1, Lista* lista2 ) {
	
    if (it1 == cont_end(lista1)) {
        for(; it2 != cont_end(lista2); it2 = cont_next(it2))
        	Lista_insertail(result, cont_getelem(it2));
    }
    else if (it2 == cont_end(lista2)) {
        for(; it1 != cont_end(lista1); it1 = cont_next(it1))
        	Lista_insertail(result, cont_getelem(it1)); 
    }
   
    else{
	
        if (compare(cont_getelem(it1),cont_getelem(it2))) {
            Lista_insertail(result,cont_getelem(it1));
    		merge(compare,cont_next(it1), it2, result,lista1,lista2); 
        } 
        else { 
            Lista_insertail(result,cont_getelem(it2));
            merge(compare,it1, cont_next(it2), result,lista1,lista2); 
        } 
    
    }

    
}

/** Function that takes two ordered list and a compare function as input 
  * checks if the lists are not null and call "merge"
  */
  
Lista* merge_sort (Lista* lista1, Lista* lista2, int (*compare)(void*,void*)) {  //precond: lista1 e lista 2 già ordinate
	
	if(lista1 == NULL && lista2 == NULL) 
		return NULL; 
    else if (lista1 == NULL && lista2 != NULL)
		return lista2;
	else if (lista1 != NULL && lista2 == NULL)
		return lista1;  
	else{ 
		iterator it1 = cont_begin(lista1);
		iterator it2 = cont_begin(lista2);
		Lista* result = Lista_new();
		merge(compare, it1, it2, result,lista1,lista2);
		return result;
	}
}
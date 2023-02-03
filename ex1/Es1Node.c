#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Es1.h"

//
struct _Lista {
  struct Nodo	* first;
	struct Nodo * tail;
	size_t size;
};
struct Nodo {
	void* element;
	struct Nodo * prev;
	struct Nodo * next;
	
};

Lista* Lista_new(){
	Lista* lista = (Lista*) malloc(sizeof(Lista));
	lista-> tail = NULL;
	lista-> first = NULL;
	lista->size = 0;
	return lista;
}

Nodo* Nodo_new(void* element){
	
	Nodo* new = (Nodo*) malloc(sizeof(Nodo));
	new->element = element;
	new->prev = NULL;
	new->next = NULL;
	
	return new;
}

void Lista_free(Lista* lista){
	Nodo* curr ;
	
	while ((curr = lista->first) != NULL) { 
		lista->first = lista->first->next;          
		free (curr);                
	}
	
	free(lista);
  
}

int Lista_empty(Lista* lista){
	if(lista->first == NULL) 
		return 1;
	else 
		return 0;
}

void* Lista_at(Lista* lista, size_t position){
	Nodo* head = lista->first;
	for(;position > 0; position --)
	  head = head->next;
	return head->element;
}

void Lista_insertail(Lista* lista, void* element){
	if(lista->first == NULL){
		Nodo* nuvo = Nodo_new(element);
		lista->first = nuvo;
		lista->tail = nuvo;
		lista->size++;
	}
	
	else { 
		
		Nodo* nuvo = Nodo_new(element);
		lista->tail->next = nuvo;
		nuvo->prev = lista->tail;
		lista->tail = nuvo;
		lista->size++;
	}
}

void Lista_insert_at(Lista* lista, void* element, size_t position){
	if(position >= 0){
	
	Nodo* head = lista->first;
	
	if(position == 0){
		Nodo* nuvo = Nodo_new(element);
		nuvo->next = lista->first;
		lista->first = nuvo;
		lista->size++;
	}
	else{
		for(position = position -1 ;position > 0 && head->next != NULL; position --)
			head = head->next;
		if(head->next == NULL)
			Lista_insertail(lista,element);
		else{
			Nodo* nuvo = Nodo_new(element);
			nuvo->next = head->next;
			head->next->prev = nuvo;
			head->next = nuvo;
			nuvo->prev = head;
		}
		lista->size++;
		
	}
	
	}
	
}

size_t Lista_size(Lista* lista){
	return lista->size;
}


void Lista_removetail(Lista* lista){
	
	Nodo* delete = lista->tail;
	lista->tail = lista->tail->prev;
	lista->size--;
	free(delete);
}

void Lista_remove_at(Lista* lista, size_t position){
	
	if(position == 0){
		Nodo* delete = lista->first;
		lista->first = lista->first->next;
		lista->size--;
		free(delete);
	}else {

	Nodo* prev = lista->first;
	Nodo* head = lista->first->next;

	for(;position > 1 && head->next!=NULL; position --){
		prev = head;
		head = head->next;
	}
	
	if(head->next == NULL)
		lista->tail = prev;
	prev->next = head->next;
	free(head);
	lista->size--;
	}
}


iterator cont_begin(Lista* a){
	return &(a->first->element);
}
iterator cont_end(Lista* a){
	return NULL;
}
iterator cont_next(iterator i){
	return &(((Nodo *) i) -> next->element);
}
void* cont_getelem(iterator i){
	return ((Nodo *) i) -> element;
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




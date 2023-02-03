#ifndef MERGE_H
#define MERGE_H

/* merge two ordered list */ 
Lista* merge_sort(Lista* a,Lista* b, int (*compare)(void*,void*));
/* compare function */
int compare_mergesort(void* left,void* right);

#endif 



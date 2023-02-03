#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include "Es1.h"
#include "merge.h"

//#define WINDOWS 1
#ifdef WINDOWS
#include "..\Resources\C\Unity\unity.h"
#include "..\Resources\C\Unity\unity_internals.h"
#endif
#define MAC 
#ifdef MAC
#include "../Resources/C/Unity/unity.h"
#include "../Resources/C/Unity/unity_internals.h"
#endif

//
static int* new_int(int value) {
	int* elem = (int*) malloc(sizeof(int));
	*elem = value;
	return elem;
}

static char* new_string(char* value) {
	char* elem = (char*) malloc(strlen(value)*sizeof(char**));
	elem = value;
	return elem;
}

int compare_mergesort(void* left,void* right){
	int first = *(int*) left;
	int second = *(int*) right;

	if(first <= second){
		return(1);
	}
	return(0);
}
static void stampa_list_string(Lista* array){
	iterator i = cont_begin(array);
	while(i != cont_end(array)){
		printf("%s\n",(char*)cont_getelem(i));
		i = cont_next(i);
	}
}

static void stampa_list(Lista* array){
	iterator i = cont_begin(array);
	while(i != cont_end(array)){
		printf("%d\n",*(int*)cont_getelem(i));
		i = cont_next(i);
	}
}

static Lista* build_fixture1() {

	Lista* array = Lista_new();
	Lista_insertail(array, new_int(1));
	Lista_insertail(array, new_int(4));
	Lista_insertail(array, new_int(7));
	Lista_insertail(array, new_int(10));
	Lista_insertail(array, new_int(12));
	Lista_insertail(array, new_int(40));
	Lista_insertail(array, new_int(78));
	Lista_insertail(array, new_int(100)); 	
	return array;
	
}
static Lista* build_fixture2() {

	Lista* array = Lista_new();
	Lista_insertail(array, new_int(2));
	Lista_insertail(array, new_int(3));
	Lista_insertail(array, new_int(7));
	Lista_insertail(array, new_int(8));
	return array;
	
}

static Lista* build_fixture_string1() {

	Lista* array = Lista_new();
	Lista_insertail(array, new_string("Abaco"));
	Lista_insertail(array, new_string("Dijkstra"));
	Lista_insertail(array, new_string("Giuseppe"));
	Lista_insertail(array, new_string("Zio"));
	return array;

}

static Lista* build_fixture_string2() {

	Lista* array = Lista_new();
	Lista_insertail(array, new_string("Babbo"));
	Lista_insertail(array, new_string("Cane"));
	Lista_insertail(array, new_string("Matrioska"));
	Lista_insertail(array, new_string("Zebra"));
	return array;

}


static void free_fixture1(Lista* array) {
	Lista_free(array);
}
static void free_fixture2(Lista* array) {
	Lista_free(array);
}

static void test_merge(){
	Lista* array1 = build_fixture1();
	Lista* array2 = build_fixture2();

	stampa_list(array1);
	printf("Fine lista 1 \n");
	stampa_list(array2);
	printf("Fine lista 2 \n");

	Lista* array3 = merge_sort(array1, array2, compare_mergesort);

	stampa_list(array3);
	printf("Fine lista 3 \n");
	TEST_ASSERT_EQUAL_INT(12, Lista_size(array3));
	free_fixture1(array1);
	free_fixture2(array2);

}

int main() {
	UNITY_BEGIN();

	RUN_TEST(test_merge);


	UNITY_END();

	return 0;
}


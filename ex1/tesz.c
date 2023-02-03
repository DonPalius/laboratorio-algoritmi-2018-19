#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "Es1.h"

#define WINDOWS 1
#ifdef WINDOWS
#include "..\Resources\C\Unity\unity.h"
#include "..\Resources\C\Unity\unity_internals.h"
#endif
//#define MAC 1
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

static Lista* build_fixture() {
  
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

static void free_fixture(Lista* array) {
	Lista_free(array);
}



static void test_lista_free() {
  Lista* array = Lista_new();
  Lista_free(array);

  TEST_ASSERT_EQUAL_INT(1, 1);
}

static void test_lista_size() {
  Lista* array = build_fixture();
  TEST_ASSERT_EQUAL_INT(8, Lista_size(array));
  free_fixture(array);
}


static void test_lista_empty() {
  Lista* array = Lista_new();
  TEST_ASSERT_EQUAL_INT(1, Lista_empty(array));
  Lista_free(array);
}



static void test_lista_at() {
  Lista* array = build_fixture();
  TEST_ASSERT_EQUAL_INT(1,  *(int*)Lista_at(array, 0) );
  TEST_ASSERT_EQUAL_INT(4,  *(int*)Lista_at(array, 1) );
  TEST_ASSERT_EQUAL_INT(7,  *(int*)Lista_at(array, 2) );
  TEST_ASSERT_EQUAL_INT(10,  *(int*)Lista_at(array, 3) );
  free_fixture(array);
}


static void test_lista_insertail() {
  Lista* array = build_fixture();

  Lista_insertail(array, new_int(0));
  TEST_ASSERT_EQUAL_INT(9, Lista_size(array));
  TEST_ASSERT_EQUAL_INT(12, *(int*)Lista_at(array, 4));

  free_fixture(array);
}


static void test_lista_insert_at() {
  Lista* array = build_fixture();
  
  Lista_insert_at(array, new_int(8),1);
  TEST_ASSERT_EQUAL_INT(9, Lista_size(array));
  TEST_ASSERT_EQUAL_INT(8, *(int*)Lista_at(array, 1));
  TEST_ASSERT_EQUAL_INT(4, *(int*)Lista_at(array, 2));

  free_fixture(array);
}

static void test_remove_tail(){

  Lista* array = build_fixture();
  
  Lista_removetail(array);
  Lista_removetail(array);
  TEST_ASSERT_EQUAL_INT(6, Lista_size(array));

  free_fixture(array);
	
}

static void test_remove_at(){

  Lista* array = build_fixture();
  
  Lista_remove_at(array,2);
  Lista_remove_at(array,5);
  Lista_remove_at(array,0);
  
  
  TEST_ASSERT_EQUAL_INT(5, Lista_size(array));
  TEST_ASSERT_EQUAL_INT(4,  *(int*)Lista_at(array, 0) );
  TEST_ASSERT_EQUAL_INT(10,  *(int*)Lista_at(array, 1) );
  TEST_ASSERT_EQUAL_INT(12,  *(int*)Lista_at(array,2) );
 
 
  free_fixture(array);
	
}


static void test_iterator(){
  
  Lista* array = build_fixture();
  iterator i = cont_begin(array);
  TEST_ASSERT_EQUAL_INT(1, *(int*)cont_getelem(i));
  i = cont_next(i);
  TEST_ASSERT_EQUAL_INT(4, *(int*)cont_getelem(i));


  free_fixture(array);

}

int main() {
  UNITY_BEGIN();
  
  RUN_TEST(test_lista_size);
  RUN_TEST(test_lista_empty);
  RUN_TEST(test_lista_at);
  RUN_TEST(test_lista_insertail);
  RUN_TEST(test_lista_insert_at);
  RUN_TEST(test_remove_tail);
  RUN_TEST(test_remove_at);
  RUN_TEST(test_iterator);
  
  UNITY_END();

  return 0;
}
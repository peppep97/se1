# Unit Testing Documentation

Authors: Filippo Fontan, Giuseppe Pipero, Iman Ostovar, Matteo Pappadà

Date: 19/05/2020

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

 ### **Class *User* - method *setReputation***



**Criteria for method *setReputation*:**
	
 - Sign of reputation
 - Type of reputation
 - Value of reputation


**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|Sign of reputation| > 0|
|          |        < 0    |
|    Type of reputation      |      Integer     |
|          |      All others     |
|     Value of reputation     |     >= minInt && <= maxInt      |
|          |     > maxInt      |
|          |     < minInt      |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|    Value of reputation      |       minInt, maxInt, minInt + 1, maxInt + 1          |



**Combination of predicates**:


| Type of reputation | Sign of reputation | Value of reputation | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| Integer | > 0 | >= minInt && <= maxInt | V | setReputation(3) --> OK | it.polito.ezgas.entity.UserTest.java|
| Integer | > 0 | > maxInt | I | setReputation(99999999) --> Exception | it.polito.ezgas.entity.UserTest.java|
| Integer | < 0 | >= minInt && <= maxInt | V | setReputation(-3) --> OK | it.polito.ezgas.entity.UserTest.java|
| Integer| < 0 | < minInt | I | setReputation(-99999999) --> Exception | it.polito.ezgas.entity.UserTest.java|
| All others | ... | ... | I | setReputation("ciao") --> Exception | it.polito.ezgas.entity.UserTest.java|

 ### **Class *User* - method *setAdmin***



**Criteria for method *setAdmin*:**
	
 - Type of admin
 - Value of admin


**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|Type of admin| Boolean |
|          |       All others    |
|    Value of admin      |      True     |
|          |      False     |



**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |



**Combination of predicates**:


| Type of admin | Value of admin | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| Boolean | True | V | setAdmin(true) --> OK | it.polito.ezgas.entity.UserTest.java|
| Boolean | False | V | setAdmin(false) --> OK | it.polito.ezgas.entity.UserTest.java|
| All others | ... | I | setAdmin("no") --> Exception | it.polito.ezgas.entity.UserTest.java|

### **Class *User* - method *setEmail***



**Criteria for method *setEmail*:**

 - Type of email


**Predicates for method *setEmail*:**

| Criteria | Predicate |
| -------- | --------- |
|Type of email| String|
|          |        All others    |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |


**Combination of predicates**:

| Type of email | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| String | V | setEmail("email@mail.com") --> OK | it.polito.ezgas.entity.UserTest.java|
| String | I | setEmail(3343) --> Exception | it.polito.ezgas.entity.UserTest.java|

# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||




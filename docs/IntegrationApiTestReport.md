# Integration and API Test Documentation

Author: Filippo Fontan, Giuseppe Pipero, Iman Ostovar, Matteo Pappadà

Date: 26/05/2020

Version: 1

# Contents

- [Integration and API Test Documentation](#integration-and-api-test-documentation)
- [Contents](#contents)
- [Dependency graph](#dependency-graph)
- [Integration approach](#integration-approach)
- [Tests](#tests)
  - [Step 1](#step-1)
  - [Step 2](#step-2)
  - [Step 3](#step-3)
  - [Step 4 API Tests](#step-4-api-tests)
- [Scenarios](#scenarios)
  - [Scenario UC4.1](#scenario-uc41)
  - [Scenario UC7.1](#scenario-uc71)
  - [Scenario UC8.1](#scenario-uc81)
- [Coverage of Scenarios and FR](#coverage-of-scenarios-and-fr)
- [Coverage of Non Functional Requirements](#coverage-of-non-functional-requirements)
    - [](#)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph 

   ```plantuml
   skinparam linetype ortho

     class User
     class UserRepository
     class BootEzGasApplication
     class GasStationController
     interface GasStationService
     interface UserService
     class HomeController
     class UserController
     class GasStationConverter
     class GasStation
     class GasStationDto
     class UserConverter
     class UserDto
     class LoginDto
     class GasStationServiceImpl
     class GasStationRepository
     class UserServiceImpl

   User <.up. BootEzGasApplication
   UserRepository <.up. BootEzGasApplication
   GasStationService <.up. GasStationController
   UserService <.up. UserController
   GasStation <.up. GasStationConverter
   GasStationDto <.up. GasStationConverter
   User <.up. UserConverter
   UserDto <.up. UserConverter
   LoginDto <.up. UserConverter
   GasStationDto <.up. GasStationService
   UserDto <.up. UserService
   LoginDto <.up. UserService
   GasStation <.up. GasStationRepository
   User <.up. UserRepository
   GasStationService <.up. GasStationServiceImpl
   GasStationRepository <.up. GasStationServiceImpl
   UserRepository <.up. GasStationServiceImpl
   GasStation <.up. GasStationServiceImpl
   GasStationConverter <.up. GasStationServiceImpl
   GasStationDto <.up. GasStationServiceImpl 
   UserService <.up. UserServiceImpl 
   UserRepository <.up. UserServiceImpl 
   UserDto <.up. UserServiceImpl 
   User <.up. UserServiceImpl 
   UserConverter <.up. UserServiceImpl 
   ```
     
# Integration approach

  <!-- <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
  (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)> 
  <The last integration step corresponds to API testing at level of Service package>
  <Tests at level of Controller package will be done later> -->

    We have followed a mixed approach.
    Step 1: test of converters
    Step 2: test of services (with mocked repositories)
    Step 3: test of repositories
    Step 4: test of services

#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
| Classes  | JUnit test cases |
|--|--|
|UserConverter|it.polito.ezgas.converter.UserConverterTest.java|
|GasStationConverter|it.polito.ezgas.converter.GasStationConverterTest.java|

## Step 2
| Classes  | JUnit test cases |
|--|--|
|GasStationServiceImpl|it.polito.ezgas.service.impl.GasStationServiceImplMockTest.java| 
|UserServiceImpl|it.polito.ezgas.service.impl.UserServiceImplMockTest.java|

## Step 3
| Classes  | JUnit test cases |
|--|--|
|UserRepository|it.polito.ezgas.repository.UserRepositoryTest.java|
|GasStationRepository|it.polito.ezgas.repository.GasStationRepositoryTest.java|



## Step 4 API Tests

   <The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
|GasStationServiceImpl|it.polito.ezgas.service.impl.GasStationServiceImplTest.java| 
|UserServiceImpl|it.polito.ezgas.service.impl.UserServiceImplTest.java|

# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UC4.1

| Scenario |  create a gas station |
| ------------- |:-------------:| 
|  Precondition     | Gas Station  G does not exist |
|       | U is an admin |
|  Post condition     | Gas Station G is created  |
| Step#        | Description  |
|  1     |  U opens admin panel|  
|  2     |  U fills gas station registration form|
|  3    |  System saves Gas Station G|

## Scenario UC7.1

| Scenario |  report gas station fuel price |
| ------------- |:-------------:| 
|  Precondition     | Gas station G  |
|| User U is registered in the system|
| Post condition   |                  Price list P is created                   |
|                  |   P.time_tag is set to the current timestamp of the system   |
|                  |                     P is attached to G                  |
|                  |                     U is attached to P  |
| Step#        | Description  |
|  1     |  U selects Gas Station G|  
|  2     |  U fills New Report form|
|  3    |  System retrieves gas station G, set new prices, attach user U to G, save modified gas station G|

## Scenario UC8.1

| Scenario |  list gas stations near a given address |
| ------------- |:-------------:| 
|  Precondition     | - |
|  Post condition     | List of gas stations is retrieved  |
| Step#        | Description  |
|  1     |  U specifies an address |  
|  2    |  System searches gas stations close to the address (in range of 1km)|
| 3 | System returns list of gas stations|

# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  10.1         | FR4 - FR4.1 - FR4.2 - FR4.3 - FR4.4 - FR4.5 - FR5 - FR5.2                             | it.polito.ezgas.service.impl.GasStationServiceImplTest.java            |
|           |                              | it.polito.ezgas.service.impl.UserServiceImplTest.java            |             
|  10.2        | FR4 - FR4.1 - FR4.2 - FR4.3 - FR4.4 - FR4.5 - FR5 - FR5.2                             |     it.polito.ezgas.service.impl.GasStationServiceImplTest.java        |             
|           |                              | it.polito.ezgas.service.impl.UserServiceImplTest.java            |            |             
| UC4.1         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |



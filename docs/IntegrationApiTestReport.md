# Integration and API Test Documentation

Authors:

Date:

Version:

# Contents

- [Integration and API Test Documentation](#integration-and-api-test-documentation)
- [Contents](#contents)
- [Dependency graph](#dependency-graph)
- [Integration approach](#integration-approach)
- [Tests](#tests)
  - [Step 1](#step-1)
  - [Step 2](#step-2)
  - [Step n API Tests](#step-n-api-tests)
- [Scenarios](#scenarios)
  - [Scenario UCx.y](#scenario-ucxy)
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

    <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
    (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)> 
    <The last integration step corresponds to API testing at level of Service package>
    <Tests at level of Controller package will be done later>



#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
| Classes  | JUnit test cases |
|--|--|
|||


## Step 2
| Classes  | JUnit test cases |
|--|--|
|||


## Step n API Tests

   <The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
|||




# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UCx.y

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |  
|  2     |  ... |



# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  ..         | FRx                             |             |             
|  ..         | FRy                             |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             
| ...         |                                 |             |             



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |



# GUI  Testing Documentation 

Author: Filippo Fontan, Giuseppe Pipero, Iman Ostovar, Matteo Pappadà

Date: 30/05/2020

Version: 1

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

### 

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | 
| UC1         | FR1 - FR1.1                     | uc1.py      |             
| UC2         | FR1 - FR1.1                     | uc2.py      |       
| UC4         | FR3 - FR3.1                     | uc4.py      |            
| UC5         | FR3 - FR3.1                     | uc5.py      |   
| UC6         | FR3 - FR3.2                     | uc6.py      |   
| UC7         | FR5 - FR5.1                     | uc7.py      |   
| UC8         | FR4 - FR4.1 - FR4.2 - FR4.3 - FR4.4 - FR4.5   | uc8.py      |   
| UC9         | FR5 - FR5.2                     | uc9.py      |   
| UC10        | FR5 - FR5.3                     | uc10.py     |   
            


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name | Functional Requirements covered |REST  API Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  UserController.getUserById  | FR1 - FR1.4    |testGetUser|     
|  UserController.getAllUsers  | FR1 - FR1.3    |testGetAllUsers|     
| UserController.saveUser      | FR1 - FR1.1    |testSaveUser|             
| UserController.deleteUser    | FR1 - FR1.2    |testDeleteUser|             
| UserController.increaseUserReputation| FR5 - FR5.2             |testIncreaseUserReputation|             
| UserController.decreaseUserReputation| FR5 - FR5.2             |testDecreaseUserReputation|             
| UserController.login         | FR2                             |testLogin|      
| GasStationController.getGasStationById         | FR4                             |testGetGasStation|  
| GasStationController.getAllGasStations         | FR4                             |testGetAllGasStations|  
| GasStationController.saveGasStation            | FR3 - FR3.1                     |testSaveGasStation|  
| GasStationController.deleteGasStation          | FR3 - FR3.2                                |testDeleteGasStation|  
| GasStationController.getGasStationsByGasolineType      | FR4 - FR4.4                        |testSearchGasStationByGasolineType|  
| GasStationController.getGasStationsByProximity         | FR4 - FR4.1 - FR4.2                |testSearchGasStationByProximity|  
| GasStationController.getGasStationsWithCoordinates     | FR4 - FR4.2 - FR4.5                |testGetGasStationsWithCoordinates|  
| GasStationController.setGasStationReport               | FR5 - FR5.1                        |testSetGasStationReport|  

# Official Requirements Document

Author: Filippo Fontan, Giuseppe Pipero, Iman Ostovar, Matteo Pappadà

Date: 18/04/2020

Version: 1

| Version | Changes                                                     |
| ------- | :---------------------------------------------------------- |
| 1       |                                 |

# Contents
- [Official Requirements Document](#official-requirements-document)
- [Contents](#contents)
- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	- [Context Diagram](#context-diagram)
	- [Interfaces](#interfaces)
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	- [Functional Requirements](#functional-requirements)
	- [Non Functional Requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	- [Use case diagram](#use-case-diagram)
	- [Use Cases](#use-cases)
		- [Use case 1, UC1 - Add/Update price of a gas station (FR1)](#use-case-1-uc1---addupdate-price-of-a-gas-station-fr1)
				- [Scenario 1.1](#scenario-11)
		- [Use case 2, UC2 Add new gas station (FR2)](#use-case-2-uc2-add-new-gas-station-fr2)
				- [Scenario 2.1](#scenario-21)
		- [Use case 3, UC3 Manage reports/modifications performed by users (FR9)](#use-case-3-uc3-manage-reportsmodifications-performed-by-users-fr9)
				- [Scenario 3.1](#scenario-31)
				- [Scenario 3.2](#scenario-32)
		- [Use case 4, UC4 Show gas stations in the area (FR4 - FR7 - FR8)](#use-case-4-uc4-show-gas-stations-in-the-area-fr4---fr7---fr8)
				- [Scenario 4.1](#scenario-41)
				- [Scenario 4.2](#scenario-42)
		- [Use case 5, UC5 Remove/correct self made errors (FR9)](#use-case-5-uc5-removecorrect-self-made-errors-fr9)
				- [Scenario 5.1](#scenario-51)
				- [Scenario 5.2](#scenario-52)
		- [Use case 6, UC6 report false information (FR6)](#use-case-6-uc6-report-false-information-fr6)
				- [Scenario 6.1](#scenario-61)
- [Glossary](#glossary)
- [Deployment diagram](#deployment-diagram)

# Abstract

EZGas is a crowdsourcing service that shares prices of gas stations. Users can just use the service to check prices, but they can also interact with it adding untracked gas stations or updateing current prices if they are obsolete. An administrator must approve every add/update performed by a user.

The user can filter the list of stations based on price, distance, or based on the last update date of each station. To find out if the prices are truthful there is a review system that can be used by users.

The administrator is in charge of managing the service (User, accounts, and Stations), access to the Gas station data. The administrator also checks reports of users and approve/disapprove modifications. In this version, the administrator just checks if data inserted by a user is reliable (i.e., no dumb or unlikely data). If so, the administrator always approves the request.

The service uses an external Map Service provider.

# Stakeholders

| Stakeholder name |                                               Description                                               |
| ---------------- | :-----------------------------------------------------------------------------------------------------: |
| Administrator    |                        Manage the service, approve modifications, manage reports                        |
| User             | Use the EZGas service. He can see stations and prices. he can also add stations and update their prices |
| Map system       |                                      External Map like Google Maps                                      |
| Developer        |                                           Develop the system                                            |

# Context Diagram and interfaces

## Context Diagram

```plantuml
left to right direction
actor Administrator as a
left to right direction
actor User as u
left to right direction
actor MapSystem as map
a -- (EzGas)
u -- (EzGas)
map -- (EzGas)
```

## Interfaces
| Actor         | Logical Interface |  Physical Interface |
| ------------- | :---------------: | ------------------: |
| Administrator |        GUI        |    Screen, keyboard |
| User          |        GUI        |    Screen, keyboard |
| Map System    |        API        | Internet connection |

# Stories and personas
John is an employee in a commercial company. Every day he does the same way to get to work in his car. Twice a week, on Monday and Thursday, he stops to get gas in the same gas station, and every time he takes note of the price for suggesting that gas station to his colleagues.
His colleagues decide to stop to the gas station suggested by John, only if the price is reasonable, but they don’ know if it is higher or lower than their gas station of trust. 
So John decides to use EZGas service to update prices of a specific gas station and share them with everyone. With this app, he can suggest more efficiently a gas station to his colleagues.

Jimmy is a delivery man who has always been very conscious about gas prices. To support his route's decisions and maximize his efficiency both money and time -wise he frequently takes a peek at the EZGAS app, particularely useful in case a new gas pump shows up. Moreover he's happy to do his part by checking and updating any gas price he can find, thus helping fellow drivers.

Mary is reporter, and has to travel a lot with her car. All the costs for the travels are not included in her salary, so she wants always to save money on gas, hotels and highway taxes. For this, every time she has to get gas, she looks for the cheaper price and since she find out the EZGas app she is very happy, because she doesn’t have to struggle a lot to find the cheaper gas station during her travels.

Bob uses EZGas to find gas stations. In a certain moment, he realize that a station reported in the app doesn't exist anymore and he would like to avoid that other people can live the same inconvenient. So, he send a report to an administrator signal that the specified station no longer exist, hoping that the report can be managed as soon as possible.

# Functional and non functional requirements
## Functional Requirements

| ID   |                                  Description                                  |
| ---- | :---------------------------------------------------------------------------: |
| FR1  |                         Add/update price/s of a gas station                         |
| FR2  |                            Add a new gas station                            |
| FR3  |                           Log in/out the service                                |
| FR4  |                  Show every gas station in a given area                         |
| FR5  |                           Add/Manage users and accounts                           |
| FR6  |                           Report false informations                             |
| FR7  |                 Sort stations by distance/price/last update date (on a limited area)                 |
| FR8 | Search stations by address/price/fuel type, distance range and other info |
| FR9 |                                Manage reports/modifications performed by users                                |
| FR10 |                    Leave a review                    |
| FR11 |                Show detailed info about single gas station                      |
| FR12 |                  Save favourite stations for quick access                       |
| FR13 |                  Show every price with its submission date                      |
| FR14  |                        Remove/correct self-made errors                          |
| FR15  |                        Edit/Delete Gas station/prices                        |

## Non Functional Requirements

| ID   | Type (efficiency, reliability, .. see iso 9126) |                                    Description                                     | Refers to |
| ---- | :---------------------------------------------: | :--------------------------------------------------------------------------------: | --------: |
| NFR1 |                    Usability                    |                    Application should be used with no training                     |    All FR |
| NFR2 |                   Performance                   |                     All functions should complete in < 0.5 sec                     |    All FR |
| NFR3 |                   Portability                   |                  The application should work on every platform                  |    All FR |
| NFR4 |                   Reliability                   | Modifications performed by users must be checked in a working day by an administrator |      FR12 |
| NFR5 |                   Reliability                   |            Fake data reports must be checked in at most one week by an administrator             |     FR 11 |
| NFR6 | Localisation |                     Dot and comma separator can be selected                     | FR4 FR7 FR12 |
| NFR7 | Localisation |                     Distance preferable unit of measure can be selected         | FR4 FR7 FR12 |
| NFR8 | Localisation |                     Currency symbol must be always visible                      | FR4 FR7 FR12 | 

# Use case diagram and use cases

## Use case diagram

```plantuml
left to right direction
actor User as u
left to right direction
actor MapSystem as map
left to right direction
actor Administrator as a

u --> (FR1 Add/update price/s of a gas station)
u --> (FR2 Add a Gas Station)
u --> (FR4 Show every gas station in a given area)
u --> (FR6 Report false informations)
u --> (FR7 Sort stations)
u --> (FR8 Search stations)
u --> (FR10 Leave a review)
u --> (FR11 Show gas station details)
u --> (FR12 Save favourite stations)
u --> (FR14 Remove/correct self made errors)
a --> (FR5 Add/Manage users and accounts)
a --> (FR9 Manage reports/modifications)
a --> (FR15 Edit/Delete Gas station/prices)

(FR1 Add/update price/s of a gas station) --> a
(FR2 Add a Gas Station) --> a
(FR6 Report false informations) --> a

(FR2 Add a Gas Station) --> map
(FR7 Sort stations) --> map
(FR8 Search stations) --> map
(FR4 Show every gas station in a given area) --> map
(FR11 Show gas station details) --> map

(FR3 Login) <.. (FR1 Add/update price/s of a gas station)
(FR3 Login) <.. (FR2 Add a Gas Station)
(FR3 Login) <.. (FR5 Add/Manage users and accounts)
(FR3 Login) <.. (FR6 Report false informations)
(FR3 Login) <.. (FR9 Manage reports/modifications)
(FR3 Login) <.. (FR10 Leave a review)
(FR3 Login) <.. (FR12 Save favourite stations)
(FR3 Login) <.. (FR14 Remove/correct self made errors)
(FR3 Login) <.. (FR15 Edit/Delete Gas station/prices)
```
## Use Cases

### Use case 1, UC1 - Add/Update price of a gas station (FR1)

| Actors Involved  |                             User                              |
| ---------------- | :-----------------------------------------------------------: |
|  Precondition     | User is logged and a station is already selected | 
|  Post condition     | Price is updated to X of Y type of fuel at Z station |
| Nominal Scenario | User login, selects gas station, fill fields to update price |
|  Variants     | User wants to edit/remove self-submitted price (if not approved yet) |

##### Scenario 1.1 
| Scenario 1.1 | |
| ------------- |:-------------:| 
|  Precondition     | User is logged in and a station is already selected |
|  Post condition     | Price is updated to X of Y type of fuel at Z station |
| Step#        | Description  |
|  1     | User open gas station details |  
|  1     | User selects the fuel type Y, if not present it can be added |  
|  2     | User inserts the listing X for said fuel |
|  3     | User submit the request |

### Use case 2, UC2 Add new gas station (FR2)
| Actors Involved  |                            User                             |
| ---------------- | :---------------------------------------------------------: |
| Precondition     |                List of gas stations L exists                |
| Postcondition   |           Define new gas station G, define prices           |
|                  |                          L.add(G)                           |
| Nominal Scenario | User login, fills the form to add a gas station, adds station X at a given point on map |
|  Variants     | User wants to remove self-submitted station |
|  Variants     | User does not need geo-locationing |

##### Scenario 2.1 
| Scenario 2.1 | |
| ------------- |:-------------:| 
|  Precondition     | User is logged in and position of the User is available |
|  Post condition     | Station X is added to the system |
| Step#        | Description  |
|  1     | User select the "Add new station.." option |  
|  2     | New station's location is pointing to User's position (if available) |
|  3     | User can manually correct new station's location  |
|  4     | User inserts info about new station |
|  5     | User can define petrol pumps and prices |
|  6     | User submit the request |

### Use case 3, UC3 Manage reports/modifications performed by users (FR9)

| Actors Involved  | Administrator |
| ---------------- | :-----------------------: |
| Precondition | Report/Request R |
| Postcondition | Report managed and closed |
| Nominal Scenario | Administrator selects a report/modifcation request, solve the issue and close it |
| Variants  |    |

##### Scenario 3.1 
| Scenario 3.1 | |
| ------------- |:-------------:| 
|  Precondition     |Report R |
|  Post condition     | Report closed |
| Step#        | Description  |
|  1     | Admin login to the manager service | 
|  2     | Admin selects a report | 
|  3     | Admin solves the issue | 
|  4     | Admin close the report | 

##### Scenario 3.2
| Scenario 3.2 | |
| ------------- |:-------------:| 
|  Precondition     |Add/Update request R |
|  Post condition     | Report closed |
| Step#        | Description  |
|  1     | Admin login to the manager service | 
|  2     | Admin select an add/update request | 
|  3     | Admin check data consistency | 
|  4     | Admin approve/reject request | 

### Use case 4, UC4 Show gas stations in the area (FR4 - FR7 - FR8)
| Actors Involved        | User, Map Provider |
| ------------- |:-------------:| 
|  Precondition     | User position is available |  
|  Post condition     | Map is populated with gas stations |
|  Nominal Scenario     | User wants to know interesting station in the area |
|  Variants     | User selects area of interest on his own |
|               | User applies filters |

##### Scenario 4.1
| Scenario 4.1 | |
| ------------- |:-------------:| 
|  Precondition     | |
|  Post condition     | Map is populated with gas stations |
| Step#        | Description  |
|  1     | User moves around the area of interest |  
|  2     | View updates with gas stations |

##### Scenario 4.2
| Scenario 4.2 | |
| ------------- |:-------------:| 
|  Precondition     | |
|  Post condition     | Map is populated with gas stations |
| Step#        | Description  |
|  1     | User selects filters |  
|  2     | User selects sorting option |  
|  3     | View updated with gas station matches these otions |

### Use case 5, UC5 Remove/correct self made errors (FR9)

| Actors Involved  | Administrator |
| ---------------- | :-----------------------: |
| Precondition | Submitted report/request R (not approved yet) |
| Postcondition | Report R deleted or modified |
| Nominal Scenario | User selects a submission, edit or remove it |
| Variants  |    |

##### Scenario 5.1
| Scenario 5.1 | |
| ------------- |:-------------:| 
|  Precondition     | User is logged in and price X was submitted by him (not approved yet) |
|  Post condition     | Price update request X of Y type of fuel at Z station is modified/removed |
| Step#        | Description  |
|  1     | User selects the price modification request |  
|  2     | User corrects/removes the request |

##### Scenario 5.2
| Scenario 5.2 | |
| ------------- |:-------------:| 
|  Precondition     | User is logged in and gas station G was submitted by him (not approved yet) |
|  Post condition     | Gas station is modified/removed  |
|  1     | User selects the added gas station |  
|  2     | User corrects/removes the request |

### Use case 6, UC6 report false information (FR6)

| Actors Involved  | Administrator |
| ---------------- | :-----------------------: |
| Precondition | User is logged in and station selected |
| Postcondition | Report R submitted |
| Nominal Scenario | User select a station, report a false information, send it |
| Variants  |    |

##### Scenario 6.1
| Scenario 6.1 | |
| ------------- |:-------------:| 
|  Precondition     | User is logged in and station selected |
|  Post condition     | Report R submitted |
| Step#        | Description  |
|  1     | User open gas station detail page | 
|  2     | User press button to send a report |  
|  3     | User report fake information |  
|  4     | User submit the report |

# Glossary

```plantuml
class EzGas

class User

class UserAccount {
+ id
+ email
+ password
+ #UpdatedPrices
+ #AddedStations
}

class Station {
+ id
+ name
+ latitude
+ longitude
+ addDate
+ #PetrolPump
+ status {pending, approved, rejected}
}

class UpdatePriceRequest {
+ id
+ price
+ date
+ status {pending, approved, rejected}
}

class PetrolPump {
+ id
+ type
+ currentPrice
+ lastUpdateDate
}

class Report {
+ id
+ description
+ status {opened, closed}
+ date
}

class Administrator {
+ id
+ password
}

class Favorite {
+ stationId
}

class Review {
+ id
+ rating {0 to 5}
+ text
}

EzGas -- "*" User : has
EzGas -- "*" Station : contains
Station "1" -- "*" PetrolPump : contains
UserAccount -- "*" Station : add
UserAccount -- "*" Favorite : has
UserAccount -- "*" UpdatePriceRequest : send
UserAccount -- "*" Report: send
UserAccount -- "*" Review: leave
Favorite -- "1" Station
Review -- "1" Station: retaled to
UpdatePriceRequest -- "1" PetrolPump : related to
Report -- "1" Station : related to
Administrator -- "*" Report: manage
Administrator -- "*" UpdatePriceRequest: manage
Administrator -- "*" Station: manage

User <|-- UserAccount
User <|-- Administrator

note right of PetrolPump : Types: Gasoline, Diesel, Bio-diesel
note left of Station: Station can be approved\n(visible by everyone),\n or in pending status if it has been\n added by a user and not approved yet.

note top of UpdatePriceRequest: The administrator manages update request\nof a petrol pump performed by a user.\nIf approved, also the corresponding petrol pump\nprice is updated with the given price.
```
# Deployment diagram

```plantuml

node server [
Server
]

node client [
Client
]

node map [
MapSystem
]


database database [
Database
  ----
Account
 ....
Data
]

server <--> client
server <--> database
server <-- map

```
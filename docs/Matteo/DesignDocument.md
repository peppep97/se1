# Design Document 


Authors: 

Date:

Version:


# Contents

- [Design Document](#design-document)
- [Contents](#contents)
- [Instructions](#instructions)
- [High level design](#high-level-design)
  - [Front End](#front-end)
  - [Back End](#back-end)
- [Low level design](#low-level-design)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document (see EZGas Official Requirements.md ). <br>
The design must comply with interfaces defined in package it.polito.ezgas.service (see folder ServicePackage ) <br>
UML diagrams **MUST** be written using plantuml notation.

# High level design 

The style selected is client - server. Clients can be smartphones, tablets, PCs.
The choice is to avoid any development client side. The clients will access the server using only a browser. 

The server has two components: the frontend, which is developed with web technologies (JavaScript, HTML, Css) and is in charge of collecting user inputs to send requests to the backend; the backend, which is developed using the Spring Framework and exposes API to the front-end.
Together, they implement a layered style: Presentation layer (front end), Application logic and data layer (back end). 
Together, they implement also an MVC pattern, with the V on the front end and the MC on the back end.



```plantuml
@startuml
package "Backend" {

}

package "Frontend" {

}


Frontend -> Backend
@enduml


```


## Front End

The Frontend component is made of: 

Views: the package contains the .html pages that are rendered on the browser and that provide the GUI to the user. 

Styles: the package contains .css style sheets that are used to render the GUI.

Controller: the package contains the JavaScript files that catch the user's inputs. Based on the user's inputs and on the status of the GUI widgets, the JavaScript controller creates REST API calls that are sent to the Java Controller implemented in the back-end.


```plantuml
@startuml
package "Frontend" {

    package "it.polito.ezgas.resources.views" {

    }


package "it.polito.ezgas.resources.controller" {

    }


package "it.polito.ezgas.resources.styles" {

    }



it.polito.ezgas.resources.styles -down-> it.polito.ezgas.resources.views

it.polito.ezgas.resources.views -right-> it.polito.ezgas.resources.controller


}
@enduml

```

## Back End

The backend  uses a MC style, combined with a layered style (application logic, data). 
The back end is implemented using the Spring framework for developing Java Entrerprise applications.

Spring was selected for its popularity and relative simplicity: persistency (M and data layer) and interactions are pre-implemented, the programmer needs only to add the specific parts.

See in the package diagram below the project structure of Spring.

For more information about the Spring design guidelines and naming conventions:  https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3



```plantuml
@startuml
package "Backend" {

package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"
   interface "UserService"
} 


package "it.polito.ezgas.controller" {

}

package "it.polito.ezgas.converter" {

}

package "it.polito.ezgas.dto" {

}

package "it.polito.ezgas.entity" {

}

package "it.polito.ezgas.repository" {

}

    
}
note "see folder ServicePackage" as n
n -- ps
```



The Spring framework implements the MC of the MVC pattern. The M is implemented in the packages Entity and Repository. The C is implemented in the packages Service, ServiceImpl and Controller. The packages DTO and Converter contain classes for translation services.



**Entity Package**

Each Model class should have a corresponding class in this package. Model classes contain the data that the application must handle.
The various models of the application are organised under the model package, their DTOs(data transfer objects) are present under the dto package.

In the Entity package all the Entities of the system are provided. Entities classes provide the model of the application, and represent all the data that the application must handle.




**Repository Package**

This package implements persistency for each Model class using an internal database. 

For each Entity class, a Repository class is created (in a 1:1 mapping) to allow the management of the database where the objects are stored. For Spring to be able to map the association at runtime, the Repository class associated to class "XClass" has to be exactly named "XClassRepository".

Extending class JpaRepository provides a lot of CRUD operations by inheritance. The programmer can also overload or modify them. 



**DTO package**

The DTO package contains all the DTO classes. DTO classes are used to transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database.

For each Entity class, a DTO class is created (in a 1:1 mapping).  For Spring the Dto class associated to class "XClass" must be called "XClassDto".  This allows Spring to find automatically the DTO class having the corresponding Entity class, and viceversa. 




**Converter Package**

The Converter Package contains all the Converter classes of the project.

For each Entity class, a Converter class is created (in a 1:1 mapping) to allow conversion from Entity class to DTO class and viceversa.

For Spring to be able to map the association at runtime, the Converter class associated to class "XClass" has to be exactly named "XClassConverter".




**Controller Package**

The controller package is in charge of handling the calls to the REST API that are generated by the user's interaction with the GUI. The Controller package contains methods in 1:1 correspondance to the REST API calls. Each Controller can be wired to a Service (related to a specific entity) and call its methods.
Services are in packages Service (interfaces of services) and ServiceImpl (classes that implement the interfaces)

The controller layer interacts with the service layer (packages Service and ServieImpl) 
 to get a job done whenever it receives a request from the view or api layer, when it does it should not have access to the model objects and should always exchange neutral DTOs.

The service layer never accepts a model as input and never ever returns one either. This is another best practice that Spring enforces to implement  a layered architecture.



**Service Package**


The service package provides interfaces, that collect the calls related to the management of a specific entity in the project.
The Java interfaces are already defined (see file ServicePackage.zip) and the low level design must comply with these interfaces.


**ServiceImpl Package**

Contains Service classes that implement the Service Interfaces in the Service package.










# Low level design

<Based on the official requirements and on the Spring Boot design guidelines, define the required classes (UML class diagram) of the back-end in the proper packages described in the high-level design section.>

```plantuml
class EZGas
class User {
 account_name
 account_pwd
 email
 trust_level
 --
 getUserById
 saveUser
 getAllUsers
 deleteUser
 login
 increaseUserReputation
 decreaseUserReputation
}
class Administrator
class GasStation {
 ID
 name
 address
 brand
 hasDiesel
 hasGasoline
 hasPremiumDiesel
 hasPremiumGasoline
 hasLPG
 hasMethane
 priceList
 --
 getGasStationById
 saveGasStation
 getAllGasStations
 deleteGasStation
 getGasStationsByGasolineType
 getGasStationsByProximity
 getGasStationsWithCoordinates
 getGasStationsWithoutCoordinates
 setReport
 getGasStationByCarSharing
 getPriceList
 getUserOfPriceList
} 
class GeoPoint {
 latitude
 longitude
}
class CarSharingCompany {
 name
}
class PriceList {
 time_tag
 dieselPrice
 gasolinePrice
 premiumDieselPrice
 premiumGasolinePrice
 LPGPrice
 methanePrice
 trust_level
}

Administrator -up-|> User
EZGas -- "*" User
EZGas -- "*" GasStation
GasStation "*" -- "0..1" CarSharingCompany
GasStation  -- "0..1" PriceList
User -- "*" PriceList
User "*" -- GeoPoint
GeoPoint -- GasStation
```






# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>


| 		| User | Administrator  | GasStation | GeoPoint  | PriceList | CarSharingCompany |
| ---------- |:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| FR1  	|x  |x	|  	|  	|  	|  	|
| FR1.1 |x 	|x 	|  	|  	|  	|  	|
| FR1.2 |x 	|x 	|  	|  	|  	|  	|
| FR1.3 |x 	|  	|  	|  	|  	|  	|
| FR1.4 |x 	|  	|  	|  	|  	|  	|
| FR2  	|x 	|x	|x 	|  	|  	|  	|
| FR3  	|  	|x 	|x 	|x 	|  	|  	|
| FR3.1 |  	|x 	|x 	|x 	|  	|  	|
| FR3.2 |  	|x 	|x 	|x 	|  	|  	|
| FR3.3	|  	|  	|x 	|x 	|  	|  	|
| FR4	|  	|  	|x 	|x 	|  	|  	|
| FR4.1 |  	|  	|x 	|x 	|  	|  	|
| FR4.2	|  	|  	|x 	|x 	|  	|  	|
| FR4.3	|  	|  	|x 	|x 	|x 	|  	|
| FR4.4	|  	|  	|x 	|x 	|x 	|  	|
| FR4.5	|  	|  	|x 	|x 	|x 	|x 	|
| FR5	|  	|x  |x 	|  	|x 	|  	|
| FR5.1	|x 	|x 	|x 	|  	|x 	|  	|
| FR5.2	|x	|  	|x 	|  	|x 	|  	|
| FR5.3	|x 	|  	|x 	|  	|x 	|  	|




# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>


```plantuml


```




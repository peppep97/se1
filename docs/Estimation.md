# Project Estimation  

Authors:

Date:

Version:

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition

### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   | 10                            |             
|  A = Estimated average size per class, in LOC       | 138                           | 
| S = Estimated size of project, in LOC (= NC * A) | 1380|
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 138                                      |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 4140| 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 1                   |               


# Estimate by activity decomposition

### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
| Requirement definition| 8 |
| UI prototype| 8 |
| Design| 10 |
| Implementation| 100 |
| Unit testing| 10 |
| Final changes| 2 |


###
Insert here Gantt chart with above activities

```plantuml
Project starts the 9th of september 2020
[Requirement definition] as [D] lasts 1 days
[Design] as [T] lasts 1 days
[T] starts at [D]'s end
[Implementation] as [I] lasts 20 days
[I] starts at [T]'s end
[Unit testing] as [U] lasts 2 days
[U] starts at [I]'s end
[Final changes] as [F] lasts 1 days
[F] starts at [U]'s end
```
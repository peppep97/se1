# Project Estimation  

Authors: Filippo Fontan, Giuseppe Pipero, Iman Ostovar, Matteo Pappadà

Date: 24/05/2020
 
Version: 1

# Contents

- [Estimation approach](#estimation-approach)
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition](#estimate-by-activity-decomposition)
    - [Gantt Chart](#gantt-chart)



# Estimation approach

This document reports an estimation of cost and effort based on two decomposition approaches. In estimation by product decomposition we have assumed an average size per class, in LOC, of 138 lines. We have also assumed a productivity of 10 LOC per person hour and a cost of 30 euros per 1 person hour. Finally, we have computed the estimated number of weeks required to complete the project, supposing that the team works 8 hours per day and 5 days per week.

# Estimate by product decomposition


|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   | 10                            |             
|  A = Estimated average size per class, in LOC       | 138                           | 
| S = Estimated size of project, in LOC (= NC * A) | 1380|
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 138                                      |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 4140| 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 1                   |               


# Estimate by activity decomposition


|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
| Requirement definition| 8 |
| UI prototype| 8 |
| Design| 10 |
| Implementation| 100 |
| Unit testing| 10 |
| Final changes| 2 |


### Gantt Chart

```plantuml
[Requirement definition] as [D] lasts 3 days
[UI Prototype] as [P] lasts 3 days
[P] starts at [D]'s end
[Design] as [T] lasts 3 days
[T] starts at [P]'s end
[Implementation] as [I] lasts 12 days
[I] starts at [T]'s end
[Unit testing] as [U] lasts 3 days
[U] starts at [I]'s end
[Final changes] as [F] lasts 1 days
[F] starts at [U]'s end
```
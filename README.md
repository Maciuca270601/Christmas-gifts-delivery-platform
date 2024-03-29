Name: Maciuca Alexandru Petru
Group: 324CA
Course: Object Oriented Programming Course
Title: Santa Claus is coming to ACS students

January 2022

# Info
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2

# About the code
    This is an idea of implementing a simple platform which provides annual information about
children and gifts.
    An important goal of the project was to gather all the information from "json" files
in order to complete certain actions and tasks based on it.

## Design Patterns
    The project is using a "Singleton Design Pattern" since all of the information is stored
in a database. For this specific project, I choose to use an "eager" implementation since
it was recommended on: "https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/static-final"
for projects that ar not using many resources such as mine.
    Another design pattern used is "Visitor Design Pattern" that is helping me in updating
certain attributes for classes such as "Child". (So far is the only one using this pattern
since it is the only class receiving updates)
    Factory is another important design pattern implemented that helps me create different
strategies and types of elves. Furthermore, Strategy design pattern is also present in order
to perform different sorting algorithms.

### Structure
    The homework is divided in several packages which contain classes that share the same
utility. In order to solve the homework I have added the following:

* package database: -> Database
                    -> Parser
Description: Since my platform is working with children and gifts, I needed to create my own
objects witch later I could save in specific arrays found in Database. It can also contain some
arrays for every type of gift in order to make the distribution process a lot easier.
All of the functions that work directly with these arrays could be found here.
             Parser has one main function that helps me build the Database.

* package entities: -> Child
                    -> Gift
Description: These classes represent the main objects used in my platform.

* package factory: -> BlackElfVisitor
                   -> ElfFactory
                   -> PinkElfVisitor
                   -> WhiteElfVisitor
                   -> YellowElfVisitor
Description: All of these classes modify certain attributes of a child such as the budget or
the list of received gifts so all of them could be visitors. In order to create them, ElfFactory
class has been created so that it receives a string type and returns the specific elf.

* package fileio: -> ChildInput
                  -> ChildUpdateInput
                  -> GiftInput
                  -> Input
                  -> InputLoader
                  -> YearDataInput
Description: Some of these classes are being used in order to gather information from all of the
"json" files and store it into arrays which could be found in InputLoader in order to make an
"Input" object. The later is being used to initialize the whole database with its specific
information. (the same principle is being applied in VideosDB)

* package solver: -> SolveFirstYear
                  -> SolveNextYears
                  -> Solver
Description: The first class does all the necessary steps for the first year of the simulation,
and the second does all the necessary updates and steps for the next years of the simulation.
Solver calls both classes in order to complete the entire simulation.

* package strategy: -> SortById
                    -> SortByNiceCity
                    -> SortByNiceScore
                    -> SortStrategy
                    -> StrategyFactory
Description: These classes contain different sorting algorithms based on specific criteria.
StrategyFactory has been implemented in order to create all of these strategies which will be
applied on the list of children found in the database.

* package utils: -> Utils
Description: This class contains functions that could be used in any class in order to solve
specific tasks.

* package visitor: -> Visitor
                   -> Visitable
                   -> AverageScoreVisitor
                   -> BudgetVisitor
                   -> GiveGiftVisitor
                   -> BonusScoreVisitor
                   -> CityScoreVisitor
Description: All the visitable objects can accept the visitor functions in order
to update their content.
"AverageScoreVisitor" sets the average niceScore for every child, whereas "BudgetVisitor"
sets its own budget. Based on this information, every child could receive gifts, using
"GiveGiftVisitor". Every city score is calculated with the help of "CityScoreVisitor" and
when it is needed "BonusScoreVisitor" could be applied to set a bonus for certain children.

* package write: -> SaveAnnualArray
                 -> SaveSimulationArrays
                 -> ChildOutput
                 -> GiftOutput
Description: These classes are being used in order to generate the "json" output files.

#### Difficulties
    I had a bit of trouble deciding how to organize my work and how to both read/write back
all the information from/to the "json" files.

##### Bibliography
Lab4: https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/static-final
Lab7: https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/visitor
JSON & Jackson: https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson

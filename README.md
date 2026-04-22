## Adventure Book Rest API

### Goal
Implement an advanture book REST API
### Subject
Adventure books have been around for decades, and they
immerse the reader-player in a fantastic and vibrant journey.
This kind of book is composed of small numbered sections, and
at the end of each section, the reader can decide what the next
step of his character will be. The book will then indicate you
what is the next section you should read, and so on.
How can a player die? Making a choice can have various
**consequences**, and some of it are life threatening. A player starts
with 10 health points and some actions will affect it (like combat,
falling, etc.). Once it reaches zero, the player dies and the
adventure is over.
### Main features
* The system should allow the reader-player to browse a collection of books.
* The system should allow the reader-player to select one and play through the book.
* The system should detect **Invalid State** of a book 
in any of the following conditions:
  * Book has none, or more than one beginning
  * Book has no ending (but can have multiple)
  * Book has invalid next section id.
  * A non-ending section has no options

### Objectives
#### Objective 1: List all existing books and allow for searching by title, author, category or difficulty.
  * UC 1 : A player can get the list of all existing books and search by one cirteria (title, author, category, difficulty)
    * list of all existing books
    * search by one cirteria
#### Objective 2: Retrieve a book’s details (ex: title, author, difficulty, categories) and allow for adding/removing categories from a book.
  * UC 2 : A player can get book's details an edit categories
    * book's details
    * edit categories
    
#### Objective 3: Allow to read a book and jump between sections.
#### Objective 4: Handle the consequences mechanism for a player.
#### Objective 5 (extra): Allow for different players each with its own progress (save, stop/pause a book)
#### Objective 6 (extra): Allow for adding new books to the collection

### Domain defintion
"_A book has an author and a difficulty level. Consider the difficulty
levels as Easy, Medium and Hard. It can also have categories,
example: FICTION, SCIENCE, HORROR, ADVENTURE, etc._"

Book is defined as follow : 
  * title String **mandatory**
  * author String **mandatory**
  * difficulty Enum **mandatory** : EASY, MEDIUM, HARD 
  * sections (List of Section) **mandatory**
  * type (List<Enum>) : FICTION, SCIENCE, HORROR, ADVENTURE, etc

Section is defined as follow :
  * id (int) **mandatory**
  * text (String) **mandatory**
  * type (Enum) **mandatory**
  * options (List of Option) **mandatory**

Option is defined as follow:
  * description(String) **mandatory**
  * gotoId (int) **mandatory**
  * consequence (Consequence)

Consequene is defined as follow:
  * type (Enum) **mandatory** : LOST_HEALTH, GAIN_HEALTH (make the type extensible)
  * value (String of int) **mandatory**
  * text (String) **mandatory**

Player is defined as follow:
  * id (long) **mandatory**
  * name (String) **mandatory**
  * healthPoints (int) **mandatory**

### Running
To run the mongo db from docker-compose
```
$ docker compose up -d
```
To run the application
```
$ ./mvnw spring-boot:run
```

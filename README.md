# WhoAreYou Personality Quiz

## Project Description

The WhoAreYou Quiz App is a web application that allows users to create and take quizzes.

## Developers

* [Aanis](https://github.com/AanisN10)
* [Janet](https://github.com/JMen121)
* [Piraven](https://github.com/PiravenNan)
* [Suzi](https://github.com/sctowers)

## Libraries Used

- Spring Boot
- Spring Data JPA
- Spring Web 
- Postgresql

## Setup Instructions

1. Clone the repository:

```bash
git  clone https://github.com/AanisN10/Backend_Project_API
```

2. Navigate to the project directory

```bash
cd WhoAreYou
```

3. Ensure you have Java and Maven installed

4. Build the project

```bash
mvn clean install
```

5. Start the application 

```bash
mvn spring-boot:run
```

6. Access the application at [https://localhost:8080](https://localhost:8080)

## Example Routes

### Quizzes

- **Get All Quizzes:**
  - Method: `GET`
  - URL: `/quizzes`

- **Get Quiz by ID:**
  - Method: `GET`
  - URL: `/quizzes/{id}`

- **Create New Quiz:**
  - Method: `POST`
  - URL: `/quizzes`
  - Request Body: JSON with quiz details
  - Example:
    ```json
    {
      "isFinished": false,
      "zsoltScore": 0,
      "colinScore": 0,
      "annaScore": 0,
      "thibyaaScore": 0,
      "numberOfQuestions": 10,
      "questionIds": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
    ```

- **Delete Quiz by ID:**
  - Method: `DELETE`
  - URL: `/quizzes/{id}`

### Questions

- **Get All Questions:**
  - Method: `GET`
  - URL: `/questions`

- **Get Question by ID:**
  - Method: `GET`
  - URL: `/questions/{id}`

- **Create New Question:**
  - Method: `POST`
  - URL: `/questions`
  - Request Body: JSON with question details
  - Example:
    ```json
    {
      "question": "If you could be a biscuit, which one would you be and why?",
      "zsoltAnswer": "D",
      "annaAnswer": "A",
      "colinAnswer": "B",
      "thibyaaAnswer": "C",
      "optionA": "Hobnob (not chocolate, original only!)",
      "optionB": "Ginger Nut - A good all-rounder",
      "optionC": "Viennese w/Chocolate - I have no reason, I was just eating them the day before",
      "optionD": "Wagon wheels - crunchy on the outside, soft on the inside",
      "quizIds": [1]
    }
    ```

- **Delete Question by ID:**
  - Method: `DELETE`
  - URL: `/questions/{id}`


### MVP and Extensions
- The MVP includes basic CRUD operations for quizzes and questions.
  * Add quiz questions
  * Display all quiz questions
  * Return a list of quiz questions with a shared property (provide “a quiz”)
  * Implement this firstly as a hard-coded route and then in relation to a Quiz object
  * Update a quiz question
  * Delete a quiz question
  * Provide a result/score based on the answers

- Extensions include features like 
  * Starting a quiz 
  * Creating a random quiz 
  * Submitting answers

## Diagrams
- Link to ERD and class diagrams 
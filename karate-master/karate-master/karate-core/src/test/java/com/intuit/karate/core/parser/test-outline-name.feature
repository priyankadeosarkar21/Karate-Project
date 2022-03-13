Feature:

Background:
  * def js_data =
  """
  [
    {
      "name": "Bob",
      "age": 10,
      "title": "name is Bob and age is 10"
    },
    {
      "name": "Nyan",
      "age": 5,
      "title": "name is Nyan and age is 150"
    }
  ]
  """

Scenario Outline: name is <name> and age is <age>
* def name = '<name>'
* match name == "#? _ == 'Bob' || _ == 'Nyan'"
* def title = karate.info.scenarioName

Examples:
| name   | age | title |
| Bob    | 10  | name is Bob and age is 10 |
| Nyan   | 5   | name is Nyan and age is 5 |


Scenario Outline: name is <name> and age is <age>
  * def name = '<name>'
  * match name == "#? _ == 'Bob' || _ == 'Nyan'"
  * def title = karate.info.scenarioName

Examples:
  | js_data |

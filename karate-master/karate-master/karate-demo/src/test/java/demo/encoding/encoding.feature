@parallel=false
Feature: encoding tests

Scenario: special characters in the url
    Given url demoBaseUrl + '/encoding/�Ill~Formed@RequiredString!'
    When method get
    Then status 200
    And match response == '�Ill~Formed@RequiredString!'

Scenario: special characters in the path
    Given url demoBaseUrl
    And path 'encoding', '�Ill~Formed@RequiredString!'
    When method get
    Then status 200
    And match response == '�Ill~Formed@RequiredString!'

Scenario: question mark in the url
    Given url demoBaseUrl + '/encoding/index.php%3F/api/v2'
    And path 'hello'
    When method get
    Then status 200
    And match response == 'hello'

Scenario: append trailing / to url
    Given url demoBaseUrl
    And path 'encoding', 'hello', ''
    When method get
    Then status 200
    And match response == 'hello/'

Scenario: path escapes special characters
    Given url demoBaseUrl
    And path 'encoding', '"<>#{}|\^[]`'
    When method get
    Then status 200
    And match response == '"<>#{}|\^[]`'

Scenario: leading / in path is not required
    Given url demoBaseUrl
    And path 'encoding', 'hello'
    When method get
    Then status 200
    And match response == 'hello'

Scenario: manually decode before using as the url
    * def encoded = 'encoding%2Ffoo%2Bbar'
    * def decoded = karate.urlDecode(encoded)
    Given url demoBaseUrl + '/' + decoded
    When method get
    Then status 200
    And match response == 'foo+bar'

Scenario: path but with forward-slashes
    Given url demoBaseUrl + '/encoding'
    And path '/hello/world/123/'
    When method get
    Then status 200
    And match response == 'hello/world/123'

Scenario: german xml
    Given url demoBaseUrl
    And path 'echo'
    And request <name>Müller</name>
    And header Content-Type = 'application/xml; charset=utf-8'
    When method post
    Then status 200
    And xml response = response
    And match response == <name>Müller</name>

Scenario: french json
    Given url demoBaseUrl
    And path 'echo'
    And request { givenName: 'oliàèôç' }
    And header Content-Type = 'application/json; charset=utf-8'
    When method post
    Then status 200
    And match response == { givenName: 'oliàèôç' }

@mock-servlet-todo
Scenario: french json ISO-8859-1
    Given url demoBaseUrl
    And path 'echo'
    And request { givenName: 'oliàèôç' }
    And header Content-Type = 'application/json; charset=ISO-8859-1'
    When method post
    Then status 200
    And match response == { givenName: '#string' }
    * def contentType = karate.prevRequest.headers['Content-Type'][0]
    * match contentType contains 'application/json'
    * match contentType contains 'charset=ISO-8859-1'

Scenario: french & german form field
    Given url demoBaseUrl
    And path 'echo', 'message'
    And form field text = 'oliàèôç Müller'
    And header Content-Type = 'application/x-www-form-urlencoded'
    When method post
    Then status 200
    And match response == 'oliàèôç Müller'

@mock-servlet-todo
Scenario: french & german multipart
    Given url demoBaseUrl
    Given path 'files'
    And multipart file myFile = { read: '../upload/karate-logo.jpg', filename: 'karate-logo.jpg', contentType: 'image/jpg' }
    And multipart field message = 'oliàèôç Müller'
    And header Content-Type = 'multipart/form-data; charset=utf-8'
    When method post
    Then status 200
    And match response == { id: '#uuid', filename: 'karate-logo.jpg', message: 'oliàèôç Müller', contentType: 'image/jpg' }

@mock-servlet-todo
Scenario: multipart but forcing the charset to NOT be sent
    Given url demoBaseUrl
    Given path 'files'
    And multipart file myFile = { read: '../upload/karate-logo.jpg', filename: 'karate-logo.jpg', contentType: 'image/jpg' }
    And multipart field message = 'oliàèôç Müller'
    # this ensures that "charset" does NOT appear in the Content-Type header
    # which is often required as some servers don't like it
    And configure charset = null
    When method post
    Then status 200
    And match response == { id: '#uuid', filename: 'karate-logo.jpg', message: 'oliàèôç Müller', contentType: 'image/jpg' }

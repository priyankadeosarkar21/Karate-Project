Feature: test that works un-changed for http as well as in-process servlet / mock-http

Scenario: get hello

When url demoBaseUrl
And path 'hello'
And param hello = 'hey'
When method get
Then status 200
And match response == 'hey world'

Scenario: post cat

When url demoBaseUrl
And path 'hello'
And request { name: 'Billie' }
When method post
Then status 200
And match response == { success: true }

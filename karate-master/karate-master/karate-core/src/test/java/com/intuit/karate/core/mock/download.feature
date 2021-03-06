Feature: file serving / download

Scenario: return a binary from the file-system
Given url mockServerUrl + 'download'
When method get
Then match responseBytes == read('test.pdf.zip')

Scenario: upload a binary file
* bytes body = read('test.pdf.zip')
Given url mockServerUrl + 'upload'
And request body
When method post
Then status 200
And match response.size == body.length

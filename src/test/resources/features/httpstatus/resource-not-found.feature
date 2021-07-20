Feature: Resource Not Found Feature

  Scenario: When the user hits an unavailable resource in the address bar, the browser should display the custom 404 page.

    Given A user opens up a browser`
    When He hits the following url 'http://localhost:8080/some-unavailable-resource'
    Then The browser should open a 404 page
    Then 'Shoppe : Resource Not Found' should be the title of the page.

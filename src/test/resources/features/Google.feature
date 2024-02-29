Feature: Google

  @test
  Scenario Outline: Search Text
    When I search text on Google "<text>"

    Examples:
      | text  |
      | Hello |
Scenario: User searches for a single step
 
Given user is on Home page

When user opens a post link
Then post page is shown

When user clicks log in with blank inputs
Then error is shown wpisz login lub email

When user type jankowalski and 12345 in the input
Then error in log in page shown Niestety podany login lub hasło jest błędne (sprawdź czy nie jest wciśnięty klawisz CapsLock)

When user write kowalski_jan91 and Test12345 in the input
Then Post box page is shown
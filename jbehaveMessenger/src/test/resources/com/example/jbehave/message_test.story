Given a server

When set connection server to inf.ug.edu.eu
Then invalid should return 1

When set connection server to inf.ug.edu.pl
Then valid should return 0


When try send a some message to inf.ug.edu.pl
Then ValidSendMessage should return 0


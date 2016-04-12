Given a server

When set connection server to inf.ug.edu.pl
Then valid should return 0

When set server connection to inf.ug.edu.eu
Then invalid should return 1



When try sending a ab to inf.ug.edu.eu
Then InvalidSendMessage should be return 2


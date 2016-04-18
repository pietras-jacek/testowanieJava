Given a server

When set connection server to inf.ug.edu.pl
Then valid should return 0

When set server connection to inf.ug.edu.eu
Then invalid should return 1

When sending valid message
Then sendMessage should return 0 or 2

When sending invalid message
Then sendMessage should return -1 or 2



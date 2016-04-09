Given a message

When set connection server to inf.ug.edu.eu
Then valid should return 0

When set connection server to inf.ug.edu.pl
Then invalid should return 1



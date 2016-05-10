#!/bin/bash

java -cp /opt/devel/db/hsqldb-2.3.3/lib/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb

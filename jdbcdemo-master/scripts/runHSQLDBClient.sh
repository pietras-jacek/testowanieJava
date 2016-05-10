#!/bin/sh

java -cp /opt/devel/db/hsqldb-2.3.3/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb

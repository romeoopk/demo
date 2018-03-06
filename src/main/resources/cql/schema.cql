CREATE KEYSPACE sample
WITH REPLICATION = {
'class' : 'SimpleStrategy',
'replication_factor' : 1
};

use sample;

CREATE TABLE IF NOT EXISTS sample.hotels (
id UUID PRIMARY KEY,
name text,
address text,
state text,
zip text);
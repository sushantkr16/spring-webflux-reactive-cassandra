CREATE KEYSPACE IF NOT EXISTS spring_reactive WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE spring_reactive.hackathon (
  team_name text,
  idea text,
  contact_email text,
  contact_phone text,
  registration_date timestamp,
  PRIMARY KEY ((name), registration_date)
) WITH CLUSTERING ORDER BY (registration_date DESC)


CREATE TABLE spring_reactive.team (
  team_name text,
  team_members_name text,
  contact_email text,
  contact_phone text,
  registration_date text,
  PRIMARY KEY ((team_name), registration_date)
) WITH CLUSTERING ORDER BY (registration_date DESC)


--Drop keyspace spring_reactive;
--drop table spring_reactive.team;
--drop table spring_reactive.hackathon;

select * from spring_reactive.team;



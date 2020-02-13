--Drop keyspace IF exists spring_reactive;
--drop table IF exists spring_reactive.team;
--drop table IF exists spring_reactive.idea;
--drop table IF exists spring_reactive.team_hackathon;
--drop table IF exists spring_reactive.hackathon;

CREATE KEYSPACE IF NOT EXISTS spring_reactive WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE spring_reactive.team (
  team_name text,
  team_members_name text,
  contact_email text,
  contact_phone text,
  registration_date date,
  PRIMARY KEY ((team_name), registration_date)
) WITH CLUSTERING ORDER BY (registration_date DESC)


CREATE TABLE spring_reactive.idea (
  team_name text,
  title text,
  idea_description text,
  contact_email text,
  idea_registration_date date,
  PRIMARY KEY ((team_name), idea_registration_date)
) WITH CLUSTERING ORDER BY (idea_registration_date DESC)



CREATE TABLE spring_reactive.team_hackathon (
  team_name text,
  team_details text,
  idea_details text,
  PRIMARY KEY (team_name)
)


CREATE TABLE spring_reactive.hackathon (
  hackathon_name text,
  first_contact_email text,
  second_contact_email text,
  first_mentor_name text,
  second_mentor_name text,
  hackathon_event_date date,
  PRIMARY KEY ((hackathon_name), hackathon_event_date)
) WITH CLUSTERING ORDER BY (hackathon_event_date DESC)
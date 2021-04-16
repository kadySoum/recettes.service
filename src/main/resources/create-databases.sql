create user postgres;
create database recettedb;
grant all privileges on database recettedb to postgres;

DROP TABLE IF EXISTS recettes;
CREATE TABLE recettes(id serial PRIMARY KEY, title VARCHAR(255), description TEXT, published BOOLEAN);
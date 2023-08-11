DROP DATABASE IF EXISTS word_game;
CREATE DATABASE word_game;
USE word_game;

DROP TABLE IF EXISTS words_table;
CREATE TABLE words_table
(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	a1 varchar(20),
	a2 varchar(20),
	a3 varchar(20),
	a4 varchar(20),
	a varchar(20),
	b1 varchar(20),
	b2 varchar(20),
	b3 varchar(20),
	b4 varchar(20),
	b varchar(20),
	c1 varchar(20),
	c2 varchar(20),
	c3 varchar(20),
	c4 varchar(20),
	c varchar(20),
	d1 varchar(20),
	d2 varchar(20),
	d3 varchar(20),
	d4 varchar(20),
	d varchar(20),
	final_word varchar(20)
);

DROP TABLE IF EXISTS players;
CREATE TABLE players
(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name varchar(20) UNIQUE,
	points int,
	games int,
	winrate float,
	wins int,
	losses int
);

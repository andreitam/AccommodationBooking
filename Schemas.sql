
DROP TABLE IF EXISTS accommodation CASCADE;

CREATE TABLE accommodation (
  	id uuid,
  	type character varying(32),
	bed_type character varying(32),
	max_guests int,
	description character varying(512),
	PRIMARY KEY (id)
);

SELECT * FROM accommodation;

DROP TABLE IF EXISTS room_fair CASCADE;

CREATE TABLE room_fair (
  	id uuid,
  	value float,
	season character varying(32),
	PRIMARY KEY (id)
);

SELECT * FROM room_fair;

DROP TABLE IF EXISTS accommodation_fair_relation CASCADE;

CREATE TABLE accommodation_fair_relation (
  	id uuid,
  	id_accommodation uuid,
	id_fair uuid,
	PRIMARY KEY (id),
	FOREIGN KEY (id_accommodation)
      REFERENCES accommodation (id),
	FOREIGN KEY (id_fair)
      REFERENCES room_fair (id)
);

SELECT * FROM accommodation_fair_relation;

/*SELECT acc.type, acc.bed_type, acc.max_guests
FROM accommodation acc INNER JOIN accommodation_fair_relation afr
ON acc.id = afr.id_accommodation;*/

/*SELECT rf.value, rf.season
FROM room_fair rf INNER JOIN accommodation_fair_relation afr
ON rf.id = afr.id_fair;*/

SELECT
	acc.type, acc.bed_type, acc.max_guests,
	rf.value, rf.season
FROM accommodation_fair_relation afr
INNER JOIN accommodation acc ON acc.id = afr.id_accommodation
INNER JOIN room_fair rf ON rf.id = afr.id_fair;


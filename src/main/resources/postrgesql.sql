-- Для постгреса немного подправим скрипт

DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT (
  ID SERIAL,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(40) NOT NULL,
  BIRTH_DATE DATE,
  VERSION INT NOT NULL DEFAULT 0,
  PRIMARY KEY (ID),
  UNIQUE(FIRST_NAME, LAST_NAME)
);

INSERT INTO contact (first_name, last_name, birth_date)
VALUES ('Chris', 'Schaefer', '1981-05-03');
INSERT INTO contact (first_name, last_name, birth_date)
VALUES ('Scott', 'Tiger', '1990-11-02');
INSERT INTO contact (first_name, last_name, birth_date)
VALUES ('John', 'Smith', '1964-02-28');
грузия
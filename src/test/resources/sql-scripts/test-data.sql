DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS movies(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    duration INT NOT NULL,
    genre VARCHAR(50) NOT NULL
);

INSERT INTO movies (name, duration, genre) values
    ('Dark Knight', 152, 'ACTION'),
    ('Memento',113, 'THRILLER'),
    ('There''s something About Mary', 119, 'COMEDY'),
    ('Super 8',112 , 'THRILLER'),
    ('Scream', 11, 'HORROR'),
    ('Home alone', 103, 'COMEDY'),
    ('Super man', 136, 'ACTION');
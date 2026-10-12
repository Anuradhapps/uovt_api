CREATE TABLE jobs (
                      id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      title VARCHAR(150) NOT NULL,
                      company VARCHAR(150) NOT NULL,
                      city VARCHAR(100) NOT NULL,
                      salary DECIMAL(12, 2) NOT NULL
);
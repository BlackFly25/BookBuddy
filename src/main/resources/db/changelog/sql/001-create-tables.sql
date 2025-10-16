CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE book (
                      id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      published_year INT NOT NULL,
                      genre VARCHAR(100)
);

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE review (
                        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                        book_id UUID REFERENCES book(id) ON DELETE CASCADE,
                        rating INT CHECK (rating BETWEEN 1 AND 5),
                        comment TEXT
);

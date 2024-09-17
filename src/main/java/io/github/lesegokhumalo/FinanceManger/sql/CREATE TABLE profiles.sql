CREATE TABLE profiles (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) REFERENCES users(username),
    income DOUBLE PRECISION,
    budget DOUBLE PRECISION
);

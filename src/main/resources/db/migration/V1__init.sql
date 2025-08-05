CREATE TABLE donor (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL
);

CREATE TABLE payment (
                         id UUID PRIMARY KEY,
                         method VARCHAR(50),
                         status VARCHAR(20),
                         external_id VARCHAR(255)
);

CREATE TABLE donation (
                          id SERIAL PRIMARY KEY,
                          donor_id INTEGER REFERENCES donor(id),
                          date TIMESTAMP,
                          amount INTEGER,
                          payment_id UUID REFERENCES payment(id)
);

CREATE TABLE beneficiary (
                             id SERIAL PRIMARY KEY,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             full_name VARCHAR(255) NOT NULL
);

CREATE TABLE help (
                      id SERIAL PRIMARY KEY,
                      beneficiary_id INTEGER REFERENCES beneficiary(id),
                      date TIMESTAMP,
                      amount INTEGER,
                      description TEXT,
                      payment_id UUID REFERENCES payment(id)
);

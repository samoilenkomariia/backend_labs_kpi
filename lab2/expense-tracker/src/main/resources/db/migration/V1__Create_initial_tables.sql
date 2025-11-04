CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE category (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    user_id UUID NULL,

    CONSTRAINT fk_category_user
                      FOREIGN KEY (user_id)
                      REFERENCES users(id)
                      ON DELETE CASCADE
);

CREATE TABLE record (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    amount DECIMAL(19, 2) NOT NULL,
    record_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id UUID NOT NULL,
    category_id UUID NOT NULL,

    CONSTRAINT fk_record_user
                    FOREIGN KEY (user_id)
                    REFERENCES users(id)
                    ON DELETE CASCADE,

    CONSTRAINT fk_record_category
                    FOREIGN KEY (category_id)
                    REFERENCES category(id)
                    ON DELETE RESTRICT
);

CREATE INDEX idx_category_user_id ON category(user_id);
CREATE INDEX idx_record_user_id ON record(user_id);
CREATE INDEX idx_record_category_id ON record(category_id);
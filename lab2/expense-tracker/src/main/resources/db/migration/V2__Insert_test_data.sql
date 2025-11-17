INSERT INTO users (id, name, password)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Alice', '$2a$10$86X3s54G/n2sA7yF5F.t7.d2t5l3d9h1P4N8m9G6J7B3C2A1O0E'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Bob', '$2a$10$86X3s54G/n2sA7yF5F.t7.d2t5l3d9h1P4N8m9G6J7B3C2A1O0E');

INSERT INTO category (id, name, user_id)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 'Groceries', NULL),
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Transport', NULL);

INSERT INTO category (id, name, user_id)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 'Alice Hobby', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

INSERT INTO record (id, amount, record_date, user_id, category_id)
VALUES
    ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a31', 95.50, NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21');

INSERT INTO record (id, amount, record_date, user_id, category_id)
VALUES
    ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32', 120.00, NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23');

INSERT INTO record (id, amount, record_date, user_id, category_id)
VALUES
    ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 30.25, NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22');
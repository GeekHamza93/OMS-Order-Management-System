INSERT INTO customers (first_name, last_name, email) VALUES
                                                         ('Alice','Martin','alice@example.com'),
                                                         ('Bilal','Haddad','bilal@example.com'),
                                                         ('Chloé','Dubois','chloe@example.com')
    ON CONFLICT DO NOTHING;

INSERT INTO orders (customer_id, status, amount)
SELECT id, 'CREATED', 129.90 FROM customers WHERE email='alice@example.com';

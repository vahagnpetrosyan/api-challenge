INSERT INTO products(id, name)
VALUES ('abc', 'Tennis ball');
INSERT INTO products(id, name)
VALUES ('fb1', 'Futball ball');
INSERT INTO products(id, name)
VALUES ('tr1', 'Tennis raquette');
INSERT INTO products(id, name)
VALUES ('tr2', 'Tennis raquette');

INSERT INTO locations(name)
VALUES ('yerevan');
INSERT INTO locations(name)
VALUES ('gyumri');

INSERT INTO productlocations (product_id, location_id, cnt)
VALUES ('tr1', 1, 1);
INSERT INTO productlocations (product_id, location_id, cnt)
VALUES ('tr2', 1, 1);
INSERT INTO productlocations (product_id, location_id, cnt)
VALUES ('tr2', 2, 1);


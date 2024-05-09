/* INSERT INTO fruitEntity(id, name) VALUES (1, 'Cherry');
INSERT INTO fruitEntity(id, name) VALUES (2, 'Apple');
INSERT INTO fruitEntity(id, name) VALUES (3, 'Banana');
ALTER SEQUENCE fruitEntity_seq RESTART WITH 4;
 */
-- force using the same if for entity and repository to facilitate testing
INSERT INTO app_user(id, username, password, role)VALUES (1, 'admin', 'admin', 'admin');

INSERT INTO address(address_id, street, neighborhood, city, number) VALUES (1, 'Rua timoteo', 'Itaim paulista', 'Sao Paulo', '63');
INSERT INTO address(address_id, street, neighborhood, city, number)VALUES (2, 'Rua angelica', 'Moema', 'Sao Paulo', '661');
INSERT INTO breed(breed_id, breed_name, size)VALUES (1, 'Cachorro Labrador', 'LARGE');
INSERT INTO breed(breed_id, breed_name, size)VALUES (2, 'Cachorro Bulldog', 'MEDIUM');
INSERT INTO breed(breed_id, breed_name, size)VALUES (3, 'Gato Persa', 'SMALL');
INSERT INTO animal(id, name, birth, address_id, breed_id, health_status) VALUES (1, 'Morgana', '2020-01-08', 1, 1, 'Saudável');
INSERT INTO animal(id, name, birth, address_id, breed_id, health_status) VALUES (2, 'Bob','2015-05-03', 2, 2, 'Saudável com limitações');
INSERT INTO animal(id, name, birth, address_id, breed_id, health_status) VALUES (3, 'Billy', '2024-03-01', 2, 3, 'Não saudável');
ALTER SEQUENCE animal_seq RESTART WITH 4;
ALTER SEQUENCE breed_seq RESTART WITH 4;
ALTER SEQUENCE address_seq RESTART WITH 3;
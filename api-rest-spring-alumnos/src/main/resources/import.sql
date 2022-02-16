INSERT INTO comunidad (id, nombre) VALUES (1, "Andalucia");
INSERT INTO comunidad (id, nombre) VALUES (2, "Aragon");
INSERT INTO comunidad (id, nombre) VALUES (3, "Castilla y Leon");
INSERT INTO comunidad (id, nombre) VALUES (4, "Castilla la Mancha");
INSERT INTO comunidad (id, nombre) VALUES (5, "Madrid");
INSERT INTO comunidad (id, nombre) VALUES (6, "Galicia");

INSERT INTO alumnado(nombre, apellido, dni, email, telefono, direccion, cp, comunidad) VALUES ("Catalin", "Petrehus", "X7459958T", "catalinpetrehus97@gmail.com", 643329097, "AV/Comunidad de Madrid", 28330, 3);

INSERT INTO alumnado(nombre, apellido, dni, email, telefono, direccion, cp, comunidad) VALUES ("Remus", "Petrehus", "X638263L", "remuspetrehus97@gmail.com", 626734592, "Calle 12 de Octubre", 23452, 1);

INSERT INTO alumnado(nombre, apellido, dni, email, telefono, direccion, cp, comunidad) VALUES ("Daniel", "Negrete", "X1325958T", "dnegl@gmail.com", 672934728, "Calle Quiñon 1", 28330, 5);

INSERT INTO alumnado(nombre, apellido, dni, email, telefono, direccion, cp, comunidad) VALUES ("David", "Ballesteros", "8374283Q", "relampo@gmail.com", 645529097, "Calle Lugo 2", 28330, 6);

INSERT INTO alumnado(nombre, apellido, dni, email, telefono, direccion, cp, comunidad) VALUES ("Virginia", "Gonzalez", "2674958T", "arllah@gmail.com", 643123097, "AV/ Atocha", 23230, 4);

INSERT INTO usuarios (username,password,enabled) VALUES ('rolando','$2a$10$VTMecMi.QwIynlpyuBHAAenhz9Wg2fEk4VbaOlXn.2xKkCJTMf75u',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin','$2a$10$nSgo1TPH4IQRro7HkVqrBO.cNC1cXrW5Xyhs5u/NwkjeEUh9Bo65G',1);

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);
-- Insertar registros en la tabla Departamentos
INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES ('Recursos Humanos', '123-456-7890', '123-456-7891');
INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES ('Tecnología', '123-456-7892', '123-456-7893');
INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES ('Marketing', '123-456-7894', '123-456-7895');

-- Insertar registros en la tabla Ingenieros
INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES ('Juan Perez', 'Software', 'Desarrollador');
INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES ('Maria Lopez', 'Redes', 'Administrador');
INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES ('Carlos Sanchez', 'Base de Datos', 'DBA');
INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES ('Ana Martinez', 'Seguridad', 'Analista de Seguridad');

-- Insertar registros en la tabla Proyectos
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto, IDIng) VALUES ('Sistema de Gestión', '2024-01-01', '2024-06-30', 2, 1);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto, IDIng) VALUES ('Redes Corporativas', '2024-02-01', '2024-07-31', 2, 2);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto, IDIng) VALUES ('Marketing Digital', '2024-03-01', NULL, 3, 3);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto, IDIng) VALUES ('Auditoría de Seguridad', '2024-04-01', '2024-12-31', 1, 4);

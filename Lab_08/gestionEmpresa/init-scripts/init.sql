-- Creación de la tabla Departamentos
CREATE TABLE Departamentos (
    IDDpto SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Telefono VARCHAR(15) NOT NULL,
    Fax VARCHAR(15)
);

-- Creación de la tabla Ingenieros
CREATE TABLE Ingenieros (
    IDIng SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Especialidad VARCHAR(255) NOT NULL,
    Cargo VARCHAR(255) NOT NULL
);

-- Creación de la tabla Proyectos
CREATE TABLE Proyectos (
    IDProy SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Fec_Inicio DATE NOT NULL,
    Fec_Termino DATE,
    IDDpto INTEGER NOT NULL,
    CONSTRAINT fk_departamento
        FOREIGN KEY(IDDpto) 
        REFERENCES Departamentos(IDDpto)
);

-- Creación de la tabla Proyecto_Ingeniero
CREATE TABLE Proyecto_Ingeniero (
    IDProy INTEGER NOT NULL,
    IDIng INTEGER NOT NULL,
    PRIMARY KEY (IDProy, IDIng),
    CONSTRAINT fk_proyecto
        FOREIGN KEY(IDProy) 
        REFERENCES Proyectos(IDProy),
    CONSTRAINT fk_ingeniero
        FOREIGN KEY(IDIng)
        REFERENCES Ingenieros(IDIng)
);

-- Creación de índices
CREATE INDEX idx_proyectos_nombre ON Proyectos(Nombre);
CREATE INDEX idx_ingenieros_nombre ON Ingenieros(Nombre);

-- Procedimiento almacenado para insertar un proyecto
CREATE OR REPLACE PROCEDURE sp_insertar_proyecto(
    p_nombre VARCHAR,
    p_fec_inicio DATE,
    p_fec_termino DATE,
    p_iddpto INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto)
    VALUES (p_nombre, p_fec_inicio, p_fec_termino, p_iddpto);
END;
$$;

-- Sentencias preparadas
PREPARE insertar_ingeniero (VARCHAR, VARCHAR, VARCHAR) AS
    INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES ($1, $2, $3);

PREPARE insertar_departamento (VARCHAR, VARCHAR, VARCHAR) AS
    INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES ($1, $2, $3);

PREPARE insertar_proyecto (VARCHAR, DATE, DATE, INTEGER, INTEGER) AS
    INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES ($1, $2, $3, $4);

-- Restricciones adicionales para mantener la consistencia de los datos
ALTER TABLE Proyectos
ADD CONSTRAINT chk_fecha CHECK (Fec_Termino IS NULL OR Fec_Termino >= Fec_Inicio);

--Inserciones de prueba
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
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES ('Sistema de Gestión', '2024-01-01', '2024-06-30', 2);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES ('Redes Corporativas', '2024-02-01', '2024-07-31', 2);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES ('Marketing Digital', '2024-03-01', NULL, 3);
INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto) VALUES ('Auditoría de Seguridad', '2024-04-01', '2024-12-31', 1);

-- Asignar ingenieros a proyectos en la tabla Proyecto_Ingeniero
INSERT INTO Proyecto_Ingeniero (IDProy, IDIng) VALUES (1, 1);
INSERT INTO Proyecto_Ingeniero (IDProy, IDIng) VALUES (2, 2);
INSERT INTO Proyecto_Ingeniero (IDProy, IDIng) VALUES (3, 3);
INSERT INTO Proyecto_Ingeniero (IDProy, IDIng) VALUES (4, 4);
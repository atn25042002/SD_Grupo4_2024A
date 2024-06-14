-- Creación de la tabla Departamentos
CREATE TABLE IF NOT EXISTS Departamentos (
    IDDpto SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Telefono VARCHAR(15) NOT NULL,
    Fax VARCHAR(15)
);

-- Creación de la tabla Ingenieros
CREATE TABLE IF NOT EXISTS Ingenieros (
    IDIng SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Especialidad VARCHAR(255) NOT NULL,
    Cargo VARCHAR(255) NOT NULL
);

-- Creación de la tabla Proyectos
CREATE TABLE IF NOT EXISTS Proyectos (
    IDProy SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Fec_Inicio DATE NOT NULL,
    Fec_Termino DATE,
    IDDpto INTEGER NOT NULL,
    IDIng INTEGER NOT NULL,
    CONSTRAINT fk_departamento
        FOREIGN KEY(IDDpto) 
        REFERENCES Departamentos(IDDpto),
    CONSTRAINT fk_ingeniero
        FOREIGN KEY(IDIng)
        REFERENCES Ingenieros(IDIng)
);

-- Creación de índices
CREATE INDEX IF NOT EXISTS idx_proyectos_nombre ON Proyectos(Nombre);
CREATE INDEX IF NOT EXISTS idx_ingenieros_nombre ON Ingenieros(Nombre);
-- Estructura de tabla para la tabla 'mitabla'
CREATE TABLE mitabla (
    DNI varchar(12) default NULL,
    correo varchar(32) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Estructura de tabla para la tabla 'miotratabla'
CREATE TABLE miotratabla (
    nombre varchar(20) default NULL,
    apellido varchar(20) default NULL,
    edad int(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE employee_details (
    empNum INT PRIMARY KEY,
    lastName VARCHAR(50),
    firstName VARCHAR(50),
    email VARCHAR(100),
    deptNum VARCHAR(10),
    salary DECIMAL(10, 2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Insertar datos de ejemplo
INSERT INTO employee_details (empNum, lastName, firstName, email, deptNum, salary) VALUES
(2001, 'Doe', 'John', 'john.doe@example.com', 'D001', 30000.00),
(2002, 'Smith', 'Jane', 'jane.smith@example.com', 'D002', 25000.00),
(2003, 'Brown', 'Charlie', 'charlie.brown@example.com', 'D001', 42000.00),
(2004, 'Johnson', 'Emily', 'emily.johnson@example.com', 'D003', 43000.00),
(2005, 'Davis', 'Michael', 'michael.davis@example.com', 'D002', 44000.00),
(2006, 'Miller', 'Sarah', 'sarah.miller@example.com', 'D003', 45000.00),
(2007, 'Wilson', 'David', 'david.wilson@example.com', 'D001', 46000.00),
(2008, 'Moore', 'Laura', 'laura.moore@example.com', 'D002', 47000.00),
(2009, 'Taylor', 'Daniel', 'daniel.taylor@example.com', 'D003', 48000.00),
(2010, 'Anderson', 'Sophia', 'sophia.anderson@example.com', 'D001', 49000.00);

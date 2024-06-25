/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
/**
 *
 * @author Lenovo
 */
public class Transaction_Management {
    
    public static void main(String[] args) {
        
        // Consultas SELECT para obtener datos de la tabla employee_details
        String QUERY = "SELECT * FROM employee_details WHERE empNum = 2001";
        String QUERY1 = "SELECT * FROM employee_details WHERE empNum = 2002";
        Boolean autoCommit;
        String update_query = "UPDATE employee_details SET salary = 41000 WHERE empNum = 2001"; 
        String update_query1 = "UPDATE employee_details SET salary = 42000 WHERE empNum = 2002"; 
        
        try (Connection conn = Database.getConnection()) {
            if (conn == null) {
                System.err.println("No se pudo establecer la conexión con la base de datos.");
                return;
            }

            Statement statemnt1 = conn.createStatement();
            ResultSet rs1 = null;

            // Ejecutar la consulta SELECT inicial
            rs1 = statemnt1.executeQuery(QUERY); 
            System.out.println("Obteniendo datos de la tabla employee_details");
            displayData(rs1);

            // Configurar auto-commit a false
            System.out.println("Configurando AutoCommit a FALSE");
            conn.setAutoCommit(false);
            autoCommit = conn.getAutoCommit();
            System.out.println("Valor de AutoCommit de la conexion = " + autoCommit);

            // Ejecutar una actualización y realizar commit
            System.out.println("Ejecutando consulta de actualizacion para actualizar el salario de EMPNUM = 2001");
            System.out.println("Consulta de actualizacion: " + update_query);
            int return_rows = statemnt1.executeUpdate(update_query);
            System.out.println("Datos actualizados pero no se ha hecho commit");

            // Mostrar datos después de la actualización usando una nueva conexión
            try (Connection conn1 = Database.getConnection()) {
                if (conn1 != null) {
                    System.out.println("Abriendo nueva conexion");
                    System.out.println("Datos de EMPNUM = 2001");
                    Statement statement2 = conn1.createStatement();
                    ResultSet rs;
                    rs = statement2.executeQuery(QUERY); 
                    displayData(rs);
                }
            }

            System.out.println("Commit realizado exitosamente");
            conn.commit();
            
            System.out.println("_____________________________________________________________");

            // Crear un punto de guardado (savepoint)
            Savepoint s1 = conn.setSavepoint();
            System.out.println("Se ha creado un SavePoint");

            // Mostrar datos usando la segunda conexión
            try (Connection conn1 = Database.getConnection()) {
                if (conn1 != null) {
                    Statement statement2 = conn1.createStatement();
                    ResultSet rs;
                    System.out.println("Usando la segunda conexion");
                    rs = statement2.executeQuery(QUERY); 
                    displayData(rs);
                }
            }

            ResultSet rs;
            rs = statemnt1.executeQuery(QUERY); 

            // Ejecutar otra actualización y realizar rollback
            System.out.println("Datos de EMPNUM = 2002");
            rs1 = statemnt1.executeQuery(QUERY1); 
            displayData(rs1); 
            System.out.println("Actualizando el salario de EMPNUM = 2002");
            System.out.println("Consulta de actualizacion: " + update_query1);
            statemnt1.executeUpdate(update_query1);
            System.out.println("Datos de EMPNUM = 2002 pero no se ha hecho commit");
            rs1 = statemnt1.executeQuery(QUERY1); 
            displayData(rs1);
            System.out.println("Realizando rollback... los datos actualizados no se reflejaran");

            // Realizar rollback al punto de guardado
            conn.rollback(s1);
            System.out.println("Datos de EMPNUM = 2002 despues de realizar rollback hasta el ultimo savepoint");
            rs1 = statemnt1.executeQuery(QUERY1); 
            displayData(rs1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void displayData(ResultSet rs1) throws SQLException {
        while (rs1.next()) {
            int empNum = rs1.getInt("empNum");
            String lastName = rs1.getString("lastName");
            String firstName = rs1.getString("firstName");
            String email = rs1.getString("email");
            String deptNum = rs1.getString("deptNum");
            String salary = rs1.getString("salary");
            System.out.println(empNum + "," + lastName + "," + firstName + "," + email + "," + deptNum + "," + salary);
        }
    }
}

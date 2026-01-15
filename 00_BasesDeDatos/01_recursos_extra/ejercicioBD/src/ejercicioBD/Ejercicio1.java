package ejercicioBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*con este import no es necesario poner los de arriba */
import java.sql.*;

/* si no os funcionan los import verificad el module info que suele dar problemas, tiene que aparecer asi*/
/*
 * module ejercicioBD {
	requires java.sql;
}
eso en caso de que sea un proyecto java, en el maven es diferente 
*/
public class Ejercicio1 {
	
	public static void main (String [] args) {
		
		/*conectado con el xampp a la ip del ordenador de julen */
		String url = "jdbc:mysql://10.5.6.53:3306/empresa_dam_v"; /* dirección ip + puerto */
		String consulta1 = "select * from departamento";
		String consulta2 = "select nomemp, puesto, salario from empleado where numdep = 10";
		String consulta3 = "select nomemp, salario, numdep from empleado where salario = (select max(salario) from empleado)";
	
		/*mejor crearlas fuera y settearlas a null*/
		Connection conn = null;
		Statement sentencia = null;
		ResultSet resultado1 = null;
		ResultSet resultado2 = null;
		ResultSet resultado3 = null;
		
		try {
			
			/* usuario y contraseña que en el xampp te tienen que dar permisos de usar la DB */
			conn = DriverManager.getConnection(url, "luis", "pepe");
			
			sentencia = conn.createStatement();
			
			resultado1 = sentencia.executeQuery(consulta1);
			System.out.println("consulta 1\n");
			while(resultado1.next()) {
				System.out.printf("%-15d%-15s%-15s \n",
						resultado1.getInt("numdep"),
						resultado1.getString("nomdep"),
						resultado1.getString("localidad"));
			}
			
			resultado2 = sentencia.executeQuery(consulta2);
			System.out.println("\nconsulta 2");
			while(resultado2.next()) {
				
				System.out.printf("\n%-15s%-15s%-15.2f",
						resultado2.getString("nomemp"),
						resultado2.getString("oficio"),
						resultado2.getFloat("salario")
						);
			}
			
			
			resultado3 = sentencia.executeQuery(consulta3);
			
			System.out.println("\n\nconsulta 3");
			while(resultado3.next()) {
				
				System.out.printf("\n%-15s%-15.2f%-15d",
						resultado3.getString("nomemp"),
						resultado3.getFloat("salario"),
						resultado3.getInt("numdep")
						);
			}
			
			
		}catch(SQLException ex) {
			/*poner esto en el catch es muy importante porque te chiva el tipo de error*/
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			/* falta el .close() de la conexión (creo) */
			
			
		}

	}
}

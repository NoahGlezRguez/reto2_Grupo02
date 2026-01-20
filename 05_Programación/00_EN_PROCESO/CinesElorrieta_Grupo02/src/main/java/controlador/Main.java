package controlador;

import java.util.Scanner;
import java.sql.*;

public class Main {

	public static String 	entrada;
	public static Scanner 	teclado = new Scanner(System.in);
	public static String		rutaBD = "jdbc:mysql://localhost:33080/CinesElorrieta";//ajustar a la ruta real 
	public static String		user = "admin";
	public static String		pw = "CinesElorrieta123";
	
	
	
	public static void main(String[] args) {
		
		Connection	conexion = null;
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		try {
			conexion = DriverManager.getConnection(rutaBD, user, pw);
			
			sentencia = conexion.createStatement();
			
			result = sentencia.executeQuery("Select * from tablaEjemplo"); //ajustar a lo que necesitemos
			
			//recorremos los resultados
			System.out.printf("Mostrando los resultados:", result);
		} catch(SQLException excpsql) {
			System.out.println("Error, no se pudo realizar la conexión con la base de datos");
		}
	}

}

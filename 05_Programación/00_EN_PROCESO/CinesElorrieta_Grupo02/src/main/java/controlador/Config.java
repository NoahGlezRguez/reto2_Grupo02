package controlador;
import java.io.*;

public class Config {
	

	
	/**
	 * <p>este método lee los datos de el fichero <b>ipConfig.txt</b>
	 * y los guarda en un array de la siguiente forma:</p></br>
	 * <ol start="0">
	 * <li>jdbc:mysql://ip:puerto/DatabaseName</li>
	 * <li>user</li>
	 * <li>password</li>
	 * </ol>
	 * 
	 * para modificar los datos se debe actualizar solo el contenido que está
	 * después de '='</br>
	 *  
	 * @return <b>String array </b></br>se utilizarán los index del array tal como
	 * se indica en la lista
	 */
	public static String [] datos(){
		
		String confi[] = new String[3];
		FileReader reader = null;
		BufferedReader buffer = null;
		String ruta = "src/main/java/files/ipConfig.txt";
		int cont = 0;
		
		try {
			
			reader = new FileReader(ruta);
			buffer = new BufferedReader(reader);
			String datos = "";
			
			while((datos = buffer.readLine()) != null) {
				
				confi[cont] = datos.substring(datos.indexOf('=')+1);
				cont++;
				
			}
			
			
			
		}catch(Exception e) {
			
			//rellenar
			
		}finally {
			
			try {
				
				buffer.close();
				
			}catch(Exception e) {
				
				//rellenar
			}
		}
		
		
		return confi;
		
	}

}

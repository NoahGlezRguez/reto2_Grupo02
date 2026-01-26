package controlador;
import java.io.*;

public class Config {
	
	public static String [] datos(){
		
		String confi[] = new String[3];
		FileReader reader = null;
		BufferedReader buffer = null;
		String ruta = "src/files/ipConfig.txt";
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

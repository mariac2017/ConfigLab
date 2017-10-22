package prg01.LabConf;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

public class LcConex {
	
	// constructor conexión a bases de datos
	
	// Para que la conexion funcione es necesario crear un folder
	// de Referenced Libraries con mysql-connector
	// luego entrar a propiedades y copiarlo alli
	// Por el menu principal seguir la ruta de :
	// TblLabConfig --> propiedades --> JavaBuildPath --> Add External Jars --> copia MySqlConnect
	
	private String usuario = "root";
	private String pwd = "root";
	private static String db= "db_labpregunta";
	static String url="jdbc:mysql://localhost/" + db;
	
	private Connection conn=null;
		
		public LcConex() {
			
			// System.out.println("url " + url);
			try {
				conn = (Connection)DriverManager.getConnection(url, usuario, pwd);
				if (conn != null){
				//	System.out.println("Conecto a " + conn);
				}
			}
			
			catch (SQLException ex){
				System.out.println("fallo la conexion " + conn);
			}

		}
	
		public void desconectar(){
			conn = null;
		}
		
		//Retornar la conexion
		public Connection getConnection(){
			return conn;
		}

		// *********************
		
		public PreparedStatement prepareStatement(String string) {
			// TODO Auto-generated method stub
			return null;
		}
}

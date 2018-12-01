package acceso;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DAO {
	private String usuario;
	private String contraseña;
	private String nombreDB;
	private String driver = "com.mysql.jdbc.Driver";
	private static Connection conexion = null;
	
	public DAO(String usuario, String contraseña, String nombreDB) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.nombreDB = nombreDB;
	}
	
	private Connection abrirConexion() throws IOException {
		if (conexion == null) {
			try {
				Runtime.getRuntime().addShutdownHook(new CerrarConexion());
				Class.forName(driver);
				conexion = (Connection) DriverManager.getConnection(nombreDB, usuario, contraseña);
				conexion.setAutoCommit(false);
				System.out.println("Abriendo conexion...");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}
	
	class CerrarConexion extends Thread {
		@Override
		public void run() {
			try {
				abrirConexion().close();
				System.out.println("... cerrando conexion");
			} catch (SQLException | IOException e) {
				new control.Errores(e);
			}
		}
	}
	
	private boolean commit(int resultado) throws SQLException {
		if (resultado == 1) {
			conexion.commit();
			return true;
		} else {
			conexion.rollback();
			return false;
		}
	}
	
	public boolean ejecutarUpdate(String sql) throws IOException, SQLException {
		abrirConexion();
		Statement myStatement = (Statement) conexion.createStatement();
		int resultado = myStatement.executeUpdate(sql);
		boolean retorno =  commit(resultado);
		CerrarConexion.currentThread();
		return retorno;
	}
	
	public ResultSet ejecutarSelect(String sql) throws IOException, SQLException {
		abrirConexion();
		Statement myStatement = (Statement) conexion.createStatement();
		ResultSet myResultSet = myStatement.executeQuery(sql);
		CerrarConexion.currentThread();
		return myResultSet;
	}
	
	
}

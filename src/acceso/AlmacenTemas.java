package acceso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;
import control.Errores;

public class AlmacenTemas {

	private String usuario = "root";
	private String contraseña = "";
	private String nombreDB;
	private DAO dao;

	public AlmacenTemas(String nombreBaseDatos) {
		this.nombreDB = "jdbc:mysql://localhost/" + nombreBaseDatos;
		this.dao = new DAO(usuario, contraseña, nombreDB);
	}

	public boolean guardarTema(String tema) throws IOException {
		try {
			return dao.ejecutarUpdate(sqlInsertarTema(tema));
		} catch (SQLException e) {
			new control.Errores(e);
			return false;
		}
	}

	private String sqlInsertarTema(String tema) {
		return "INSERT INTO `tema` (`tema`) VALUES ('" +tema +"');";
	}

	public ArrayList<String> leer()
			throws IOException, SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		ArrayList<String> temas = new ArrayList<>();
		CachedRowSet cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(dao.ejecutarSelect(sqlObtenerTemas()));
		while (cachedRowSet.next()) {
			temas.add(cachedRowSet.getString(1));
		}
		cachedRowSet.close();
		return temas;
	}

	private String sqlObtenerTemas() {
		return "SELECT * FROM `tema` ";
	}

	public String buscar(String tema)throws IllegalArgumentException, IllegalAccessException, SecurityException, IOException, SQLException {
		return dao.ejecutarSelect(sqlBuscarTema(tema)).getString(1);
	}

	private String sqlBuscarTema(String tema) {
		return "SELECT * FROM `tema` WHERE `tema` = '" + tema + "'";
	}

	public boolean borrarTema(String tema) throws IOException {
		try {
			return dao.ejecutarUpdate(sqlBorrarTema(tema));
		} catch (SQLException e) {
			return false;
		}
	}

	private String sqlBorrarTema(String tema) {
		return "DELETE FROM `tema` WHERE `tema` = '" + tema + "'";
	}

	public boolean modificarTema(String original, String modificado) throws IOException {
		try {
			return dao.ejecutarUpdate(sqlModificarTema(original, modificado));
		} catch (SQLException e) {
			e.printStackTrace();
			new Errores(e);
			return false;
		}
	}

	private final String sqlModificarTema(String original, String modificado) {
		return "UPDATE `tema` SET " + "`tema`= '" + modificado + "'  WHERE `ISBN` = '" + original + "'";
	}

}

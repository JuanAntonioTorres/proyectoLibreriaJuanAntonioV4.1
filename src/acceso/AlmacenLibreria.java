package acceso;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;
import control.Errores;
import modelo.Libro;

public class AlmacenLibreria {

	private String usuario = "root";
	private String contraseña = "";
	private String nombreDB;
	private DAO dao;
	
	public AlmacenLibreria(String nombreBaseDatos) {
		this.nombreDB = "jdbc:mysql://localhost/"+nombreBaseDatos;
		this.dao = new DAO(usuario, contraseña, nombreDB);
	}

	public boolean guardar(Libro libro) throws IOException {
		try {
			System.out.println(sqlInsertarLibro(libro));
			return dao.ejecutarUpdate(sqlInsertarLibro(libro));
		} catch (SQLException e) {
			new control.Errores(e);
			return false;
		}
	}
	
	public ArrayList<Libro> leer() throws IOException, SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		ArrayList<Libro> libros = new ArrayList<>();
		CachedRowSet cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(dao.ejecutarSelect(sqlObtenerLibros()));
		while(cachedRowSet.next()) {
			libros.add(crearLibroDesdeResultSet(cachedRowSet.getOriginalRow()));
		}
		cachedRowSet.close();
		return libros;
	}
	
	public ArrayList<String> obtenerFormatos() throws IOException, SQLException {
		ArrayList<String> formatos = new ArrayList<>();
		CachedRowSet cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(dao.ejecutarSelect(sqlObtenerFormatos()));
		while(cachedRowSet.next()) {
			formatos.add(cachedRowSet.getString(1));
			System.out.println(cachedRowSet.getString(1));
		}
		cachedRowSet.close();
		return formatos;
	}
	
	public ArrayList<String> obtenerEstados() throws IOException, SQLException {
		ArrayList<String> estados = new ArrayList<>();
		CachedRowSet cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(dao.ejecutarSelect(sqlObtenerEstados()));
		while(cachedRowSet.next()) {
			estados.add(cachedRowSet.getString(1));
		}
		cachedRowSet.close();
		return estados;
	}

	public Libro buscar(String isbn) throws IllegalArgumentException, IllegalAccessException, SecurityException, IOException, SQLException {
		return crearLibroDesdeResultSet(dao.ejecutarSelect(sqlBuscarLibro(isbn)));
	}
	
	private Libro crearLibroDesdeResultSet(ResultSet resultSet) throws IOException, SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		HashMap<String, Object> datosLibro = new HashMap<>();
		if(resultSet.first()) {
			for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
				datosLibro.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
			}
			return new Libro(datosLibro);
		}
		return null;
	}

	public boolean borrarLibro(String isbn) throws IOException {
		try {
			return dao.ejecutarUpdate(sqlBorrarLibro(isbn));
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean modificarLibro(Libro original, Libro modificado) throws IOException {
		try {
			return dao.ejecutarUpdate(sqlModificarLibro(original, modificado));
		} catch (SQLException e) {
			e.printStackTrace();
			new Errores(e);
			return false;
		}
	}
	
	private final String sqlInsertarLibro(Libro obLibro) {
		return "INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `tema`, `paginas`, `formato`, `estado`, `editorial`, `unidades`) VALUES ('"
				+ obLibro.getIsbn()
				+ "' , '"
				+ obLibro.getTitulo()
				+ "' , '"
				+ obLibro.getAutor()
				+ "' , '"
				+ obLibro.getTema()
				+ "' , '"
				+ obLibro.getPaginas()
				+ "' , '"
				+ obLibro.getFormato()
				+ "' , '"
				+ obLibro.getEstado()
				+ "' , '"
				+ obLibro.getEditorial()
				+ "' , '"
				+ obLibro.getUnidades()
				+"');";
	}

	private final String sqlBorrarLibro(String isbn) {
		return "DELETE FROM `libro` WHERE `isbn` = '" + isbn + "'";
	}

	private final String sqlBuscarLibro(String isbn) {
		return "SELECT * FROM `libro` WHERE `isbn` = '"+ isbn + "'";
	}

	private final static String sqlObtenerLibros() {
		return "SELECT * FROM `libro` ";
	}

	private final String sqlModificarLibro(Libro original, Libro modificado) {
		return "UPDATE `libro` SET "
				+ "`isbn`= '" + original.getIsbn()
				+ "' ,`titulo`= '" + modificado.getTitulo()
				+ "' ,`autor`= '" + modificado.getAutor() 
				+ "' ,`tema`= '" + modificado.getTema() 
				+ "' ,`paginas`= '" + modificado.getPaginas() 
				+ "' ,`formato`= '" + modificado.getFormato()
				+ "' ,`estado`= '" + modificado.getEstado() 
				+ "' ,`editorial`= '" + modificado.getEditorial()
				+ "' ,`unidades`= '" + modificado.getUnidades()
				+ "'  WHERE `ISBN` = '" + original.getIsbn() + "'";
	}

	private final String sqlObtenerFormatos() {
		return "SELECT `formato` FROM `formato` WHERE 1";
	}

	private final String sqlObtenerEstados() {
		return "SELECT `estado` FROM `estado` WHERE 1";
	}
	

}

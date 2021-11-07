package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Frame.Inicio;
import Frame.Principal;

public class Modelo {

	private Inicio miInicio;
	private Principal miPrincipal;
	private DefaultTableModel miModelito;
	private String querySQL = "SELECT * FROM escuderias";
	private Connection conexion;
	private String db = "formula__1";

	public void setPrincipal(Principal miPrincipal) {
		this.miPrincipal = miPrincipal;
	}

	public void setInicio(Inicio miInicio) {
		this.miInicio = miInicio;
	}

	public TableModel cargarModelito() {
		return miModelito;
	}

	// Método para conectarse a la base de datos
	public void conexionBD() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + db, "root", "");
			System.out.println("Conectado a localhost MySql");
		} catch (Exception e) {
			System.out.println("Error:");
			e.printStackTrace();
		}
	}

	// Método para eliminar info de la tabla, será llamado desde el botón
	public void delete(JTable tabla, DefaultTableModel modelo) {
		try {
			modelo = miModelito;
			tabla = miPrincipal.getTable();
			int row = tabla.getSelectedRow();
			String value = tabla.getModel().getValueAt(row, 0).toString();
			int aux = Integer.parseInt(value);
			String query = "DELETE FROM escuderias WHERE id = ?";
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, aux);
			pstmt.executeUpdate();
			modelo.removeRow(tabla.getSelectedRow());
			System.out.println("Se han eliminado datos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para añadir info de la tabla, será llamado desde el botón
	public void add(String txtNombrePiloto, String txtNombreEscuderia) {
		try {
			String query = "INSERT INTO escuderias (nombre_piloto, nombre_escuderia) VALUES (?, ?);";
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, txtNombrePiloto);
			pstmt.setString(2, txtNombreEscuderia);
			pstmt.executeUpdate();
			System.out.println("Se han insertado datos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para editar info de la tabla, será llamado desde el botón
	public void edit(String txtNombrePiloto, String txtNombreEscuderia) {
		try {
			JTable tabla = miPrincipal.getTable();
			int fila = tabla.getSelectedRow();
			String auxiliar = tabla.getModel().getValueAt(fila, 0).toString();
			int id = Integer.parseInt(auxiliar);
			String query = "UPDATE ESCUDERIAS SET nombre_piloto = ?, nombre_escuderia = ? WHERE id = " + id;
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, txtNombrePiloto);
			pstmt.setString(2, txtNombreEscuderia);
			pstmt.executeUpdate();
			System.out.println("Se han editado datos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para cargar la BD en la tabla, será llamado al cargar la pantalla
	public void loadDBTable() {
		int numberColumns = numCols(querySQL);
		int numberRows = numRows(querySQL);
		String[] header = new String[numberColumns];
		Object[][] content = new Object[numberRows][numberColumns];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(querySQL);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numberColumns; i++) {
				header[i] = rsmd.getColumnName(i + 1);
			}
			int row = 0;
			while (rset.next()) {
				for (int col = 1; col <= numberColumns; col++) {
					content[row][col - 1] = rset.getString(col);
				}
				row++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		miModelito = new DefaultTableModel(content, header);
		miPrincipal.setModelito(miModelito);
	}

	// Recoge el número de columnas de la tabla de la base de datos
	private int numCols(String sql) {
		int cols = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			cols = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cols;
	}

	// Recoge el número de filas de la tabla de la base de datos
	private int numRows(String sql) {
		int rows = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				rows++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
}

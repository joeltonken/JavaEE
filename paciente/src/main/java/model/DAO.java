package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbpaciente?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "12345";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir paciente.
	 *
	 * @param paciente the paciente
	 */
	public void inserirPaciente(JavaBeans paciente) {
		String create = "insert into pacientes (nome, idade, peso, altura) values (?, ?, ?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, paciente.getNome());
			pst.setString(2, paciente.getIdade());
			pst.setDouble(3, paciente.getPeso());
			pst.setDouble(4, paciente.getAltura());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar pacientes.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarPacientes() {
		ArrayList<JavaBeans> pacientes = new ArrayList<>();
		String read = "select * from pacientes order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				double peso = rs.getDouble(4);
				double altura = rs.getDouble(5);
				pacientes.add(new JavaBeans(idcon, nome, idade, peso, altura));
			}
			con.close();
			return pacientes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar paciente.
	 *
	 * @param paciente the paciente
	 */
	public void selecionarPaciente(JavaBeans paciente) {
		String read2 = "select * from pacientes where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, paciente.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				paciente.setIdcon(rs.getString(1));
				paciente.setNome(rs.getString(2));
				paciente.setIdade(rs.getString(3));
				paciente.setPeso(rs.getDouble(4));
				paciente.setAltura(rs.getDouble(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar paciente.
	 *
	 * @param paciente the paciente
	 */
	public void alterarPaciente(JavaBeans paciente) {
		String update = "update pacientes set nome=?,idade=?,peso=?,altura=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, paciente.getNome());
			pst.setString(2, paciente.getIdade());
			pst.setDouble(3, paciente.getPeso());
			pst.setDouble(4, paciente.getAltura());
			pst.setString(5, paciente.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletar paciente.
	 *
	 * @param paciente the paciente
	 */
	public void deletarPaciente(JavaBeans paciente) {
		String delete = "delete from pacientes where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, paciente.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

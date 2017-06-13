package br.univel.jhonatan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao {
	private Connection con;
	private static final String delete_from = "DELETE FROM pessoa WHERE ID = ?";
	private static final String select_from = "SELECT * FROM pessoa";
	private static final String insert_into = "INSERT INTO pessoa(id,nome,idade,telefone)"
			+ "VALUES (?,?,?)";
	
	public List<Pessoa> buscarTodos() {
		
		con = Conexao.newInstance().getConecao();
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		try {
			PreparedStatement ps = con.prepareStatement(select_from);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setIdade(rs.getInt(3));
				p.setTelefone(rs.getString(4));
				lista.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public void salvar(Pessoa p) {
		con = Conexao.newInstance().getConecao();
		
		try {
			PreparedStatement ps = con.prepareStatement(insert_into);
			
			int id = p.getId();
			String nome = p.getNome();
			int idade = p.getIdade();
			String telefone = p.getTelefone();
			
			ps.setInt(1,  id);
			ps.setString(2, nome);
			ps.setInt(3, idade);
			ps.setString(4, telefone);
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirItem(int intId) {
		con = Conexao.newInstance().getConecao();
		try {
			PreparedStatement ps = con.prepareStatement(delete_from);
			ps.setInt(1, intId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

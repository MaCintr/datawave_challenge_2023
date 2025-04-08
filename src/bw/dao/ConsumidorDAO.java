package bw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bw.conexao.Conexao;
import bw.consumidor.Consumidor;

public class ConsumidorDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;

	public ConsumidorDAO() {
		conexao = new Conexao();
	}

	public void inserir(Consumidor consumidor) {
		sql = "insert into bw_consumidor values(?, ?, ?, ?, ?, ?)";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, consumidor.getIdConsumidor());
			ps.setString(2, consumidor.getNome());
			ps.setString(3, consumidor.getEmail());
			ps.setLong(4, consumidor.getTelefone());
			ps.setInt(5, consumidor.getIdade());
			ps.setString(6, consumidor.getEndereco());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar usuário\n" + e);
		}

	}

	public List<Consumidor> listar() {
		List<Consumidor> lista = new ArrayList<>();
		sql = "select * from bw_consumidor order by id_consumidor";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				int telefone = rs.getInt("telefone");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				Consumidor consumidor = new Consumidor(0, nome, email, telefone, idade, endereco);
				lista.add(consumidor);
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao listar consumidor\n" + e);
		}

		return lista;
	}

	public Consumidor pesquisar(int id) {
		Consumidor consumidor = null;
		sql = "select * from bw_consumidor where id_consumidor = ?";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				int telefone = rs.getInt("telefone");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				consumidor = new Consumidor(0, nome, email, telefone, idade, endereco);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return consumidor;
	}

	public void alterar(Consumidor consumidor) {
		sql = "update bw_consumidor set nome = ?, email = ?, telefone = ?, idade = ?, "
				+ "endereco = ? where id_consumidor = ?";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setString(1, consumidor.getNome());
			ps.setString(2, consumidor.getEmail());
			ps.setLong(3, consumidor.getTelefone());
			ps.setInt(4, consumidor.getIdade());
			ps.setString(5, consumidor.getEndereco());
			ps.setInt(6, consumidor.getIdConsumidor());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao pesquisar usuário\n" + e);
		}
	}
	public void remover(int id) {
		sql = "delete from bw_consumidor where id_consumidor = ?";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao pesquisar usuário\n" + e);
		}	
	}
}
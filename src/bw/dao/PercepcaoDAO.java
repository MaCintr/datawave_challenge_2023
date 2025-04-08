package bw.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bw.acaoEsg.AcaoEsg;
import bw.conexao.Conexao;
import bw.consumidor.Consumidor;
import bw.percepcao.Percepcao;

public class PercepcaoDAO {

	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;

	public PercepcaoDAO() {
		conexao = new Conexao();
	}

	public void inserir(Percepcao per) {
		sql = "insert into bw_percepcao values(?, ?, ?, ?, ?)";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, per.getIdPercepcao());
			ps.setInt(2, per.getConsumidor().getIdConsumidor());
			ps.setInt(3, per.getAcaoEsg().getIdAcao());
			ps.setInt(4, per.getAvaliacao());
			ps.setString(5, per.getComentario());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar percepcao\n" + e);
		}

	}
	public Percepcao pesquisar(int id) {
		Percepcao percepcao = null;
		sql = "SELECT\r\n"
				+ "    bw_percepcao.*,\r\n"
				+ "    c.nome AS consumidor,\r\n"
				+ "    a.ds_acao AS acao\r\n"
				+ "FROM\r\n"
				+ "    bw_percepcao\r\n"
				+ "INNER JOIN\r\n"
				+ "    bw_consumidor c ON bw_percepcao.id_consumidor = c.id_consumidor\r\n"
				+ "INNER JOIN\r\n"
				+ "    bw_acao_esg a ON bw_percepcao.id_acao = a.id_acao\r\n"
				+ "    WHERE id_percepcao = ?";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String nome = rs.getString("consumidor");
				String acao = rs.getString("acao");
				int avaliacao = rs.getInt("avaliacao");
				String coment = rs.getString("comentario");
				Consumidor cons = new Consumidor(0, nome, null, 0, 0, null);
				AcaoEsg acaoEsg = new AcaoEsg(0, acao);
				percepcao = new Percepcao(id, cons, acaoEsg, avaliacao, coment);
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao pesquisar percepcao\n" + e);
		}
		return percepcao;
	}
	public void remover(int id) {
		sql = "delete from bw_percepcao where id_percepcao = ?";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("erro ao pesquisar feedback\n" + e);
		}	
}
}

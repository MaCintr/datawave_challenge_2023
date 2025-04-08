package bw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bw.conexao.Conexao;
import bw.acaoEsg.AcaoEsg;

public class AcaoEsgDAO {
	private PreparedStatement ps;
	private String sql;
	private Conexao conexao;
	private ResultSet rs;

	public AcaoEsgDAO() {
		conexao = new Conexao();
	}

	public List<AcaoEsg> listar() {
		List<AcaoEsg> lista = new LinkedList<>();
		sql = "select * from bw_acao_esg order by id_acao";

		try (Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new AcaoEsg(rs.getInt("id_acao"), rs.getString("ds_acao")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return lista;
	}
	public void inserir(AcaoEsg acao) {
		sql = "insert into bw_acao_esg(id_acao, ds_acao) values(?, ?)";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, acao.getIdAcao());
			ps.setString(2, acao.getDescricao());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public boolean pesquisar(int id) {
		boolean aux = false;
		sql = "select * from bw_acao_esg where id_acao = ?";
		
		try(Connection connection = conexao.conectar()) {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				aux = true;
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return aux;
	}
}

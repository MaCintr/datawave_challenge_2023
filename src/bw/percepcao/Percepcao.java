package bw.percepcao;

import java.sql.Date;

import bw.acaoEsg.AcaoEsg;
import bw.consumidor.Consumidor;

public class Percepcao {
	private int idPercepcao;
	private Consumidor consumidor;
	private AcaoEsg acao;
	private int avaliacao;
	private String comentario;


	public Percepcao(int idPercepcao, Consumidor consumidor, AcaoEsg acao, int avaliacao,
			String comentario) {
		super();
		this.idPercepcao = idPercepcao;
		this.consumidor = consumidor;
		this.acao = acao;
		this.avaliacao = avaliacao;
		this.comentario = comentario;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getIdPercepcao() {
		return idPercepcao;
	}

	public void setIdPercepcao(int idPercepcao) {
		this.idPercepcao = idPercepcao;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public AcaoEsg getAcaoEsg() {
		return acao;
	}

	public void setAcaoEsg(AcaoEsg acaoEsg) {
		this.acao = acaoEsg;
	}

	@Override
	public String toString() {
		return "Usuário: " + consumidor.getNome() 
		+ "\nAção ESG: " + acao.getDescricao()
		+ "\nAvaliação: " + avaliacao + "\nComentário: " + comentario;
	}
}

package bw.acaoEsg;

public class AcaoEsg {
	private int idAcao;
	private String descricao;

	public AcaoEsg(int idAcao, String descricao) {
		super();
		this.idAcao = idAcao;
		this.descricao = descricao;

	}

	public String getDados() {
		return "ID: " + idAcao + "\nAção: " + descricao;
	}

	public int getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(int idAcao) {
		this.idAcao = idAcao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

package bw.consumidor;

public class Consumidor {
	private int idConsumidor;
	private String nome;
	private String email;
	private int telefone;
	private int idade;
	private String endereco;

	public Consumidor(int idConsumidor, String nome, String email, int telefone, int idade, String endereco) {
		super();
		this.idConsumidor = idConsumidor;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.idade = idade;
		this.endereco = endereco;
	}

	public int getIdConsumidor() {
		return idConsumidor;
	}

	public void setIdConsumidor(int idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}


	@Override
	public String toString() {
		return "Consumidor [idConsumidor=" + idConsumidor + ", nome=" + nome + ", email=" + email + ", telefone="
				+ telefone + ", idade=" + idade + ", endereco=" + endereco + "]";
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}

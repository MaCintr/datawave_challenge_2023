package bw.menu;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.List;

import bw.acaoEsg.AcaoEsg;
import bw.consumidor.Consumidor;
import bw.dao.AcaoEsgDAO;
import bw.dao.ConsumidorDAO;
import bw.dao.PercepcaoDAO;
import bw.exception.OpcaoException;
import bw.percepcao.Percepcao;

public class Menu {

	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if(opcao < 1 || opcao > 11) {
					throw new OpcaoException("Opção inválida");
				} else {
					switch(opcao) {
						case 1:
							cadastrarUsuario();
							break;
						case 2:
							listarUsuario();
							break;
						case 3:
						    pesquisarUsuario();
						    break;
						case 4:
							alterarUsuario();
							break;
						case 5:
						    excluirUsuario();
						    break;
						case 6:
							cadastrarFeedback();
							break;
						case 7:
							cadastrarAcao();
							break;
						case 8:
							listarAcao();
							break;
						case 9:
							pesquisarFeedback();
							break;
						case 10:
							excluirFeedback();
							break;
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número\n" + e);
			}
			catch(OpcaoException e) {
				showMessageDialog(null, e.getMessage());
			}
		} while(opcao != 11);		
	}
	
	private void excluirFeedback() {
		PercepcaoDAO dao = new PercepcaoDAO();
		int id = parseInt(showInputDialog("ID"));
		Percepcao perc = dao.pesquisar(id);
		if (perc == null) {
			showMessageDialog(null, "Feedback não encontrado");
		} else {
			dao.remover(id);
		}
	}

	private void pesquisarFeedback() {
		PercepcaoDAO dao = new PercepcaoDAO();
		int id = parseInt(showInputDialog("ID"));
		Percepcao perc = dao.pesquisar(id);
		if (perc == null) {
			showMessageDialog(null, "Feedback não encontrado.");
		} else {
			showMessageDialog(null, perc);
		}
	}

	private void cadastrarAcao() {
		AcaoEsgDAO dao = new AcaoEsgDAO();
		int id = parseInt(showInputDialog("ID"));
		if (dao.pesquisar(id)) {
			showMessageDialog(null, "Ação já existe.");
		} else {
			String desc = showInputDialog("Descrição\nExemplo: 'Ação Social'");
			AcaoEsg esg = new AcaoEsg(id, desc);
			dao.inserir(esg);
		}
	}

	private void cadastrarFeedback() {
		PercepcaoDAO perDao = new PercepcaoDAO();
		AcaoEsgDAO acaoDao = new AcaoEsgDAO();
		ConsumidorDAO consDao = new ConsumidorDAO();
		int id = parseInt(showInputDialog("ID"));
		Percepcao percepcao = perDao.pesquisar(id);
		if (percepcao != null) {
			showMessageDialog(null, "ID já cadastrado");
		} else {
		
			int idUser = parseInt(showInputDialog("ID Usuário:\n"));
			int idAcao = parseInt(showInputDialog("ID Ação ESG:\n"));
			int nota = parseInt(showInputDialog("Avaliação"));
			if (nota > 10 || nota < 1) {
				showMessageDialog(null, "Nota inválida");
			} else {
				String coment = showInputDialog("Comentário");
				AcaoEsg acao = new AcaoEsg(idAcao, null);
				Consumidor cons = new Consumidor(idUser, null, null, 0, 0, null);
				Percepcao per = new Percepcao(id, cons, acao, nota, coment);
				perDao.inserir(per);
			}
			
			}
	}

	private void excluirUsuario() {
		ConsumidorDAO dao = new ConsumidorDAO();
		int id = parseInt(showInputDialog("ID"));
		Consumidor consumidor = dao.pesquisar(id);
		if(consumidor == null) {
			showMessageDialog(null, "Usuário não encontrado");
		} else {
			dao.remover(id);
			showMessageDialog(null, "Usuário excluido com sucesso.");
		}
	}

	private void alterarUsuario() {
		ConsumidorDAO dao = new ConsumidorDAO();
		int id = parseInt(showInputDialog("ID"));
		Consumidor consumidor = dao.pesquisar(id);
		if(consumidor == null) {
			showMessageDialog(null, "Usuário não encontrado");
		} else {
			String nome = showInputDialog("Novo nome");
			String email = showInputDialog("Novo email");
			int telefone = parseInt(showInputDialog("Novo telefone"));
			int idade = parseInt(showInputDialog("Nova Idade"));
			String endereco = showInputDialog("Novo endereco");
			consumidor.setIdConsumidor(id);
			consumidor.setNome(nome);
			consumidor.setEmail(email);
			consumidor.setTelefone(telefone);
			consumidor.setIdade(idade);
			consumidor.setEndereco(endereco);
			dao.alterar(consumidor);
		}	
	}

	private void pesquisarUsuario() {
			ConsumidorDAO dao = new ConsumidorDAO();
			int id = parseInt(showInputDialog("ID"));
			Consumidor consumidor = dao.pesquisar(id);
			if(consumidor == null) {
				showMessageDialog(null, "Usuário não encontrado");
			} else {
				showMessageDialog(null, consumidor);
			}		
	}

	private void listarUsuario() {
			ConsumidorDAO dao = new ConsumidorDAO();
			List<Consumidor> lista = dao.listar();
			String aux = "";
			for(Consumidor e : lista) {
				aux += e + "\n";
			}
			showMessageDialog(null, aux);		
	}

	private void cadastrarUsuario() {
		ConsumidorDAO dao = new ConsumidorDAO();
		int id = parseInt(showInputDialog("ID"));
		if (dao.pesquisar(id) != null) {
			showMessageDialog(null, "Consumidor já existe.");
		} else {
		String nome = showInputDialog("Nome");
		int idade = parseInt(showInputDialog("Idade"));
		String email = showInputDialog("Email");
		int telefone = parseInt(showInputDialog("Telefone"));
		String endereco = showInputDialog("Endereço");
		Consumidor consumidor = new Consumidor(id, nome, email, telefone, idade, endereco);
		dao.inserir(consumidor);
		}
		
		
		
	}

	private void listarAcao() {
		AcaoEsgDAO dao = new AcaoEsgDAO();
		List<AcaoEsg> lista = dao.listar();
		String aux = "";
		for(AcaoEsg e : lista) {
			aux += e.getIdAcao() + e.getDescricao() + "\n";
		}
		showMessageDialog(null, aux);
	}

	private String gerarMenu() {
		String menu = "------DATAWAVE PRÉ-ALFA------\nEscolha uma opção:\n";
		menu += "1. Cadastrar Usuário Consumidor\n";
		menu += "2. Listar Usuários\n";
		menu += "3. Pesquisar Usuário\n";
		menu += "4. Alterar Usuário\n";
		menu += "5. Excluir Usuário\n";
		menu += "6. Cadastrar Feedback\n";
		menu += "7. Cadastrar Ações ESG da empresa\n";
		menu += "8. Listar Ações ESG da empresa\n";
		menu += "9. Pesquisar um Feedback\n";
		menu += "10. Excluir um Feedback\n";
		menu += "11. Finalizar\n";
		return menu;
	}
}

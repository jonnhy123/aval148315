package br.univel.jhonatan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelPessoa extends PainelPessoaBase{
	private PessoaModel modelo;

	public PainelPessoa() {
		iniciarTabela();
		configurarBotoes();
	}

	private void iniciarTabela() {
		PessoaDao dao = new PessoaDao();
		List<Pessoa> lista = dao.buscarTodos();
		this.modelo = new PessoaModel(lista);
		super.table.setModel(modelo);
	}

	private void configurarBotoes() {
		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnExcluirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirItem();
			}
		});
	}

	protected void excluirItem() {
		String strId = txtId.getText().trim();
		int intId = Integer.parseInt(strId);
		
		PessoaDao dao = new PessoaDao();
		dao.excluirItem(intId);
		iniciarTabela();
	}

	protected void salvar() {
		String strId = txtId.getText().trim();
		String strIdade = txtIdade.getText().trim();
		String strNome = txtNome.getText().trim();
		String strTelefone = txtTelefone.getText().trim();
		int intId = Integer.parseInt(strId);
		int intIdade = Integer.parseInt(strIdade);
		
		Pessoa p = new Pessoa();
		p.setId(intId);
		p.setIdade(intIdade);
		p.setNome(strNome);
		p.setTelefone(strTelefone);
		
		PessoaDao dao = new PessoaDao();
		dao.salvar(p);
		iniciarTabela();
	}

	protected void limparCampos() {
		txtId.setText("");
		txtIdade.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
	}
}

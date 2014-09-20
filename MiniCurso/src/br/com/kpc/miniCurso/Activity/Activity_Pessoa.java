package br.com.kpc.miniCurso.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.kpc.miniCurso.R;
import br.com.kpc.miniCurso.Dao.PessoaDao;
import br.com.kpc.miniCurso.Entity.Pessoa;
import br.com.kpc.miniCurso.Util.Util;

/**
 * 
 * @author Pcego
 * @since 1.0 Activity para cadastro de pessoas
 * 
 * 
 */
public class Activity_Pessoa extends Activity {

	private EditText nome;
	private EditText cpf;
	private EditText telefone;
	private Button btCadastrar;
	private Button btCancelar;
	private Button btSair;
	private Pessoa pessoa;
	private boolean b;
	private int codPessoa = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pessoa);

		nome = (EditText) findViewById(R.id.edtNome);
		cpf = (EditText) findViewById(R.id.edtCpf);
		telefone = (EditText) findViewById(R.id.edtTelefone);
		btCadastrar = (Button) findViewById(R.id.btnSalvarPessoa);
		btCancelar = (Button) findViewById(R.id.btnCancelarPessoa);
		btSair = (Button) findViewById(R.id.btnSairPessoa);

		// Exemplo de ação do botão Direto
		btSair.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			codPessoa = bundle.getInt("codPessoa");
			carregarDados(codPessoa);
		}

	}

	// Ação do botão Cancelar
	public void btCancelar(View v) {

		nome.setText(null);
		cpf.setText(null);
		telefone.setText(null);
	}

	// Ação do botão Salvar
	public void btSalvar(View v) {

		pessoa = new Pessoa();
		pessoa.setNome(nome.getText().toString());
		pessoa.setCpf(cpf.getText().toString());
		pessoa.setTelefone(telefone.getText().toString());

		if (codPessoa == 0) {
			b = PessoaDao.getPessoaDao(getApplicationContext()).insert(pessoa);
		} else {
			pessoa.setId(codPessoa);
			b = PessoaDao.getPessoaDao(getApplicationContext()).update(pessoa);
		}
		if (b) {

			Util.menssagemAviso("Confirmação", "Pessoa gravada com sucesso!",
					Activity_Pessoa.this);

			nome.setText(null);
			cpf.setText(null);
			telefone.setText(null);
		}

		else {
			Util.menssagemAviso("Falha", "Erro ao Salvar Pessoa",
					Activity_Pessoa.this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pessoa, menu);
		return true;
	}

	public void menssagemAviso(String titulo, String msg) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				getApplicationContext());
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	public void carregarDados(int codigo) {
		pessoa = new Pessoa();
		pessoa = PessoaDao.getPessoaDao(getApplicationContext())
				.selecionaPorId(codigo);

		nome.setText(pessoa.getNome());
		cpf.setText(pessoa.getCpf());
		telefone.setText(pessoa.getTelefone());
	}
}

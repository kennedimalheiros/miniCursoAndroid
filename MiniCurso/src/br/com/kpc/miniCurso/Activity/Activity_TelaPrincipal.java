package br.com.kpc.miniCurso.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnCreateContextMenuListener;
import br.com.kpc.miniCurso.R;

/**
 * 
 * @author Pcego
 * @since 1.0 Activity tela principal contem todos os menus de contesto para
 *        iteração com a aplicação
 *
 */
public class Activity_TelaPrincipal extends Activity implements
		OnCreateContextMenuListener {

	// Código para identificação de clique em Menu.

	public static final int MENUITEM_CAD_PESSOA = 1;
	public static final int MENUITEM_RELATORIO = 2;
	// Código para identificação de clique em Menu/Edit.
	public static final int CONTEXTMENUITEM_EDIT = 101;

	// Código para identificação de clique em Menu/Delete.
	public static final int CONTEXTMENUITEM_DELETE = 102;

	// Código para identificação de solicitação de operação
	// inclusão de um nova tarefa no banco de dados.
	public static final int REQUEST_NEW = 100;

	// Código para identificação de solicitação de operação
	// atualização de tarefas no banco de dados.
	public static final int REQUEST_UPDATE = 101;

	// Código de resposta a ser utilizado para quando houver
	// sucesso no processamento solicitado.
	public static final int RESPONSE_SUCCESS = 200;

	// Código de resposta a ser utilizado para quando houver
	// cancelamento no processamento solicitado.
	public static final int RESPONSE_CANCEL = 300;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tela_principal, menu);

		super.onCreateOptionsMenu(menu);

		MenuItem menu_cad_motorista = menu.add(0, MENUITEM_CAD_PESSOA, 0,
				R.string.menuitem_cad_pessoa);
		MenuItem menu_lista_pessoas = menu.add(1, MENUITEM_RELATORIO, 0,
				R.string.menuitem_relatorios);
		menu_cad_motorista.setIcon(android.R.drawable.ic_menu_agenda);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		// Pode haver vários itens de menus, assim, identifica-se
		// qual item menu foi clicado.

		if (item.getItemId() == MENUITEM_CAD_PESSOA) {

			// ... cria-se uma Intent para iniciar a activity que
			// contém o formulário de inserção e...
			Intent intent = new Intent(getApplicationContext(),
					Activity_Pessoa.class);

			// inicia-se a nova activity.
			startActivity(intent);

		} else if (item.getItemId() == MENUITEM_RELATORIO) {

			// ... cria-se uma Intent para iniciar a activity que
			// contém o formulário de inserção e...
			Intent intent = new Intent(getApplicationContext(),
					Activity_ListaPessoa.class);

			// inicia-se a nova activity.
			startActivity(intent);

		}

		return true;
	}
}

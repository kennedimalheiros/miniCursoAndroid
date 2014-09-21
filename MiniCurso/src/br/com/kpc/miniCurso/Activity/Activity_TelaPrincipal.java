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

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tela_principal, menu);

		super.onCreateOptionsMenu(menu);

		MenuItem menu_principal = menu.add(0, MENUITEM_CAD_PESSOA, 0,
				R.string.menuitem_cad_pessoa);
		MenuItem menu_list = menu.add(0, MENUITEM_RELATORIO, 0,
				R.string.menuitem_relatorios);

		menu_principal.setIcon(android.R.drawable.ic_menu_agenda);

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

			Intent intent = new Intent(getApplicationContext(),
					Activity_ListaPessoa.class);

			// inicia-se a nova activity.
			startActivity(intent);

		}

		return true;
	}
}

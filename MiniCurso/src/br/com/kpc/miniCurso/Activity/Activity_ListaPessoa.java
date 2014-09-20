package br.com.kpc.miniCurso.Activity;

import java.util.List;

import br.com.kpc.miniCurso.R;
import br.com.kpc.miniCurso.Adapter.Adapter_Pessoa;
import br.com.kpc.miniCurso.Dao.PessoaDao;
import br.com.kpc.miniCurso.Entity.Pessoa;
import br.com.kpc.miniCurso.R.id;
import br.com.kpc.miniCurso.R.layout;
import br.com.kpc.miniCurso.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
                                 //extends ListActivity  
public class Activity_ListaPessoa extends ListActivity {
	private List<Pessoa> pessoa;
	private ArrayAdapter<Pessoa> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Criando Adapter
		adapter = new Adapter_Pessoa(getApplicationContext(),
				R.layout.item_pessoas, PessoaDao.getPessoaDao(
						getApplicationContext()).selecionaTodos());

		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Pessoa item = (Pessoa) l.getItemAtPosition(position);

		Toast.makeText(getApplicationContext(), "Você selecionou " + item.getNome(),
				Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(getApplicationContext(), Activity_Pessoa.class);
		intent.putExtra("codPessoa", item.getId());
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__lista_pessoa, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

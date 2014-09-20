package br.com.kpc.miniCurso.Adapter;

import java.util.List;

import br.com.kpc.miniCurso.R;
import br.com.kpc.miniCurso.Entity.Pessoa;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapter_Pessoa extends ArrayAdapter<Pessoa> {
	Context context;
	int textViewResourceId;
	List<Pessoa> allToDos;

	public Adapter_Pessoa(Context context, int textViewResourceId,
			List<Pessoa> allToDos) {
		super(context, textViewResourceId, allToDos);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.allToDos = allToDos;
	}

	private static class Auxiliar {
		TextView cpf;
		TextView nome;
		TextView telefone;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// super.getView(position, convertView, parent);

		Auxiliar auxiliar = null;

		if (convertView == null) {
			LayoutInflater layout = LayoutInflater.from(context);
			convertView = layout.inflate(textViewResourceId, parent, false);

			auxiliar = new Auxiliar();

			auxiliar.cpf = (TextView) convertView.findViewById(R.id.tviCPF);

			auxiliar.nome = (TextView) convertView.findViewById(R.id.tviNome);
			auxiliar.telefone = (TextView) convertView
					.findViewById(R.id.tviTelefone);

			convertView.setTag(auxiliar);

		} else {
			auxiliar = (Auxiliar) convertView.getTag();
		}

		Pessoa pessoa = allToDos.get(position);
		auxiliar.cpf.setText(pessoa.getCpf());
		auxiliar.nome.setText(pessoa.getNome());
		auxiliar.telefone.setText(pessoa.getTelefone());
		return convertView;
	}

	@Override
	public int getCount() {
		return allToDos.size();
	}

	@Override
	public Pessoa getItem(int position) {
		return allToDos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}

package br.com.kpc.miniCurso.Dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.kpc.miniCurso.Entity.Pessoa;

/**
 * 
 * @author Paulo César
 * 
 */

public class PessoaDao extends DaoGenerico<Pessoa> {

	/**
	 * 
	 */
	public static Context contesto;

	/**
	 * 
	 * 
	 */
	public static PessoaDao pessoaDao;

	/**
	 * 
	 */
	public static String NOME_TABELA = "pessoas";

	/**
	 * 
	 * @author Paulo César
	 * 
	 */
	public static final class Coluna {
		public static String ID = "_id";
		public static String NOME = "nome";
		public static String CPF = "cpf";
		public static String TELEFONE = "telefone";

	}

	private PessoaDao(Context ctx) {
		super(contesto);
		contesto = ctx;

	}

	/**
	 * @author Paulo César
	 * @param ctx
	 * @return pessoaDao
	 */
	public static PessoaDao getPessoaDao(Context ctx) {
		contesto = ctx;
		if (pessoaDao == null) {

			pessoaDao = new PessoaDao(contesto);
		}
		return pessoaDao;
	}

	/**
	 * 
	 */
	@Override
	public List<Pessoa> selecionaTodos() {

		SQLiteDatabase db = getDb();

		Cursor c = null;

		try {

			String colunas[] = new String[] { Coluna.ID, Coluna.NOME,
					Coluna.CPF, Coluna.TELEFONE };

			// Execução da consulta.
			// O resultado é um cursor para iteração sobre o resultado.
			c = db.query(NOME_TABELA, colunas, null, null, null, null,
					Coluna.NOME);

			// Variável para armazenamento dos
			// resultados gerados pela consulta.
			List<Pessoa> todos = new ArrayList<Pessoa>();

			// Se existe um primeiro registro...
			if (c.moveToFirst()) {
				do {
					// ... cria-se uma classe que será populada pelos
					// dados retornados pela consulta
					Pessoa pessoa = new Pessoa();
					pessoa.setId(c.getInt(c.getColumnIndex(Coluna.ID)));
					pessoa.setNome((c.getString(c.getColumnIndex(Coluna.NOME))));
					pessoa.setCpf((c.getString(c.getColumnIndex(Coluna.CPF))));
					pessoa.setTelefone((c.getString(c
							.getColumnIndex(Coluna.TELEFONE))));

					// Adiciona-se a nova instância à lista geral.
					todos.add(pessoa);

					// Itera enquanto houver um próximo registro.
				} while (c.moveToNext());
			}

			// Devolve a lista com todos os resgistros encontrados.
			// Pode ser nulo, caso não haja resgistros armazenados.
			return todos;

		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Falha na leitura dos dados.", e);
			e.printStackTrace();
		} finally {
			// Libera recursos para o sistema.
			// startManagingCursor(Cursor) só funciona
			// para o ciclo de vida de uma Activity!
			if (c != null) {
				c.close();
			}
			db.close();
		}

		// Garante que haja um valor de retorno
		return null;

	}

	@Override
	public Pessoa selecionaPorId(int i) {

		SQLiteDatabase db = getDb();
		Cursor c = null;

		try {
			String colunas[] = new String[] { Coluna.ID, Coluna.NOME,
					Coluna.CPF, Coluna.TELEFONE };

			// Column.ID + " = ?" corresponde ao critério de consulta.
			// new String[] { String.valueOf(i) } corresponde ao(s)
			// valor(es) a ser(em) substituído(s) no critério de consulta.

			c = db.query(NOME_TABELA, colunas, Coluna.ID + " = ?",
					new String[] { String.valueOf(i) }, null, null, null);

			Pessoa pessoa = new Pessoa();

			if (c.moveToFirst()) {
				pessoa.setId(c.getInt(c.getColumnIndex(Coluna.ID)));
				pessoa.setNome((c.getString(c.getColumnIndex(Coluna.NOME))));
				pessoa.setCpf((c.getString(c.getColumnIndex(Coluna.CPF))));
				pessoa.setTelefone((c.getString(c
						.getColumnIndex(Coluna.TELEFONE))));

				return pessoa;
			}

		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Falha na leitura dos dados.", e);
			e.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}
			db.close();
		}

		return null;
	}

	@Override
	public boolean insert(Pessoa pessoa) {

		SQLiteDatabase db = getDb();

		try {

			// Variável que conterá os valores a serem armazenados.
			ContentValues valores = new ContentValues();

			// Preparação do par coluna/valor para inserção.
			// _id é autoincrementável, bastando que seja inserida
			// a descrição da nova tarefa.

			valores.put(Coluna.NOME, pessoa.getNome());
			valores.put(Coluna.CPF, pessoa.getCpf());
			valores.put(Coluna.TELEFONE, pessoa.getTelefone());

			// Inserção do(s) valor(es) na tabela específica.
			db.insert(NOME_TABELA, null, valores);

		} catch (Exception e) {
			Log.e("MotoristaDao", NOME_TABELA + ": falha ao inserir registro "
					+ pessoa.getNome(), e);
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	@Override
	public void delete(int i) {

		SQLiteDatabase db = getDb();

		try {
			// "_id = ?" corresponde ao critério da exclusão.
			// new String[] { String.valueOf(i) } corresponde ao(s)
			// valor(es) a ser(em) substituído(s) no critério de exclusão.
			db.delete(NOME_TABELA, "_id = ?",
					new String[] { String.valueOf(i) });
		} catch (Exception e) {
			Log.e("MotoristaDao", NOME_TABELA + ": falha ao excluir registro "
					+ i, e);
		} finally {
			db.close();
		}
	}

	@Override
	public boolean update(Pessoa pessoa) {

		// Processo semelhante ao método anterior
		SQLiteDatabase db = getDb();

		try {

			ContentValues values = new ContentValues();

			values.put(Coluna.NOME, pessoa.getNome());
			values.put(Coluna.CPF, pessoa.getCpf());
			values.put(Coluna.TELEFONE, pessoa.getTelefone());

			// "_id = ?" corresponde ao critério da atualização.
			// new String[] { String.valueOf(todo.getId()) } corresponde ao(s)
			// valor(es) a ser(em) substituído(s) no critério de atualização.
			db.update(NOME_TABELA, values, "_id = ?",
					new String[] { String.valueOf(pessoa.getId()) });

		} catch (Exception e) {
			Log.e("pessoaDao", NOME_TABELA + ": falha ao atualizar registro "
					+ pessoa.getId(), e);
			return false;
		} finally {
			db.close();
		}
		return true;
	}

}

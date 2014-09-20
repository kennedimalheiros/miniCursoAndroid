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
 * @author Paulo C�sar
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
	 * @author Paulo C�sar
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
	 * @author Paulo C�sar
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

			// Execu��o da consulta.
			// O resultado � um cursor para itera��o sobre o resultado.
			c = db.query(NOME_TABELA, colunas, null, null, null, null,
					Coluna.NOME);

			// Vari�vel para armazenamento dos
			// resultados gerados pela consulta.
			List<Pessoa> todos = new ArrayList<Pessoa>();

			// Se existe um primeiro registro...
			if (c.moveToFirst()) {
				do {
					// ... cria-se uma classe que ser� populada pelos
					// dados retornados pela consulta
					Pessoa pessoa = new Pessoa();
					pessoa.setId(c.getInt(c.getColumnIndex(Coluna.ID)));
					pessoa.setNome((c.getString(c.getColumnIndex(Coluna.NOME))));
					pessoa.setCpf((c.getString(c.getColumnIndex(Coluna.CPF))));
					pessoa.setTelefone((c.getString(c
							.getColumnIndex(Coluna.TELEFONE))));

					// Adiciona-se a nova inst�ncia � lista geral.
					todos.add(pessoa);

					// Itera enquanto houver um pr�ximo registro.
				} while (c.moveToNext());
			}

			// Devolve a lista com todos os resgistros encontrados.
			// Pode ser nulo, caso n�o haja resgistros armazenados.
			return todos;

		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Falha na leitura dos dados.", e);
			e.printStackTrace();
		} finally {
			// Libera recursos para o sistema.
			// startManagingCursor(Cursor) s� funciona
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

			// Column.ID + " = ?" corresponde ao crit�rio de consulta.
			// new String[] { String.valueOf(i) } corresponde ao(s)
			// valor(es) a ser(em) substitu�do(s) no crit�rio de consulta.

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

			// Vari�vel que conter� os valores a serem armazenados.
			ContentValues valores = new ContentValues();

			// Prepara��o do par coluna/valor para inser��o.
			// _id � autoincrement�vel, bastando que seja inserida
			// a descri��o da nova tarefa.

			valores.put(Coluna.NOME, pessoa.getNome());
			valores.put(Coluna.CPF, pessoa.getCpf());
			valores.put(Coluna.TELEFONE, pessoa.getTelefone());

			// Inser��o do(s) valor(es) na tabela espec�fica.
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
			// "_id = ?" corresponde ao crit�rio da exclus�o.
			// new String[] { String.valueOf(i) } corresponde ao(s)
			// valor(es) a ser(em) substitu�do(s) no crit�rio de exclus�o.
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

		// Processo semelhante ao m�todo anterior
		SQLiteDatabase db = getDb();

		try {

			ContentValues values = new ContentValues();

			values.put(Coluna.NOME, pessoa.getNome());
			values.put(Coluna.CPF, pessoa.getCpf());
			values.put(Coluna.TELEFONE, pessoa.getTelefone());

			// "_id = ?" corresponde ao crit�rio da atualiza��o.
			// new String[] { String.valueOf(todo.getId()) } corresponde ao(s)
			// valor(es) a ser(em) substitu�do(s) no crit�rio de atualiza��o.
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

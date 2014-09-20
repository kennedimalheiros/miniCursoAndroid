package br.com.kpc.miniCurso.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Paulo César
 * 
 */
public class DbHelper extends SQLiteOpenHelper {

	/**
	 * 
	 */
	public DbHelper(Context contesto, String nome, CursorFactory fabrica,
			int versao) {
		super(contesto, nome, fabrica, versao);
	}

	/**
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		try {

			String sql_pessoaDao = " CREATE TABLE "
					+ PessoaDao.NOME_TABELA + " ( " + PessoaDao.Coluna.ID
					+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
					+ PessoaDao.Coluna.NOME + " TEXT NOT NULL, "
					+ PessoaDao.Coluna.CPF + " TEXT NOT NULL, "
					+ PessoaDao.Coluna.TELEFONE + " TEXT NOT NULL);";
					
			db.execSQL(sql_pessoaDao);

		} catch (Exception e) {
			Log.e("DbHelper", "Erro na criação da tabela pessoas", e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}

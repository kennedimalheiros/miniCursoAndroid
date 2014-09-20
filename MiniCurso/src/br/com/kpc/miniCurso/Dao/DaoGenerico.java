package br.com.kpc.miniCurso.Dao;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Paulo César
 * @version 1.0
 * @since 1.0
 *  
 */

public abstract class DaoGenerico <T> {
	
	/*
	 * declaração variáveis bd
	 */
	private static String NOME_DB = "miniCurso";
	private int VERSAO_DB = 1;
	private Context contesto;
	private DbHelper dbHelper;
	
	/**
	 * @author Paulo César
	 * @since 1.0
	 * @version 1.0
	 * @return Retorna uma lista contendo todos os dados
	 * gravados no bd
	 */
	public abstract List<T> selecionaTodos();
	
	/**
	 * 
	 */
	public abstract T selecionaPorId(int i);
	
	/**
	 * 
	 */
	public abstract boolean insert(T o);
	
	/**
	 * 
	 */
	public abstract void delete(int i);
	
	/**
	 * 
	 */
	public abstract boolean update(T o);
	
	/**
	 * 
	 */
	protected DaoGenerico(Context contesto){
		this.contesto = contesto;
		dbHelper = new DbHelper(contesto, NOME_DB, null, VERSAO_DB);
		
	}
	
	/**
	 * 
	 */
	protected SQLiteDatabase getDb(){
		return dbHelper.getWritableDatabase();
	}
	

}

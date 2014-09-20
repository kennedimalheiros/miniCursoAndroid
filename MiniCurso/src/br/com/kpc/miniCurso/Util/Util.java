package br.com.kpc.miniCurso.Util;

import br.com.kpc.miniCurso.Activity.Activity_Pessoa;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class Util {
	
	/**
	 * m�todo para exibir avisos
	 * @author Pcego 
	 * @param titulo do aviso
	 * @param msg a ser exibida
	 * @param contesto da aplica��o
	 * @since 1.0
	 */

	public static void menssagemAviso(String titulo, String msg,
			Context contesto) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(contesto);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}
	
	/**
	 * m�todo para exibir alertas
	 * @author Pcego 
	 * @param titulo do aviso
	 * @param msg a ser exibida
	 * @param contesto da aplica��o
	 * @since 1.0
	 */

	public static void menssagemAlerta(String titulo, String msg,
			final Context contesto) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(contesto);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("Sair",new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
				Runnable r = new Runnable() {
					
					public void run() {
						
						Intent i = new Intent(contesto,Activity_Pessoa.class);
						contesto.startActivity(i);
						
					}
				};
			}
		
		});				
		mensagem.show();
	}
	
	/**
	 * M�todo respons�vel pela gera��o e tratamento de di�logos.
	 * 
	 * @param context Contexto da aplica��o. 
	 * @param title T�tulo do di�logo a ser gerado.
	 * @param message Mensagem de texto para intera��o com o usu�rio.
	 * @param positiveLabel Texto para resposta positiva.
	 * @param positiveListener Tratamento da resposta positiva.
	 * @param negativeLabel Texto para resposta negativa.
	 * @param negativeListener Tratamento da resposta negativa.
	 * 
	 * @return Di�logo (AlertDialog) parametrizado.
	 */
	public static AlertDialog createDialog(Context context, int title,
			int message, int positiveLabel, OnClickListener positiveListener,
			int negativeLabel, OnClickListener negativeListener) {

		Log.d("Utility", title + " - " + message);
		Log.d("Utility", title + " - " + positiveLabel);
		Log.d("Utility", positiveListener == null ? "posL nulo" : "posL OK");
		Log.d("Utility", title + " - " + negativeLabel);
		Log.d("Utility", negativeListener == null ? "negL nulo" : "negL OK");

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(positiveLabel, positiveListener);
		builder.setNegativeButton(negativeLabel, negativeListener);

		return builder.create();
	}
	

}

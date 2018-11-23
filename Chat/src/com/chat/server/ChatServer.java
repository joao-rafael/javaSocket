package com.chat.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe do server
 * 
 * de momento, essa classe recebe uma mensagem de um client e 
 * capitaliza, isto é, deixa todas as suas letras maiúsculas.
 * 
 * Deverá ser adaptada(obviamente) para obedecer os requisitos do
 * trabalho, conforme enunciado
 * 
 * O Nome da classe é mandatório conforme enunciado.
 * 
 * @author João Rafael e Arlei
 */
public class ChatServer {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while(true) {
			 Socket connectionSocket = welcomeSocket.accept();
	         BufferedReader inFromClient =
	            new BufferedReader(new
	                  InputStreamReader(connectionSocket.getInputStream()));
	         
	         DataOutputStream outToClient =
	            new DataOutputStream(connectionSocket.getOutputStream());
	         
	         clientSentence = inFromClient.readLine();
	         capitalizedSentence = clientSentence.toUpperCase() + '\n';
	         outToClient.writeBytes(capitalizedSentence);
	         connectionSocket.close();
	    }
	}

}
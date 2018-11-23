//declara��o do pacote
package com.chat.client;

/*
 * importa��es necess�rias
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/** 
 * Chat project
 * 
 * Classe do Client side
 * 
 * Conforme enunciado, as classes devem ter nome
 * de ChatServer e ChatClient
 *
 * @authors Jo�o Rafael and Arlei
 */
public class ChatClient {
	/*
	 * O seguinte bloco de vari�veis est�
	 * relacionado a interface gr�fica e n�o deve ser modificado.
	 * (conforme enunciado)
	 */
    JFrame frame = new JFrame("Chat Client");
    private JTextField chatBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    //---- fim do bloco de vari�veis da interface gr�fica

    /* Uma vez que haja necessidade, o c�digo de inicializa��o
     * do construtor dever� ser posto aqui.
     */

    
    public void printMessage(final String message) {
        chatArea.append(message);
    }

    
    /**
     * Construtor da classe
     * 
     * @param server
     * 			recebe o server
     * @param port
     * 			porta de conex�o
     * @throws IOException
     * 			
     */
    public ChatClient(String server, int port) throws IOException {

        // Inicialização da interface gráfica --- * NÃO MODIFICAR *
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chatBox);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setVisible(true);
        chatArea.setEditable(false);
        chatBox.setEditable(true);
        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    newMessage(chatBox.getText());
                } catch (IOException ex) {
                } finally {
                   chatBox.setText("");
                }
            }
        });
        /*
         * Fim da inicializa��o da interface gr�fica
         * se for necess�rio adicionar
         * inicializa��o do construtor,
         * deve ser posto aqui
         */

    }

    /**
     * M�todo que dever� ser invocado sempre que o usu�rio
     * digitar uma mensagem na caixa de entrada;
     * @param message
     * @throws IOException
     */
    public void newMessage(String message) throws IOException {
        //TODO: c�digo que deve enviar a mensagem recebida ao server
    }

    
    /**
     * M�todo principal do objeto, invocado pelo m�todo main
     * @throws IOException
     */
    public void run() throws IOException {
        //TODO: m�todo principal
    }
    

    /**
     * Instancia a classe(inicia)
     * 
     * *N�O MODIFICAR*
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient(args[0], Integer.parseInt(args[1]));
        client.run();
    }

}
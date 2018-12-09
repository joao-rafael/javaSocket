/*
 * importações necessárias
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
 * O cliente deve usar duas threads uma vez que a execução deve
 * continuar a funcionar(em espera ao server e 
 * utilização da interface gráfica ao mesmo tempo)
 * 
 * 
 * @authors João Rafael and Arlei
 */
public class ChatClient{
	/*
	 * O seguinte bloco de variáveis está
	 * relacionado a interface gráfica e não deve ser modificado.
	 * (conforme enunciado)
	 */
    JFrame frame = new JFrame("Chat Client");
    private JTextField chatBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    //---- fim do bloco de variáveis da interface gráfica

    /* Uma vez que haja necessidade, o código de inicialização
     * do construtor deverá ser posto aqui.
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
     * 			porta de conexão
     * @throws IOException
     * 			
     */
    public ChatClient(String server, int port) throws IOException {

        // InicializaÃ§Ã£o da interface grÃ¡fica --- * NÃƒO MODIFICAR *
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
         * Fim da inicialização da interface gráfica
         * se for necessário adicionar
         * inicialização do construtor,
         * deve ser posto aqui
         */

    }

    /**
     * Método que deverá ser invocado sempre que o usuário
     * digitar uma mensagem na caixa de entrada;
     * @param message
     * @throws IOException
     */
    public void newMessage(String message) throws IOException {
        //TODO: código que deve enviar a mensagem recebida ao server
    }

    
    /**
     * Método principal do objeto, invocado pelo método main
     * @throws IOException
     */
    public void run() throws IOException {
        //TODO: método principal
    }
    

    /**
     * Instancia a classe(inicia)
     * 
     * *NÃO MODIFICAR*
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient(args[0], Integer.parseInt(args[1]));
        client.run();
    }

}
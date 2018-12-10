/*
 * importações necessárias
 */
import java.io.*;
import java.net.*;
import java.nio.charset.*;
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
    //Declaração das variáveis globais
    // Decodificador para texto, UTF8
    static private final Charset charset = Charset.forName("UTF8");
    static private final CharsetDecoder decoder = charset.newDecoder();
	/*
	 * O seguinte bloco de variáveis está
	 * relacionado a interface gráfica e não deve ser modificado.
	 * (conforme enunciado)
	 */
    JFrame frame = new JFrame("Chat Client");
    private JTextField chatBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    //---- fim do bloco de variáveis da interface gráfica

    Socket clientSocket;
    /* Uma vez que haja necessidade, o código de inicialização
     * do construtor deverá ser posto aqui.
     */
    
    public void printMessage(final String message) {
        chatArea.append(message + "\n");
    }

    /**
     * Construtor da classe
     * 
     * @param server
     * 			recebe o server, "localhost" como de praxe, da linha de
     *          comandos.
     * @param port
     * 			recebe porta de conexão, como de praxe, da linha de
     *          comandos.
     * @throws IOException
     * 			TODO: inserir descrição dessa exceção
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
         * Fim da inicialização da interface gráfica
         * se for necessário adicionar
         * inicialização do construtor,
         * deve ser posto aqui
         */
        clientSocket = new Socket(server, port);
        String message;
        String receptedMessage;
    }

    /**
     * Método que deverá ser invocado sempre que o usuário
     * digitar uma mensagem na caixa de entrada;
     * @param message
     * @throws IOException
     */
    public void newMessage(String message) throws IOException {
        //sent message to the server
        System.out.println("Launched function to send to server");
        InputStream targetStream =
        new ByteArrayInputStream(message.getBytes(charset));
        BufferedReader newMessage =
        new BufferedReader(new InputStreamReader(targetStream));
        DataOutputStream outToServer =
        new DataOutputStream(this.clientSocket.getOutputStream());
        outToServer.writeBytes(message + "\n");
        System.out.println("Message " + message + " sent to server");
    }

    
    /**
     * Método principal do objeto, invocado pelo método main
     * @throws IOException
     */
    public void run() throws IOException {
        //TODO: método principal

        //cria stream de entrada associada ao socket
        //read
        InputStreamReader inputReader = 
        new InputStreamReader(this.clientSocket.getInputStream());
        BufferedReader inputFromServer =
        new BufferedReader(inputReader);

        String receptedMessage = inputFromServer.readLine();
        if(receptedMessage == null){
            clientSocket.close();
            System.out.println("Closing connection and shutting down");
            return;
        }else{
            System.out.println("Recepted message: " + receptedMessage);
        }

    }
    

    /**
     * Instancia a classe(inicia)
     * 
     * *NÃO MODIFICAR*
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        ChatClient client = new ChatClient(args[0], Integer.parseInt(args[1]));
        client.run();
    }

}
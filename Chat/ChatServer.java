import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

/** 
 * Server Class
 * 
 * O server deve suportar os seguintes comandos:
 * /nick
 * /join
 * /leave
 * /bye
 * @author João Rafael e Arlei
*/
public class ChatServer extends Thread{

	//lista de bufferedwriter para cada client
	static private ArrayList<BufferedWriter> clients;

  //buffer pre alocado para dados recebidos
  static private final ByteBuffer buffer = ByteBuffer.allocate( 16384 );

  // Decodificador para texto, UTF8
  static private final Charset charset = Charset.forName("UTF8");
  static private final CharsetDecoder decoder = charset.newDecoder();
  static public void main( String args[] ) throws Exception {
    //recebe a porta como argumento na linha de comando
    int port = Integer.parseInt(args[0]);
    try {

      //Ao invés de criar uma socket de server, cria um canal de sockets
      ServerSocketChannel ssc = ServerSocketChannel.open();
      //atribui-se a não bloqueio, então pode ser seleccionado
      ssc.configureBlocking( false );

      //Pega o socket conectado do canal e o liga a porta de escuta
      ServerSocket ss = ssc.socket();
      InetSocketAddress isa = new InetSocketAddress( port );
      ss.bind(isa);
      //cria um novo seletor
      Selector selector = Selector.open();
      // Register the ServerSocketChannel, so we can listen for incoming
      // connections
      ssc.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("Listening on port "+port );

      while (true) {
        // See if we've had any activity -- either an incoming connection,
        // or incoming data on an existing connection
        int num = selector.select();
        // If we don't have any activity, loop around and wait again
        if (num == 0) {
          continue;
        }

        // Get the keys corresponding to the activity that has been
        // detected, and process them one by one
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
          // Get a key representing one of bits of I/O activity
          SelectionKey key = it.next();

          // What kind of activity is it?
          if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
            SelectionKey.OP_ACCEPT) {
            // It's an incoming connection.  Register this socket with
            // the Selector so we can listen for input on it
            Socket s = ss.accept();
            System.out.println( "Estabelecida conexao com "+s );

            // Make sure to make it non-blocking, so we can use a selector
            // on it.
            SocketChannel sc = s.getChannel();
            sc.configureBlocking( false );

            // Register it with the selector, for reading
            sc.register( selector, SelectionKey.OP_READ );

          } else if ((key.readyOps() & SelectionKey.OP_READ) ==
            SelectionKey.OP_READ) {

            SocketChannel sc = null;

            try {
              // It's incoming data on a connection -- process it
              sc = (SocketChannel)key.channel();
              boolean ok = processInput(sc);

							/*
              * If the connection is dead, remove it from the selector
							* and close it
							*/
              if (!ok) {
                key.cancel();

                Socket s = null;
                try {
                  s = sc.socket();
                  System.out.println( "A fechar conexao com"+s );
                  s.close();
                } catch( IOException ie ) {
                  System.err.println( "Erro, fechando socket "+s+": "+ie );
                }
              }

            } catch( IOException ie ) {

              // On exception, remove this channel from the selector
              key.cancel();

              try {
                sc.close();
              } catch( IOException ie2 ) { System.out.println( ie2 ); }

              System.out.println( "Closed "+sc );
            }
          }
        }

        // We remove the selected keys, because we've dealt with them.
        keys.clear();
      }
    } catch( IOException ie ) {
      System.err.println( ie );
    }
  }


  // Just read the message from the socket and send it to stdout
  static private boolean processInput( SocketChannel sc ) throws IOException {
    // Read the message to the buffer
    buffer.clear();
    sc.read( buffer );
    buffer.flip();
    // If no data, close the connection
    if (buffer.limit()==0) {
      return false;
    }
    // Decode and print the message to stdout
		String message = decoder.decode(buffer).toString();
		
    System.out.print("recepted message: " + message);

    return true;
  }
}
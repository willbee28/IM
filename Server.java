import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Listener on the ServerSocket. This class runs as a Thread and is attached to 
// a ServerSocket. It receives connections on this socket and spins off a new 
// Connection Thread for each new Socket created.
public class Server implements Runnable {

	// The server socket accepting connections.
	private ServerSocket server_socket;
	// The central data base of the TIM server used by all connections.
	private Users user_database = new Users();

	// Constructor
	// Parameters: port - The port to listen on.
	// Throws IOEXception if the server socket cannot be opened.
	public Server(int port) throws IOException {
		server_socket = new ServerSocket(port);

	}

	// Main loop for the server. An infinite loop that accepts new connections and
	// spins a new Connection Thread for each one.
	public void run() {
		System.out.println("Server ready for connection...");
		Socket socket = null;
		Connection chat = null;
		try {
			while (true) {
				try {
					socket = server_socket.accept();
					System.out.println("Connecting...");
					chat = new Connection(socket, user_database);
					new Thread(chat).start();

				} catch (IOException e) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} finally {
			try {
				chat.close();
				socket.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}
}

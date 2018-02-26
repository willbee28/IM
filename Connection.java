import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

//Class that handles a connection from (and to) a user.
public class Connection implements Runnable {

	// List of commands to be searched for the applicable command.
	private static ArrayList<Command> commands = new ArrayList<Command>();
	// Convenient way of processing the InputStream from the client.
	private Scanner input;
	// Convenient way of outputting messages to the client.
	private PrintStream output;
	// Socket connected to the users client.
	private Socket socket;
	// The user for the client connection.
	private User user;
	// The central database of the server.
	private Users user_database;

	// Constructor for the Connection
	// Parameters: socket - The socket connected to the client.
	// user_database - The central server database
	// Throws IOException if the supplied socket isn't well formed.
	public Connection(Socket socket, Users user_database) {
		commands.add(new CreateCommand());
		commands.add(new LoginCommand());
		commands.add(new SendCommand());
		commands.add(new QuitCommand());
		this.socket = socket;
		this.user_database = user_database;
		try {
			input = new Scanner(socket.getInputStream());
			output = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// Set the user connected to this Connection.
	// Parameters: user - User to connect.
	public void setUser(User user) {
		this.user = user;
	}

	// Obtain the user record associated with this connection.
	// Returns: The user record or null if no user is connected.
	public User getUser() {
		return user;
	}

	// Obtain the input of the Connection. This is a Scanner from which we can read
	// user data.
	// Returns: The Scanner attached to the Socket.
	public Scanner input() {
		return input;
	}

	// Obtain the output of the Connection. This is a PrintStream in order to allow
	// the code to use println and formatting methods.
	// Returns: The PrintStream connected to the client.
	public PrintStream output() {
		return output;
	}

	// Gracefully close this connection.
	public void close() {
		user = null;
		user.connect(null);
	}

	// The main evaluate loop for the client-server connection. Reads in a line of
	// text, strips the first word, then compares it with all of the programmed
	// commands performing the one that matches. It outputs a message if no command
	// matches.
	public void run() {
		boolean quit = false;
		while (!quit) {
			boolean found = false;
			while (!found && input.hasNext()) {
				String cmd = input.next();
				for (Command command : commands) {
					if (command.matches(cmd)) {
						found = true;
						try {
							command.perform(this, user_database);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;

					}
				}
				if (found == false) {
					output().println("No such command \""+ cmd +".\"");
					input.next();
				}
			}
		}
	}
}

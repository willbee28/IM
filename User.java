import java.util.ArrayList;

//Embodies the concept of a user.
public class User {

	// The connection the user is using.
	private Connection connection;
	// A list of the messages sent to the user while they were logged out.
	private ArrayList<Message> messages = new ArrayList<Message>();
	// Password of the user represented
	private String password;
	// Flag indicating that the user is a superuser.
	private boolean super_user;
	// Name of the user represented
	private String user_name;

	// Constructor
	public User(String user_name, String password, boolean super_user) {
		this.user_name = user_name;
		this.password = password;
		this.super_user = super_user;
	}

	// Check that the supplied password is the password for the user.
	public boolean authenticate(String password) {
		if (this.password.equals(password)) {
			connect(connection);
			return true;
		} else {
			return false;
		}
	}

	// Clear the list of saved messages (when they have been sent to the user on
	// login).
	public void clearMessages() {
		messages.clear();
	}

	// Record the current Connection for this user on authentication.
	// Parameters: connection - The new connection for the user.
	public void connect(Connection connection) {
		this.connection = connection;
	}

	// Determine if the user is connected.
	public boolean connected() {
		if (connection == null) {
			return false;
		} else {
			return true;
		}
	}

	// Disconnect the user from the current connection.
	public void disconnect() {
		connection.setUser(null);
		connection = null;
	}

	// Calculate a hash code depending only on the user_name
	public int hashCode() {
		return user_name.hashCode();
	}

	// Obtain the saved messages for this user.
	public ArrayList<Message> messages() {
		return messages;
	}

	// Send a message to the user. If the user is connected send the message out
	// along the appropriate connection, otherwise save the message in the list of
	// recorded messages.
	// Parameters: message - The message to send.
	public void send(Message message) {
		if (connected()) {
			connection.output().println("Message from " + message.from().user_name + " follows: " + message.message());
		} else {
			messages.add(message);
		}
	}

	// Obtain the user name.
	public String username() {
		return user_name;
	}

}

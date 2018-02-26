// The command to send a message to another user.
public class SendCommand extends Command {

	// Constructor
	public SendCommand() {

	}

	// Is the command requested the command required?
	// Parameters: keyword - the command keyword
	// Returns: the command required matches this one
	public boolean matches(String keyword) {
		if (keyword.equals("SEND")) {
			return true;
		} else {
			return false;
		}
	}

	// Take the action for the command requested.
	// Parameters: connection - The connection of which the command came.
	// user_database - The central database of the server
	public void perform(Connection connection, Users user_database) {
		String to = connection.input().next();
		String text = connection.input().nextLine();
		User receiver = user_database.getUser(to);
		User sender = connection.getUser();
		if (sender == null) {
			connection.output().println("Not connected as a user.");
		} else if (receiver == null) {
			connection.output().println("User " + to +" does not exist.");
		} else {
			Message message = new Message(connection.getUser(), text);
			connection.output().println("Message sent.");
			receiver.send(message);
		}
	}
}

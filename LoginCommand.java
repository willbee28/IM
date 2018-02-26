//The command to login a user connection.
public class LoginCommand extends Command {

	// Constructor
	public LoginCommand() {

	}

	// Is the command requested the command required?
	// Parameters: keyword - the command keyword
	// Returns: the command required matches this one
	public boolean matches(String keyword) {
		if (keyword.equals("AUTH")) {
			return true;
		} else {
			return false;
		}
	}

	// Take the action for the command requested.
	// Parameters: connection - The connection of which the command came.
	// user_database - The central database of the server
	public void perform(Connection connection, Users user_database) {
		String txt = connection.input().nextLine();
		String[] arr = txt.split(" ");
		String user_name = arr[1];
		String password = arr[2];
		User x = user_database.getUser(user_name);

		if (arr.length != 3) {
			connection.output().println("Too few/many words. Try again.");
		} else {
			if (x == null) {
				connection.output().println("User " + user_name + " does not exist.");
			} else if (connection.getUser() != null) {
				if (connection.getUser().connected()) {
					connection.output().println("Already connected as " + connection.getUser().username());
				}
			} else {
				if (x.connected() == true) {
					connection.output().println(user_name + " already connected.");
				} else {
					if (x.authenticate(password) == true) {
						x.connect(connection);
						connection.setUser(x);
						connection.output().println("Connected as " + user_name);
						for (Message message : x.messages()) {
							x.send(message);
						}
					} else {
						connection.output().println("Invalid username or password.");
					}
				}
			}
		}
	}
}

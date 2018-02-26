//The command to create a new user. 
public class CreateCommand extends Command {

	// Constructor
	public CreateCommand() {

	}

	// Is the command requested the command required?
	// Parameters: keyword - the command keyword
	// Returns: the command required matches this one
	public boolean matches(String keyword) {
		if (keyword.equals("CRTE")) {
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

		if (arr.length != 3) {
			connection.output().println("Too few/many words. Try again.");
		} else {
			if (user_database.getUser(user_name) != null) {
				connection.output().println("User " + user_name + " already exists.");
			} else {
				if (user_database.isEmpty()) {
					user_database.users.put(user_name, new User(user_name, password, true));
					connection.output().println("User " + user_name + " created as a superuser.");
				} else {
					user_database.users.put(user_name, new User(user_name, password, false));
					connection.output().println("User " + user_name + " created.");
				}
			}

		}
	}

}

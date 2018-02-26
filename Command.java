// The embodiment of a command for the IM server. The server creates a singleton
// object of each of the subclasses. The server then runs through all of these
// commands determining if there's a match. If there is a match the perform()
// method is called to perform the command
public abstract class Command {

	// Constructor
	public Command() {

	}

	// Is the command requested the command required?
	// Parameters: keyword - the command keyword
	// Returns: the command required matches this one
	public abstract boolean matches(String keyword);

	// Take the action for the command requested.
	// Parameters: connection - The connection of which the command came.
	// user_database - The central database of the server
	public abstract void perform(Connection connection, Users user_database);

}

import java.util.HashMap;
import java.util.Map;

// A central database of all the users on the server.
public class Users {

	// The database of User records indexed by user_name.
	Map<String, User> users = new HashMap<String, User>();

	// Constructor
	public Users() {

	}

	// Get the record of a user
	public User getUser(String user_name) {
		if (users.get(user_name)==null) {
			return null;
		}
		else{
			return users.get(user_name);
		}
	}

	// Determine if there are no users in the database.
	public boolean isEmpty() {
		if (users.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}

	// Create a record for a new user.
	public void NewUser(String user_name, String password, boolean super_user) {
		User mans = new User(user_name, password, super_user);
		users.put(user_name, mans);
	}

}

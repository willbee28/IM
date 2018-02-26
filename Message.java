// Class embodying a message.
public class Message {

	// The user sending the message;
	private User from;
	// The message sent by the user.
	private String message;

	// Constructor
	// Parameters: from - The sending user.
	// message - The message to be sent.
	public Message(User from, String message) {
		this.from = from;
		this.message = message;
	}

	// Obtain the user sending the message.
	// Returns the user sending the message
	public User from() {
		return from;
	}

	public String message() {
		return message;
	}

}

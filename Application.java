import java.io.IOException;

// The program class for the server
public class Application extends Thread{
	
	//The main function of the server. Starts a new Server that listens on port 7777 and attaches it to a Thread.
	public static void main(String [] args) throws IOException{
		
		new Thread(new Server(7777)).start();
		
	}
}

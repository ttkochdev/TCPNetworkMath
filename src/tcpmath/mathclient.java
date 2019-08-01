package tcpmath;

/**
 *
 * @author ttkoch
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mathclient {
	public static void main(String[] args) {
		System.out.println("What is the IP you want to connect to?");
		Scanner ip = new Scanner(System.in);
		String serverName = ip.next();
		// int x = Integer.parseInt(args[0]); //read from command line arguments
		// int y = Integer.parseInt(args[1]);
		int port = 50000;
		try {

			System.out.println("Connecting to " + serverName + " on port "
					+ port);
                try (Socket client = new Socket(serverName, port)) {
                    System.out.println("Just connected to "
                                    + client.getRemoteSocketAddress());
                    System.out.println("Please enter a command: (ex. 'add 1 2' or 'disconnect')");

                    BufferedReader bR = new BufferedReader(new InputStreamReader(
                                    client.getInputStream()));
                    PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                    Scanner sc = new Scanner(System.in);
                    String input;
                    while (true) {
                            input = sc.nextLine(); // reads next line input
                            pw.println(input); // sends to server input			
                            if (input.equals("disconnect")) {
                                    break;
                            }
                            System.out.println(bR.readLine()); // reads lines from server pw
                            System.out.println("Please enter a command: ");
                    }
                }
		} catch (IOException e) {
		}
	}
}
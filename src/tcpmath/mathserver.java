package tcpmath;

/**
 *
 * @author ttkoch
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class mathserver {

	/*
	 * public mathserver(int port) throws IOException { serverSocket = new
	 * ServerSocket(port); serverSocket.setSoTimeout(90000); }
	 */

	static void process(Socket sock) throws IOException {
		boolean debug; // for debugging
                debug = false;

		while (true) {
			try {
				// System.out.println("Waiting for client on port " +
				// serverSocket.getLocalPort() + "...");
				// Socket server = serverSocket.accept();

				InputStream in = sock.getInputStream();
				BufferedReader bR = new BufferedReader(
						new InputStreamReader(in));
				OutputStream out = sock.getOutputStream();
				PrintWriter pw = new PrintWriter(out, true); // print to client
				// Scanner sc = new Scanner(System.in);
				// String scInput;

				String input = bR.readLine();
				Scanner s = new Scanner(input);
				String name = s.next();
				int x = s.nextInt();
				int y = 0;
				boolean yParam;
                                yParam = false;
				if (s.hasNextInt()) {
					y = s.nextInt();
					yParam = false;
				} else {
					yParam = true;
				}

				int total;

				while (input != null && !input.equals("disconnect")) {

					if (yParam) {
                                        switch (name) {
                                            case "power":
                                                total = x * x;
                                                pw.println(total);
                                                break;
                                            case "cube":
                                                total = x * x * x;
                                                pw.println(total);
                                                break;
                                        }
					} else {
                                        switch (name) {
                                            case "add":
                                                total = x + y;
                                                pw.println(total);
                                                break;
                                            case "subtract":
                                                total = x - y;
                                                pw.println(total);
                                                break;
                                            case "multiply":
                                                total = x * y;
                                                pw.println(total);
                                                break;
                                            case "divide":
                                                total = x / y;
                                                pw.println(total);
                                                break;
                                            case "remainder":
                                                total = x % y;
                                                pw.println(total);
                                                break;
                                            default:
                                                pw.println("Please try entering the information again.");
                                                break;
                                        }
					}// yParam else

					// System.out.println(total);

					input = bR.readLine(); // reads input from client pw
					s = new Scanner(input);
					name = s.next();
					x = s.nextInt();
					if (s.hasNextInt()) {
						y = s.nextInt();
						yParam = false;
					} else {
						yParam = true;
					}

				}
				sock.close();
				/*
				 * System.out.println("Just connected to " +
				 * server.getRemoteSocketAddress()); DataInputStream in = new
				 * DataInputStream(server.getInputStream());
				 * System.out.println(in.readUTF());
				 * 
				 * DataOutputStream out = new
				 * DataOutputStream(server.getOutputStream());
				 * out.writeUTF("Thank you for connecting to " +
				 * server.getLocalSocketAddress() + "\nGoodbye!");
				 * server.close();
				 */
			}/*
			 * catch(SocketTimeoutException s) {
			 * System.out.println("Socket timed out!"); break; }
			 */catch (IOException e) {
				break;
			}
		}
	}

	public static void main(String[] args) {

		try {

			ServerSocket serverSocket = new ServerSocket(50000);

			while (true) {
				Socket clientsock = serverSocket.accept();
				process(clientsock);
			}
			/* start a thread 
			 * Thread t = new mathserver(port); t.start();
			 */
		} catch (IOException e) {
		}
	}
}
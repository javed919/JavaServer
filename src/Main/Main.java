package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main 
{
	private static final int portnumber = 61000;
	
	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		
		try
		{
			System.out.println("Server starting at port number: " + portnumber);
			serverSocket = new ServerSocket(portnumber);
			
			//Client Connecting
			System.out.println("Waiting for clients to connect.");
			Socket socket = serverSocket.accept();
			System.out.println("Connected to client");
			//Send message to the client
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write("This is a message from server.");
			bw.newLine();
			bw.flush();
			
			//Receive message from client
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data;
			
						
			while ((data = br.readLine())!=null)
			{
				System.out.println("Message from the client: " + data);
				//Saving data to a file
				
				try(FileWriter fw = new FileWriter("Datafile.txt", true);
						BufferedWriter bw1= new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw1))
				{
					out.println(data);
				}
				catch (IOException e)
				{
					
				}
				
//				PrintWriter writedata = new PrintWriter("datafile.txt");
//				writedata.println(data);
//				writedata.close();
			}
			
			System.out.println("Server has closed");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

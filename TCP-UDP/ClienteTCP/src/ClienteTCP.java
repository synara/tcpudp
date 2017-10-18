import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClienteTCP {
	public static void main(String args[]) {
		Socket socketCliente = null;
		int requisicao = 0;
		Scanner resposta = new Scanner(System.in);
		
		try {
			// Cria o socket
			socketCliente = new Socket("localhost", 7896);
			// Vincula os streams de enrada e saida
			DataInputStream entrada = new DataInputStream(socketCliente.getInputStream());
			DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());
			// Escreve no stream de saida
			//saida.writeUTF("Msg para o servidor");
			// Recebe no stream de entrada
			//String data = entrada.readUTF();
			//System.out.println("Recebeu do servidor: " + data);
			
			System.out.println("Deseja solicitar ou devolver uma bicicleta?\nDigite 1 para solicitar ou 2 para devolver");
			requisicao = resposta.nextInt();
			
			
			saida.writeInt(requisicao);
			saida.writeInt(1);
			saida.writeInt(1);
		
			String respostaServidor = entrada.readUTF();
			
		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (socketCliente != null)
				try {
					socketCliente.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}
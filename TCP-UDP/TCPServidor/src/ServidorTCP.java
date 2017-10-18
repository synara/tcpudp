import java.net.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.*;

public class ServidorTCP {
	public static void main(String args[]) {

		try {
			// Cria o socket do servidor
			ServerSocket socketServidor = new ServerSocket(7896);
			while (true) {
				System.out.println("Aguardando cliente...");
				// Recebe solicita��o de socket do cliente
				Socket socketCliente = socketServidor.accept();
				// Estabelece conex�o instanciando a thread
				Connection c = new Connection(socketCliente);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream entrada;
	DataOutputStream saida;
	Socket socketCliente;
	ArrayList<DadosCliente> dadosCliente;

	public Connection(Socket aSocketCliente) {
		try {
			socketCliente = aSocketCliente;
			// Vincula streams de entrada e sa�da ao socket
			entrada = new DataInputStream(socketCliente.getInputStream());
			saida = new DataOutputStream(socketCliente.getOutputStream());
			// Inicia thread
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try {
			int requisicao = entrada.readInt();
			int idEstacao = entrada.readInt();
			int idPessoa = entrada.readInt();
			GregorianCalendar hora = new GregorianCalendar();
			
			DadosCliente dados = new DadosCliente(idPessoa, idEstacao, requisicao, hora);			
			armazenar(dados);
			
			if(requisicao == 1) {
				saida.writeUTF("Solicita��o recebida.");
			}
			else {
				saida.writeUTF("Devolu��o cadastrada.");
			}
			
			saida.writeUTF("Servidor: \n");
			System.out.println("Recebeu: \nN�mero requisi��o: " + requisicao + "\nId Esta��o: " + idEstacao + "\nId Pessoa: " + idPessoa);
			System.out.println("Saida " + saida.size());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				socketCliente.close();
			} catch (IOException e) {
				/* erro ao fechar */
			}
		}
	}
	
	public void armazenar(DadosCliente d) {
		dadosCliente = new ArrayList<DadosCliente>();
		int ultimo; 
		DadosCliente cliente;
		long registrada = 0;
		long atual = 0;
		
		if(d.getRequisicao() != 1 && d != null) {
			ultimo = dadosCliente.indexOf(dadosCliente.size());			
			cliente = dadosCliente.get(ultimo);	
			
			if(cliente != null) {
				 registrada = (cliente.getHora().getTimeInMillis())/60; 
				 GregorianCalendar horaAtual = new GregorianCalendar();
				 atual = (horaAtual.getTimeInMillis())/60;
			}
			
			else if((registrada - atual) > 30) {
				System.out.println("Pagar multa!!");
			}
			
		}
		
		dadosCliente.add(d);
	}

	
}




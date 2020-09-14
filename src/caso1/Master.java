package caso1;

import java.util.ArrayList;

public class Master {
	
	public static void main(String[] args) {
		int cantidadClientes = 5000;
		int threadsServer = 8;
		int tamanhoBuffer = 200;
		int mensajesPorCliente = 7;
		Buffer buffer = new Buffer(tamanhoBuffer, cantidadClientes);
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for (int i = 0; i < cantidadClientes; i++) {
			clientes.add(new Cliente(mensajesPorCliente*(i+1), buffer));
		}
		
		Servidor server = new Servidor(threadsServer, buffer);
		server.start();
		
		for (int i = 0; i < clientes.size(); i++) {
			clientes.get(i).start();
		}
	}

}

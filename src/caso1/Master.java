package caso1;

import java.util.ArrayList;

public class Master {
	
	public static void main(String[] args) {
		int cantidadClientes = 3;
		int threadsServer = 1;
		int tamanhoBuffer = 4;
		int mensajesPorCliente = 5;
		Buffer buffer = new Buffer(tamanhoBuffer);
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

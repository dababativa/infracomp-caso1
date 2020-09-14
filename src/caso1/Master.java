package caso1;

import java.io.*;
import java.util.ArrayList;
/**
 * Clase principal. Aca se crean los diferentes elementos principales del sistema (Buffer, Clientes, Mensajes, Servidor)
 */
public class Master {
	
	public static void main(String[] args) {

		try{

			/*
				Creacion del BufferedReader para leer los datos del archivo
			 */
			BufferedReader bf = new BufferedReader(new FileReader(new File("./src/caso1/datos.txt")));

			/* 
				Creacion de los parametros que se usaran para el funcionamiento:
				-cantidadClientes: representa la cantidad de clientes que se crean.
				-threadsServer: representa la cantidad de Threads que el servidor va a crear
				-tamanhoBuffer: representa el tamanho del buffer, es decir, la cantidad total de mensajes que puede almacenar
				-mensajesPorCliente: representa la base mediante la cual se calcula la cantidad de mensajes que 
									 un cliente puede mandar. El numero de mensajes que el cliente puede mandar
									 es igual a el parametro mensajesPorCliente multiplicado por el numero de 
									 creacion del cliente. Por ejemplo, si mensajesPorCliente es igual a 5 y 
									 se van a crear 5 clientes, el primero puede mandar 5 mensajes, el segundo 
									 puede mandar 10, el tercero puede mandar 15, el cuarto 20 y el ultimo 25.
			 */
			int cantidadClientes = Integer.parseInt(bf.readLine());
			int threadsServer = Integer.parseInt(bf.readLine());
			int tamanhoBuffer = Integer.parseInt(bf.readLine());
			int mensajesPorCliente = Integer.parseInt(bf.readLine());

			//Crea el Buffer
			Buffer buffer = new Buffer(tamanhoBuffer, cantidadClientes);
			
			//Crea los Clientes
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			for (int i = 0; i < cantidadClientes; i++) {
				clientes.add(new Cliente(mensajesPorCliente*(i+1), buffer));
			}
			
			//Crea e inicializa el Servidor
			Servidor server = new Servidor(threadsServer, buffer);
			server.start();
			
			//Inicializa los clientes
			for (int i = 0; i < clientes.size(); i++) {
				clientes.get(i).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

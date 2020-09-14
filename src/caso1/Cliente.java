package caso1;

import java.util.ArrayList;

/**
 * Representa los Clientes
 */
public class Cliente extends Thread {

	//-------------------------------------
	// ATRIBUTOS
	//-------------------------------------

	/**
	 * Representa la lista de mensajes que el cliente tiene que mandar
	 */
	private ArrayList<Mensaje> mensajes;

	/**
	 * Representa el Buffer por el que tienen que pasar los mensajes
	 */
	private Buffer buffer;

	/**
	 * Representa el id del Cliente. En este caso el id es la cantidad de mensajes totales que el cliente tiene que mandar
	 */
	private int id;
	
	//-------------------------------------
	// CONSTRUCTOR
	//-------------------------------------

	/**
	 * Metodo constructor. Crea un cliente usando los valores que entran como parametro.
	 * @param cantidadMensajes la cantidad de mensajes totales que el cliente tiene que mandar.
	 * @param pBuffer el buffer mediante el cual se mandan los mensajes
	 */
	public Cliente(int cantidadMensajes, Buffer pBuffer) {
		mensajes = new ArrayList<Mensaje>();
		id = cantidadMensajes;
		buffer = pBuffer;
		for (int i = 0; i < cantidadMensajes; i++) {
			mensajes.add(new Mensaje(i,this));
		}
	}
	
	//-------------------------------------
	// METODOS
	//-------------------------------------

	/**
	 * Metodo run propio de los Threads. Hace que el Cliente mande mensajes mientras su lista de mensajes no este vacia
	 */
	public void run() {
		while(!mensajes.isEmpty()) {
			Mensaje mensajeActual = mensajes.remove(0);
			System.out.println("ENVIAR " + mensajeActual.identificar());

			//Envia el mensaje a Buffer y posteriormente activa el metodo enviarMensaje() para detenerse hasta que este sea respondido.
			buffer.enviarMensaje(mensajeActual);
			mensajeActual.enviarMensaje();
		}
		//Imprime en consola que el cliente actual termino de enviar sus mensajes y luego le avisa al buffer eso.
		System.out.println(" CLIENTE: " + id + " TERMINA");
		buffer.quite();
	}

	/**
	 * Retorna el id del cliente. En este caso el id del cliente es la cantidad total de mensajes que el cliente debe mandar
	 * @return el id del cliente.
	 */
	public int darId() {
		 return id;
	}
}

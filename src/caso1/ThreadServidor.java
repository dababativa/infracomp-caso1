package caso1;

/**
 * Clase que representa los Threads que crea el servidor
 */
public class ThreadServidor extends Thread{
	//-------------------------------------
	// ATRIBUTOS
	//-------------------------------------

	/**
	 * Representa el Buffer
	 */
	private Buffer buffer;

	/**
	 * Representa el id del thread. En este caso es el orden de creacion
	 */
	private int id;

	//-------------------------------------
	// CONSTRUCTOR
	//-------------------------------------

	/**
	 * Metodo constructor. Crea un Thread del servidor de acuerdo a los valores que entran como parametro
	 * @param buffer el buffer del cual se sacan los mensajes
	 * @param pId el id del thread
	 */
	public ThreadServidor(Buffer buffer, int pId) {
		this.buffer = buffer;
		id = pId;
	}

	//-------------------------------------
	// METODOS
	//-------------------------------------

	/**
	 * Metodo run propio de los thread. Mientras haya clientes que no hayan terminado de mandar sus mensajes, el thread toma un mensaje del buffer y luego lo responde
	 */
	public void run() {
		while(buffer.hayClientes()) {
			Mensaje mensaje = buffer.retirarMensaje();

			//Pregunta si el mensaje es null, lo que indica que el Buffer esta vacio. En caso de estar vacio, libera el procesador y pasa al estdo listo.
			if(mensaje==null) {
				System.out.println("NO HAY MENSAJES");
				yield();
			} else {

				//En caso de no estar vacio, indica en consola que se esta respondiendo el mensaje y se activa el metodo responder mensaje para liberar al cliente y este pueda mandar su siguiente mensaje.
				System.out.println("RESPONDER: " + id + "->" + mensaje.identificar());
				mensaje.responderMensaje();
			}
		
		}
		System.out.println("THREAD " + id + "TERMINA");
	}
}

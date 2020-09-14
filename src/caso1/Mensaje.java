package caso1;

/**
 * Representa los mensajes 
 */
public class Mensaje {
	//-------------------------------------
	// ATRIBUTOS
	//-------------------------------------

	/**
	 * representa el contenido del mensaje, en este caso es simplemente el numero del mensaje en la lista del cliente.
	 */
	private int contenido;

	/**
	 * Es el cliente que creo este mensaje
	 */
	private Cliente remitente;
	
	//-------------------------------------
	// CONSTRUCTOR
	//-------------------------------------

	/**
	 * Metodo constructor. Crea un Mensaje usando los valores que ingresan por parametro.
	 * @param contenido el contenido del mensaje
	 * @param remitente el cliente que envia el mensaje
	 */
	public Mensaje(int contenido, Cliente remitente) {
		this.contenido = contenido;
		this.remitente = remitente;
	}
	
	//-------------------------------------
	// METODOS
	//-------------------------------------

	/**
	 * retorna el id del cliente que envia el mensaje
	 * @return
	 */
	public int darRemitente(){
		return remitente.darId();
	}

	/**
	 * evita que el cliente mande mas mensajes mientras espera a que le respondan uno. 
	 */
	public synchronized void enviarMensaje() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna el identificador del mensaje. Este es definido como idRemitente.idMensaje
	 * @return el identificador del mensaje
	 */
	public String identificar(){
		return remitente.darId() + "." + contenido;
	}

	/**
	 * multiplica el contenido en -1 para representar qua ya fue respondido y desbloquea al cliente para que mande el siguiente mensaje
	 */
	public synchronized void responderMensaje() {
		contenido = -contenido;
		notify();
	}
}

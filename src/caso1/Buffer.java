package caso1;

import java.util.ArrayList;

/**
 * Representa el Buffer del sistema
 */
public class Buffer {
	//-------------------------------------
	// ATRIBUTOS
	//-------------------------------------

	/**
	 * Representa la cantidad de espacios libres que tiene el Buffer
	 */
	private int recursosLibres;

	/**
	 * Es un arreglo que contiene todos los mensajes actualmente dentro del Buffer
	 */
	private ArrayList<Mensaje> mensajes;

	/**
	 * Representa la cantidad de clientes que pueden mandar mensajes 
	 */
	private int clientes;

	//-------------------------------------
	// CONSTRUCTOR
	//-------------------------------------

	/**
	 * Metodo constructor. Construye un Buffer usando los valores que entran como parametro.
	 * @param tamanho es el tamanho del Buffer, es decir, la cantidad maxima de mensajes que puede llegar a tener
	 * @param pClientes es la cantidad total de clientes creados originalmente.
	 */
	public Buffer(int tamanho, int pClientes) {
		recursosLibres = tamanho;
		clientes = pClientes;
		mensajes = new ArrayList<Mensaje>();
	}

	//-------------------------------------
	// METODOS
	//-------------------------------------

	/**
	 * Verifica si aun hay clientes que pueden mandar mensajes
	 * @return true si aun hay clientes que pueden mandar mensajes, false de lo contrario
	 */
	public boolean hayClientes(){
		return clientes>0;
	}

	/**
	 * Reduce la cantidad de clientes que pueden mandar mensajes en 1
	 */
	public void quite(){
		clientes--;
	}

	/**
	 * Ingreso de un mensaje al Buffer
	 * @param mensaje el mensaje que va a ingresar al Buffer
	 */
	public synchronized void enviarMensaje(Mensaje mensaje) {

		//Verifica que haya espacio en el Buffer
		if(recursosLibres<1) {
			try {

				//En caso de que no haya espacio en el Buffer se detiene, para no superarel tamaño del Buffer
				System.out.println("BUFFER LLENO");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//En caso de que haya espacio en el buffer, reduce en uno la cantidad de recursos libres y anhade el mensaje a la lista
		recursosLibres--;
		mensajes.add(mensaje);

		//Imprime en consola que se anhadio un mensaje, seguido de su identificador, y luego imprime la cantidad de mensajes que hay actualmente (indicando que se acaba de anhadir un mensaje)
		System.out.println("ANHADIR: " + mensaje.identificar());
		mensajesActuales("(SE ACABA DE ANHADIR UNO))");
	}

	/**
	 * Imprime en consola el estado actual del Buffer (la cantidad de espacios ocupados y si lo ultimo que paso fue una llegada o una salida de un mensaje)
	 * @param a indica si el ultimo cambio fue una llegada de mensaje o una salida
	 */
	public void mensajesActuales(String a){
		System.out.println("HAY " + mensajes.size() + " MENSAJES. " + a);
	}

	/**
	 * retira el primer mensaje del Buffer y lo retorna.
	 * @return el primer mensaje de la lista. retorna null si el Buffer esta vacio.
	 */
	public synchronized Mensaje retirarMensaje() {

		//Verifica si el buffer esta vacio. En caso de estarlo retorna null 
		if(mensajes.isEmpty()) {
			System.out.println("BUFFER VACIO");
			return null;
		}

		//saca el mensaje en el primer espacio del Buffer y lo guarda en una variable. Luego incrementa en uno la cantidad de espacios libres del Buffer.
		Mensaje message = mensajes.remove(0);
		recursosLibres++;

		//Imprime que se acaba de sacar el mensaje, seguido de su identificador y el nuevo estado del Buffer
		System.out.println("RETIRAR: " + message.identificar());
		mensajesActuales("(SE ACABA DE SACAR UNO)");

		//En caso de que haya exactamente un espacio libre luego de retirar el buffer, se avisa que el Buffer ahora no esta lleno
		if(recursosLibres==1) {
			System.out.println("BUFFER CON ESPACIO");
			notify();
		}
		
		return message;
	}
}

package caso1;

import java.util.ArrayList;

/**
 * Representa el servidor
 */
public class Servidor extends Thread {
	//-------------------------------------
	// ATRIBUTOS
	//-------------------------------------

	/**
	 * Representa la cantidad de threads que el servidor va a crear
	 */
	private int cantidadThreads;

	/**
	 * Una lista con todos los Threads que crea el servidor
	 */
	private ArrayList<ThreadServidor> threads;

	/**
	 * El buffer
	 */
	private Buffer buffer;

	//-------------------------------------
	// CONSTRUCTOR
	//-------------------------------------

	/**
	 * Metodo constructor. Crea un Servidor usando los valores que ingresan como parametro
	 * @param cantidadThreads la cantidad de threads que el servidor tiene que crear
	 * @param buffer el buffer del que los threads reciben los mensajes
	 */
	public Servidor(int cantidadThreads, Buffer buffer) {
		this.setCantidadThreads(cantidadThreads);
		threads = new ArrayList<ThreadServidor>();
		this.buffer = buffer;
		for (int i = 0; i < cantidadThreads; i++) {
			ThreadServidor nuevoThread = new ThreadServidor(buffer, i);
			threads.add(nuevoThread);
		}
	}

	/**
	 * retorna la cantidad de threads
	 * @return la cantidada de threads
	 */
	public int getCantidadThreads() {
		return cantidadThreads;
	}

	/**
	 * Ajusta la cantidad de threads de acuerdo al valor que entra como parametro
	 * @param cantidadThreads la nueva cantidad de threads
	 */
	public void setCantidadThreads(int cantidadThreads) {
		this.cantidadThreads = cantidadThreads;
	}

	/**
	 * Metodo run propio de los threads. Inicializa todos los threads que crea el servidor
	 */
	public void run() {
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}
	}
}

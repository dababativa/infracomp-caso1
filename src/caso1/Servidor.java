package caso1;

import java.util.ArrayList;

public class Servidor extends Thread {

	private int cantidadThreads;
	private ArrayList<ThreadServidor> threads;
	private Buffer buffer;

	public Servidor(int cantidadThreads, Buffer buffer) {
		this.cantidadThreads = cantidadThreads;
		threads = new ArrayList<ThreadServidor>();
		buffer = this.buffer;
		for (int i = 0; i < cantidadThreads; i++) {
			ThreadServidor nuevoThread = new ThreadServidor(buffer);
			threads.add(nuevoThread);
		}
	}
	

	public void run() {
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}
	}
}

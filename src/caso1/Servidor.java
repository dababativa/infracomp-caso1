package caso1;

import java.util.ArrayList;

public class Servidor extends Thread {

	private int cantidadThreads;
	private ArrayList<ThreadServidor> threads;
	private Buffer buffer;

	public Servidor(int cantidadThreads, Buffer buffer) {
		this.setCantidadThreads(cantidadThreads);
		threads = new ArrayList<ThreadServidor>();
		this.buffer = buffer;
		for (int i = 0; i < cantidadThreads; i++) {
			ThreadServidor nuevoThread = new ThreadServidor(buffer, i);
			threads.add(nuevoThread);
		}
		System.out.println("Se creo un servidor con " + cantidadThreads + " de threads");
	}

	public int getCantidadThreads() {
		return cantidadThreads;
	}

	public void setCantidadThreads(int cantidadThreads) {
		this.cantidadThreads = cantidadThreads;
	}

	public void run() {
		System.out.println("El servidor va a crear Threads");
		for (int i = 0; i < threads.size(); i++) {
			System.out.println("Se emperica un Thread");
			threads.get(i).start();
		}
	}
}

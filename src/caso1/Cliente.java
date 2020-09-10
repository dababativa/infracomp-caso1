package caso1;

import java.util.ArrayList;

public class Cliente extends Thread {

	private ArrayList<Mensaje> mensajes;
	private Buffer buffer;
	
	public Cliente(int cantidadMensajes, Buffer buffer) {
		mensajes = new ArrayList<Mensaje>();
		this.buffer = buffer;
		for (int i = 0; i < cantidadMensajes; i++) {
			mensajes.add(new Mensaje(i,this));
		}
	}
	
	public void run() {
		while(!mensajes.isEmpty()) {
			Mensaje mensajeActual = mensajes.remove(0);
			buffer.enviarMensaje(mensajeActual);
			mensajeActual.enviarMensaje();
		}
		
	}
}

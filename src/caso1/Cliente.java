package caso1;

import java.util.ArrayList;

public class Cliente extends Thread {

	private ArrayList<Mensaje> mensajes;
	private Buffer buffer;
	private int id;
	
	public Cliente(int cantidadMensajes, Buffer pBuffer) {
		mensajes = new ArrayList<Mensaje>();
		id = cantidadMensajes;
		buffer = pBuffer;
		for (int i = 0; i < cantidadMensajes; i++) {
			mensajes.add(new Mensaje(i,this));
		}
	}
	
	public void run() {
		while(!mensajes.isEmpty()) {
			Mensaje mensajeActual = mensajes.remove(0);
			System.out.println("Se va a enviar " + mensajeActual.identificar());
			buffer.enviarMensaje(mensajeActual);
			mensajeActual.enviarMensaje();
		}
		System.out.println(id + " dice chao");
		buffer.quite();
	}
	public int darId() {
		 return id;
	}
}

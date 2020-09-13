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
		buffer.existo();
		for (int i = 0; i < cantidadMensajes; i++) {
			mensajes.add(new Mensaje(i,this));
		}
		System.out.println("Se creo un Cliente con " + cantidadMensajes + " de mensajes");
	}
	
	public void run() {
		System.out.println("El Cliente"+ id +  "va a hacer sus cosas");
		while(!mensajes.isEmpty()) {
			System.out.println("Cliente " + id + " haciendo cosas");
			Mensaje mensajeActual = mensajes.remove(0);
			buffer.enviarMensaje(mensajeActual);
			mensajeActual.enviarMensaje();
		}
		
	}
	public int darId() {
		 return id;
	}
}

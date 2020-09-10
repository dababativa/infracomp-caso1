package caso1;

import java.util.ArrayList;

public class Buffer {
	
	private int recursosLibres;
	private ArrayList<Mensaje> mensajes;
	
	public Buffer(int tamanho) {
		recursosLibres = tamanho;
		mensajes = new ArrayList<Mensaje>();
	}
	
	public void enviarMensaje(Mensaje mensaje) {
		if(recursosLibres<1) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		recursosLibres--;
		mensajes.add(mensaje);
	}
	
	public Mensaje retirarMensaje() {
		if(mensajes.isEmpty()) {
			return null;
		}
		Mensaje message = mensajes.remove(0);
		recursosLibres++;
		if(recursosLibres==1) {
			notify();
		}
		return message;
	}
}

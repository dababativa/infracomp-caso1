package caso1;

public class Mensaje {

	private int contenido;
	private Cliente remitente;
	
	public Mensaje(int contenido, Cliente remitente) {
		System.out.println("se va a crear un mensaje: " + contenido + " del cliente " + remitente.darId());
		this.contenido = contenido;
		this.remitente = remitente;
	}
	
	public void enviarMensaje() {
		try {
			System.out.println("El mensaje " + contenido + " de " + remitente.darId() + "se va a enviar");
			this.wait();
			System.out.println("El mensaje fue respondido " + contenido);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void responderMensaje() {
		System.out.println("Se va a responder un mensaje " + contenido);
		contenido = -contenido;
		this.notify();
	}
}

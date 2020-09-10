package caso1;

public class Mensaje {

	private int contenido;
	private Cliente remitente;
	
	public Mensaje(int contenido, Cliente remitente) {
		this.contenido = contenido;
		this.remitente = remitente;
	}
	
	public void enviarMensaje() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void responderMensaje() {
		contenido = -contenido;
		this.notify();
	}
}

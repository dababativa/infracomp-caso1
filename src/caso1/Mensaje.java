package caso1;

public class Mensaje {

	private int contenido;
	private Cliente remitente;
	
	public Mensaje(int contenido, Cliente remitente) {
		this.contenido = contenido;
		this.remitente = remitente;
	}
	
	public int darRemitente(){
		return remitente.darId();
	}
	public synchronized void enviarMensaje() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String identificar(){
		return remitente.darId() + "." + contenido;
	}
	public synchronized void responderMensaje() {
		contenido = -contenido;
		notify();
	}
}

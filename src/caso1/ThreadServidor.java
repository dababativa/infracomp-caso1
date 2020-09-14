package caso1;

public class ThreadServidor extends Thread{

	private Buffer buffer;
	private int id;
	public ThreadServidor(Buffer buffer, int pId) {
		this.buffer = buffer;
		id = pId;
	}

	public void run() {
		while(buffer.hayClientes()) {
			Mensaje mensaje = buffer.retirarMensaje();
			if(mensaje==null) {
				System.out.println("El thread " + id + " no encontro mensajes en el buffer, se va a devolver a listo");
				yield();
			} else {
				System.out.println("el thread " + id + "va a responder un mensaje " + mensaje.identificar());
				mensaje.responderMensaje();
			}
		
		}
		System.out.println(id + " se despide");
	}
}

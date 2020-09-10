package caso1;

public class ThreadServidor extends Thread{

	private Buffer buffer;

	public ThreadServidor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		while(true) {
			Mensaje mensaje = buffer.retirarMensaje();
			if(mensaje==null) {
				yield();
			} else {
				mensaje.responderMensaje();
			}
		
		}
	}
}

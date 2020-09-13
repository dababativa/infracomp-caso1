package caso1;

public class ThreadServidor extends Thread{

	private Buffer buffer;
	private int id;
	public ThreadServidor(Buffer buffer, int pId) {
		this.buffer = buffer;
		buffer.existo();
		id = pId;
		System.out.println("Se creo un thread de servidor con id " + id);
	}

	public void run() {
		System.out.println("El thread " + id + " va a ahcer cosas");
		while(true) {
			System.out.println("El thread " + id +" esta haciendo cosas");
			Mensaje mensaje = buffer.retirarMensaje();
			if(mensaje==null) {
				System.out.println("El thread " + id + " no encontro mensajes en el buffer, se va a devolver a listo");
				yield();
			} else {
				System.out.println("el thread " + id + "va a responder un mensaje");
				mensaje.responderMensaje();
			}
		
		}
	}
}

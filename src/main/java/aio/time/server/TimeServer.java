package aio.time.server;

public abstract class TimeServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8080;
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
	}

}

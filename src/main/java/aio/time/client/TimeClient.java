package aio.time.client;

public class TimeClient {
	public static void main(String[] args){
		int port = 8080;
		new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
		System.out.println(1);
		System.out.println(2);
		///1234132413433
		System.out.println("github update");
	}
}

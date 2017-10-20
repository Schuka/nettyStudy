package aio.time.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

	private AsynchronousSocketChannel channel;
	
	public ReadCompletionHandler(AsynchronousSocketChannel channel){
		if(channel != null){
			this.channel = channel;
		}
	}
	
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		try{
			String req = new String(body, "UTF-8");
			System.out.println("The time server receive order: " + req);
			String currentTime = "QUERY TIME ORDER".equals(req) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
			doWrite(currentTime);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void doWrite(String currentTime){
		if(currentTime != null && currentTime.trim().length() > 0){
			byte[] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>(){

				public void completed(Integer result, ByteBuffer buffer){
					if(buffer.hasRemaining()){
						channel.write(buffer, buffer, this);
					}
				}
				
				public void failed(Throwable exc, ByteBuffer attachement){
					try{
						channel.close();
					}catch(Exception e){
							
					}
				}
				
			});
		}
	}
	
	public void failed(Throwable arg0, ByteBuffer arg1) {
		try{
			channel.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

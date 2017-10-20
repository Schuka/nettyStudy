package aio.time.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements
		CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

	public void completed(AsynchronousSocketChannel result,
			AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		result.read(readBuffer, readBuffer, new ReadCompletionHandler(result));
	}

	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}

}

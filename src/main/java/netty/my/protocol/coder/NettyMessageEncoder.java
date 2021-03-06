package netty.my.protocol.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import netty.my.protocol.message.NettyMessage;

public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
	NettyMarshallingEncoder  marshallingEncoder;
	
	public NettyMessageEncoder() throws IOException {
		this.marshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		if(msg == null || msg.getHeader() == null){
			throw new Exception("The encode message is null");
		}
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt(msg.getHeader().getCrcCode());
		sendBuf.writeInt(msg.getHeader().getLength());
		sendBuf.writeLong(msg.getHeader().getSessionID());
		sendBuf.writeByte(msg.getHeader().getType());
		sendBuf.writeByte(msg.getHeader().getPriorty());
		sendBuf.writeInt(msg.getHeader().getAttachment().size());
		
		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for(Map.Entry<String, Object> param: msg.getHeader().getAttachment().entrySet()){
			key = param.getKey();
			keyArray = key.getBytes("UTF-8");
			sendBuf.writeInt(keyArray.length);
			sendBuf.writeBytes(keyArray);
			value = param.getValue();
			 marshallingEncoder.encode(ctx, value, sendBuf); 
		}
		
		key = null;
		keyArray = null;
		value = null;
		
		if(msg.getBody() != null){
			marshallingEncoder.encode(ctx, msg.getBody(), sendBuf);  
		}else{
			sendBuf.writeInt(0);
		}
		sendBuf.setIndex(4, sendBuf.readableBytes());
		out.add(sendBuf);  
	}
	
	
}

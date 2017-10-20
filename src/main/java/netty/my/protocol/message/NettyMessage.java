package netty.my.protocol.message;

public final class NettyMessage {
	private Header header;
	
	private Object body;
	
	public final Header getHeader() {
		return this.header;
	}
	
	public final void setHeader(Header header) {
		this.header = header;
	}
	
	public final Object getBody() {
		return this.body;
	}
	
	public final void setBody(Object body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "NettyMessage [header=" + header + "]";
	}
}

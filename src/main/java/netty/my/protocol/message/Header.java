package netty.my.protocol.message;

import java.util.HashMap;
import java.util.Map;

public final class Header {
	private int crcCode = 0xabef0101;
	private int length;
	private long sessionID;
	private byte type;
	private byte priorty;
	private Map<String, Object> attachment = new HashMap<String, Object>();
	public int getCrcCode() {
		return crcCode;
	}
	public final void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}
	public final int getLength() {
		return length;
	}
	public final void setLength(int length) {
		this.length = length;
	}
	public final long getSessionID() {
		return sessionID;
	}
	public final void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
	public final byte getType() {
		return type;
	}
	public final void setType(byte type) {
		this.type = type;
	}
	public final byte getPriorty() {
		return priorty;
	}
	public final void setPriorty(byte priorty) {
		this.priorty = priorty;
	}
	public final Map<String, Object> getAttachment() {
		return attachment;
	}
	public final void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "Header [crcCode=" + crcCode + ", length=" + length
				+ ", sessionID=" + sessionID + ", type=" + type + ", priorty="
				+ priorty + ", attachment=" + attachment + "]";
	}
	
}

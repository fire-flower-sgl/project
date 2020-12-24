package com.mhtech.platform.msrv.pojo;

public class ServerAlertLogQty {

	private long serverId;
	
	private int qty;
	
	private boolean isAlerting;

	public long getServerId() {
		return serverId;
	}

	public void setServerId(long serverId) {
		this.serverId = serverId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isAlerting() {
		return isAlerting;
	}

	public void setAlerting(boolean isAlerting) {
		this.isAlerting = isAlerting;
	}
}

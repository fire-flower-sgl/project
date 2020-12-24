package com.mhtech.platform.msrv.pojo;

import java.util.List;

public class ServerGrpInfo extends ServerInfo {

	private static final long serialVersionUID = 1482612162710345230L;

	private List<ServerInfo> subServers;

	public List<ServerInfo> getSubServers() {
		return subServers;
	}

	public void setSubServers(List<ServerInfo> subServers) {
		this.subServers = subServers;
	}
}

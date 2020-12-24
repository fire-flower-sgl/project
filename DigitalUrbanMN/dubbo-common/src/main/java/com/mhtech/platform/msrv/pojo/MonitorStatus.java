package com.mhtech.platform.msrv.pojo;

public interface MonitorStatus {

	public static enum ServerStatus {

		Activator((short) 10, "激活"),
		Disabled((short) 20, "停用");
		
		private ServerStatus(short code, String alias) {
			this.code = code;
			this.alias = alias;
		}
		
		public static ServerStatus valueOf(short code) {
			ServerStatus[] status = values();
			for (ServerStatus serverStatus : status) {
				if(serverStatus.code == code) {
					return serverStatus;
				}
			}
			return null;
		} 
		
		private short code;
		private String alias;
		public short getCode() {
			return code;
		}
		public String getAlias() {
			return alias;
		}
	}
	
	public static enum ParamAlertStatus {
		Activator((short) 10, "激活"),
		Disabled((short) 20, "停用");
		
		private ParamAlertStatus(short code, String alias) {
			this.code = code;
			this.alias = alias;
		}
		
		public static ParamAlertStatus valueOf(short code) {
			ParamAlertStatus[] stats = values();
			for (ParamAlertStatus stat : stats) {
				if(stat.code == code) {
					return stat;
				}
			}
			return null;
		}
		
		private short code;
		private String alias;
		public short getCode() {
			return code;
		}
		public String getAlias() {
			return alias;
		}
	}
	
	public static enum AlertLogStatus {
		NoneAlert((short) 10, "未产生告警"),
		Alerting((short) 20, "告警");
		
		private AlertLogStatus(short code, String alias) {
			this.code = code;
			this.alias = alias;
		}
		
		public static AlertLogStatus valueOf(short code) {
			AlertLogStatus[] stats = values();
			for (AlertLogStatus stat : stats) {
				if(stat.code == code) {
					return stat;
				}
			}
			return null;
		}
		
		private short code;
		private String alias;
		public short getCode() {
			return code;
		}
		public String getAlias() {
			return alias;
		}
	}
	
	public static enum NotifyLogStatus {
		Unprocessed((short) 10, "未处理"),
		Onprocessed((short) 15, "未处理"),
		Processed((short) 20, "已处理");
		
		private NotifyLogStatus(short code, String alias) {
			this.code = code;
			this.alias = alias;
		}
		
		public static NotifyLogStatus valueOf(short code) {
			NotifyLogStatus[] stats = values();
			for (NotifyLogStatus stat : stats) {
				if(stat.code == code) {
					return stat;
				}
			}
			return null;
		}
		
		private short code;
		private String alias;
		public short getCode() {
			return code;
		}
		public String getAlias() {
			return alias;
		}
	}
}

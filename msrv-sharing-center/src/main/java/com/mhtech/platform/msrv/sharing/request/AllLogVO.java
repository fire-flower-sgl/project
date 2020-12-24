package com.mhtech.platform.msrv.sharing.request;


import com.mhtech.platform.msrv.sharing.dao.model.SpUserLog;

public class AllLogVO {
	
	
		private Long id;
	    private String userCode;
	    private String actionModule;
	    private String actionNum;
	    private String actionStartTime;
	    private String actionEndTime;
	    
		private SpUserLog spUserLog;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public String getActionModule() {
			return actionModule;
		}

		public void setActionModule(String actionModule) {
			this.actionModule = actionModule;
		}

		public String getActionNum() {
			return actionNum;
		}

		public void setActionNum(String actionNum) {
			this.actionNum = actionNum;
		}

		public String getActionStartTime() {
			return actionStartTime;
		}

		public void setActionStartTime(String actionStartTime) {
			this.actionStartTime = actionStartTime;
		}

		public String getActionEndTime() {
			return actionEndTime;
		}

		public void setActionEndTime(String actionEndTime) {
			this.actionEndTime = actionEndTime;
		}

		public SpUserLog getSpUserLog() {
			return spUserLog;
		}

		public void setSpUserLog(SpUserLog spUserLog) {
			this.spUserLog = spUserLog;
		}


		public AllLogVO(Long id, String userCode, String actionModule, String actionNum, String actionStartTime,
				String actionEndTime, SpUserLog spUserLog) {
			super();
			this.id = id;
			this.userCode = userCode;
			this.actionModule = actionModule;
			this.actionNum = actionNum;
			this.actionStartTime = actionStartTime;
			this.actionEndTime = actionEndTime;
			this.spUserLog = spUserLog;
		}

		@Override
		public String toString() {
			return "AllLogVO [id=" + id + ", userCode=" + userCode + ", actionModule=" + actionModule + ", actionNum="
					+ actionNum + ", actionStartTime=" + actionStartTime + ", actionEndTime=" + actionEndTime
					+ ", spUserLog=" + spUserLog + "]";
		}
		
		
		
		
		
}

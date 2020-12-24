package com.mhtech.platform.msrv.pojo;

public interface DataSetConstant {

	/**
	 * 数据集是否开放
	 * @author GM
	 */
	public static enum OpenState {
		
		OPEN(Boolean.TRUE),
		NOT_OPEN(Boolean.FALSE);
		
		private boolean state;
		private OpenState(boolean state) {
			this.state = state;
		}
		public boolean isState() {
			return state;
		}
	}
	
	/**
	 * 数据集业务状态
	 * @author GM
	 */
	public static enum BizState {
		
		VALID((short) 10), INVALID((short) 20);
		
		public static BizState valueOf(short state) {
			BizState[] values = values();
			for (BizState bizState : values) {
				if(bizState.state == state) {
					return bizState;
				}
			}
			return null;
		}
		
		private short state;
		private BizState(short state) {
			this.state = state;
		}
		public short getState() {
			return state;
		}
	}
}

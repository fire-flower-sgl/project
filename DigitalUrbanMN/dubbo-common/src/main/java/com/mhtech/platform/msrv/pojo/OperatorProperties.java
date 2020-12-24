package com.mhtech.platform.msrv.pojo;

public interface OperatorProperties {

	public static enum IsOpen {
		OPEN((short) 1, "开放"),
		CLOSE((short) 2, "未开放");
		private Short value;
		private String type;
		private IsOpen(Short value, String type) {
			this.value = value;
			this.type = type;
		}
		public Short getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
	
	//10:输入;20:输出;30:设置
	public static enum ParamType {
		INPUT((short) 10, "输入"),
		OUTPUT((short) 20, "输出"),
		SETTING((short) 30, "设置");
		
		public static ParamType valueOf(Short val) {
			ParamType[] types = values();
			for (ParamType paramType : types) {
				if(paramType.value == val) {
					return paramType;
				}
			}
			return null;
		}
		
		private Short value;
		private String type;
		private ParamType(Short value, String type) {
			this.value = value;
			this.type = type;
		}
		public Short getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
	
	//10:文本框;20:单选;30:多选;40:日期(yyyy-MM-dd);50:时间(yyyy-MM-dd HH:mm:ss)
	public static enum FormType {
		TEXT((short) 10, "文本框"),
		RADIO((short) 20, "单选"),
		CHECKBOX((short) 30, "多选"),
		DATE((short) 40, "日期"),
		TIME((short) 50, "时间");
		private Short value;
		private String type;
		
		public static FormType valueOf(Short val) {
			FormType[] types = values();
			for (FormType formType : types) {
				if(formType.value == val) {
					return formType;
				}
			}
			return null;
		}
		
		private FormType(Short value, String type) {
			this.value = value;
			this.type = type;
		}
		public Short getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
	
	//0:用户填写; 10:数据集表名; 20:表列名; 30:字典数据
	public static enum BindData {
		FILLED((short) 0, "用户填写"),
		TABLENAME((short) 10, "数据集表名"),
		COLUMNNAME((short) 20, "表列名"),
		DICT((short) 30, "字典数据");
		private Short value;
		private String type;
		
		public static BindData valueOf(Short val) {
			BindData[] bindDatas = values();
			for (BindData bindData : bindDatas) {
				if(bindData.value == val) {
					return bindData;
				}
			}
			return null;
		}
		
		private BindData(Short value, String type) {
			this.value = value;
			this.type = type;
		}
		public Short getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
	
	//0:选填; 1:必填
	public static enum FieldRequired {
		Optional(Boolean.FALSE, "选填"),
		Required(Boolean.TRUE, "必填");
		private Boolean value;
		private String type;
		private FieldRequired(Boolean value, String type) {
			this.value = value;
			this.type = type;
		}
		public Boolean getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
	
	//0:否（由系统定义对用户隐藏）;1:是
	public static enum IsFixed {
		FIXABLE(Boolean.FALSE, "否"),
		NONFIXABLE(Boolean.TRUE, "是");
		private Boolean value;
		private String type;
		private IsFixed(Boolean value, String type) {
			this.value = value;
			this.type = type;
		}
		public Boolean getValue() {
			return value;
		}
		public String getType() {
			return type;
		}
	}
}

package com.mobile.model;

import java.io.Serializable;

/**
 * 当前容器上下文
 * @author GM
 */
public class ApplicationContextHolder implements Serializable {

	private static ThreadLocal<LoginUser> userInfo  = new ThreadLocal<LoginUser>();
	/**
	 * 在控制层受理请求前为空
	 */
	private static ThreadLocal<Action> action = new ThreadLocal<Action>();
	/**
	 * 当前request内容
	 */
	private static ThreadLocal<RequestWrapper> rw = new ThreadLocal<RequestWrapper>();
	
	/**
	 * 当前登入用户
	 * @return
	 */
	public static LoginUser getCurrtUser() {
		return userInfo.get();
	}
	
	/**
	 * 保存当前用户
	 * @param user
	 */
	public static void setCurrtUser(LoginUser user) {
		userInfo.set(user);
	}
	
	/**
	 * 移除
	 */
	public static void removeCurrtUser() {
		userInfo.remove();
	}
	
	/**
	 * 获取当前用户行为
	 * @return
	 */
	public static Action getAction() {
		return action.get();
	}
	
	/**
	 * 移除action
	 */
	public static void removeAction() {
		action.remove();
	}
	
	/**
	 * 移除rw
	 */
	public static void removRW() {
		rw.remove();
	}
	
	/**
	 * 设置行为
	 * @param act
	 */
	public static void setAction(Action act) {
		action.set(act);
	}
	
	public static void setRequestContext(RequestWrapper request) {
		rw.set(request);
	}
	
	public static RequestWrapper getRequestContext() {
		return rw.get();
	}
}

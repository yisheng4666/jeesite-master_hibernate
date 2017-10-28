package com.thinkgem.jeesite.modules.sys.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.sys.entity.Msg;
import com.thinkgem.jeesite.modules.sys.entity.User;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.SessionManager;

/**
 * 消息推送工具类
 * 
 * @author Yyz
 * @version 2017-10-23
 */
public class MsgPushUtils {

	/**
	 * 点播
	 * 
	 * @param user
	 *            接收对象
	 * @param msg
	 *            消息
	 */
	public static void unicast(User user, Msg msg) {
		if (SessionManager.getInstance().hasSession(user.getId())) {
			Event event = Event.createDataEvent("common");
			event.setField("msgTitle", encode(msg.getTitle()));
			event.setField("msgContent", encode(msg.getContent()));
			Dispatcher.getInstance().unicast(event, user.getId()); // 向用户推送

			System.out.println("uc success...." + "--->" + user.getName());
		} else {
			System.out.println(user.getName() + " do not login....");
		}
	}

	/**
	 * 组播
	 * 
	 * @param tag
	 *            组播对象
	 * @param msg
	 *            消息
	 */
	public static void multicast(String tag, Msg msg) {
		Event event = Event.createDataEvent(tag);
		event.setField("msgTitle", encode(msg.getTitle()));
		event.setField("msgContent", encode(msg.getContent()));
		Dispatcher.getInstance().multicast(event); // 向所有和tag名称匹配的事件推送

		System.out.println("mc success....");
	}

	/**
	 * 广播
	 * 
	 * @param msg
	 *            消息
	 */
	public static void broadcast(Msg msg) {
		Event event = Event.createDataEvent(IdGen.uuid()); // 向所有的事件推送
		event.setField("msgTitle", encode(msg.getTitle()));
		event.setField("msgContent", encode(msg.getContent()));
		Dispatcher.getInstance().broadcast(event);

		System.out.println("bc success....");
	}
	
	private static String encode(String str) {
		try {
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}

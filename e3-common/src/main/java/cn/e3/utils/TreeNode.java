package cn.e3.utils;

import java.io.Serializable;

public class TreeNode implements Serializable{
	/**
	 * [{
	 * 	"id":1,
	 *  "text":"Node 1",
	 *  "state":"closed"
	 * }]
	 */
	private Long id;  //树形节点的id
	private String text; // 树形节点的名称
	private String state;  
	//如果is_parent = 1 ,表示有子节点, state = 'closed',表示可打开
	//如果is_parent = 0 ,表示没有子节点,state = 'open',表示不可打开 	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

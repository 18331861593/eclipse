package com.three.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	/**
	 * 显示节点的id
	 */
	private int id;
	
	/**
	 * 辅助字段
	 */
	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 数据库状态状态
	 */
	private String mstate;
	
	public String getMstate() {
		return mstate;
	}

	public void setMstate(String mstate) {
		this.mstate = mstate;
	}

	/**
	 * 显示节点的名称
	 */
	private String text;
	/**
	 * 显示节点的图标
	 */
	private String iconCls;
	/**
	 * 显示节点的父节点
	 */
	private int parentId;
	/**
	 * 显示节点的子节点集合
	 */
	private List<TreeNode> children;
	private String url;
	/**
	 * 被添加到节点的自定义属性
	 * */
   private  Object attributes;
	
    /**
     * 是否勾选
     * */
   private boolean checked;	
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 空的构造函数
	 */
	public TreeNode() {

	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	/**
	 * 添加子节点的方法
	 * 
	 * @param node
	 *            树节点实体
	 */
	public void addChild(TreeNode node) {
		if (this.children == null) {
			children = new ArrayList<TreeNode>();
			children.add(node);
		} else {
			children.add(node);
		}
	}

	public TreeNode(int id, String text, String iconCls, int parentId,
			List<TreeNode> children, String url, Object attributes,boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.children = children;
		this.url = url;
		this.attributes = attributes;
		this.checked=checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getParentId() {
		return parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setParentId(int i) {
		this.parentId = i;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}

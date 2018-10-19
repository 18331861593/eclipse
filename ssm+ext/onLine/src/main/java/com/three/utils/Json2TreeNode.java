package com.three.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * 和 easyui的tree 进行转换
 * @author Administrator
 *
 */
public class Json2TreeNode {
	
	public static List<TreeNode> buildtree(List<TreeNode> nodes, int id){
		 List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		 for (TreeNode treeNode : nodes) {
			 TreeNode node = new TreeNode();
			 node.setId(treeNode.getId());
			 node.setText(treeNode.getText());
			 node.setIconCls(treeNode.getIconCls());
			 node.setMstate(treeNode.getMstate());
			 node.setUrl(treeNode.getUrl());
			 //新增一行:权限分配是否勾选
			 node.setChecked(treeNode.isChecked());
			 node.setParentId(treeNode.getParentId());
			 //下面这个HashMap是给tree中的attributes赋值
			 if (id == treeNode.getParentId()) {
				 System.out.println();
				 node.setChildren(buildtree(nodes, node.getId()));
				 treeNodes.add(node);
			 }
		 }
		 return treeNodes;
	}
	
	public static List<ExtTreeNode> buildExttree(List<ExtTreeNode> nodes, int id){
		 List<ExtTreeNode> treeNodes = new ArrayList<ExtTreeNode>();
		 for (ExtTreeNode treeNode : nodes) {
			 ExtTreeNode node = new ExtTreeNode();
			 node.setId(treeNode.getId());
			 node.setText(treeNode.getText());
			 node.setIconCls(treeNode.getIconCls());
			 node.setMstate(treeNode.getMstate());
			 node.setState(treeNode.getState());
			 node.setUrl(treeNode.getUrl());
			 //新增一行:权限分配是否勾选
			 node.setParentId(treeNode.getParentId());
			 //下面这个HashMap是给tree中的attributes赋值
			 if (id == treeNode.getParentId()) {
				 System.out.println();
				 node.setChildren(buildExttree(nodes, node.getId()));
				 treeNodes.add(node);
			 }
		 }
		 return treeNodes;
	}
	
}

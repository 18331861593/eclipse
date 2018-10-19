package com.hello.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.hello.shiro.entity.User;

public class UserDao {

	public User getByUsername(Connection conn, String username) throws Exception {
		User result = null;

		String sql = "select * from t_user where username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			result = new User();
			result.setId(rs.getInt("id"));
			result.setUsername(rs.getString("username"));
			result.setPassword(rs.getString("password"));
		}

		return result;
	}

	public Set<String> getPermissions(Connection conn, String username) throws Exception {
		Set<String> permissions = new HashSet<String>();
		String sql = "select * from t_user u, t_role r, t_permission p where u.roleId = r.id and p.roleId = r.id and u.username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			permissions.add(rs.getString("permissionName"));
		}
		return permissions;
	}

	public Set<String> getRoles(Connection conn, String username) throws Exception {
		Set<String> roles = new HashSet<String>();
		String sql = "select * from t_user u,t_role r where u.roleId = r.id and u.username = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			roles.add(rs.getString("rolename"));
		}
		return roles;
	}

}

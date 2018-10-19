package com.hello.shiro.Realm;

import java.sql.Connection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hello.shiro.dao.UserDao;
import com.hello.shiro.entity.User;
import com.hello.shiro.utils.DbUtils;

public class MyRealm extends AuthorizingRealm {

	private UserDao userDao = new UserDao();

	/**
	 * 为当前登录成功的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Connection conn = null;

		try {
			conn = DbUtils.getConn();
			authorizationInfo.setRoles(userDao.getRoles(conn,username));
			authorizationInfo.setStringPermissions(userDao.getPermissions(conn,username));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.closeConn(conn);
			} catch (Exception e2) {
			}
		}

		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Connection conn = null;

		try {
			conn = DbUtils.getConn();
			User user = userDao.getByUsername(conn, username);
			if (user != null) {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
						"xx");
				return authcInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.closeConn(conn);
			} catch (Exception e2) {
			}
		}

		return null;
	}

}

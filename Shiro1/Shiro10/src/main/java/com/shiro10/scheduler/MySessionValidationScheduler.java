package com.shiro10.scheduler;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import com.shiro10.JdbcTemplateUtils;
import com.shiro10.SerializableUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MySessionValidationScheduler implements SessionValidationScheduler, Runnable {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	/** Private internal log instance. */
	private static final Logger log = LoggerFactory.getLogger(MySessionValidationScheduler.class);

	ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	private boolean enabled = false;

	public MySessionValidationScheduler() {
		super();
	}

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void run() {
		if (log.isDebugEnabled()) {
			log.debug("Executing session validation...");
		}
		long startTime = System.currentTimeMillis();

		String sql = "select session from sessions limit ?,?";
		int start = 20;
		int size = 20;
		List<String> sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
		while (sessionList.size() > 0) {
			for (String sessionStr : sessionList) {
				try {
					Session session = SerializableUtils.deserialize(sessionStr);
					Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class,
							"validate", Session.class, SessionKey.class);
					validateMethod.setAccessible(true);
					ReflectionUtils.invokeMethod(validateMethod, sessionManager, session,
							new DefaultSessionKey(session.getId()));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			start = start + size;
			sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
			long stopTime = System.currentTimeMillis();
			if (log.isDebugEnabled()) {
				log.debug("Session validation completed successfully in " + (stopTime - startTime) + " milliseconds.");
			}
		}
	}

	public boolean isEnabled() {
		return false;
	}

	public void enableSessionValidation() {
		if (this.interval > 0l) {
			this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
			});
			this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
			this.enabled = true;
		}

	}

	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;
	}

}

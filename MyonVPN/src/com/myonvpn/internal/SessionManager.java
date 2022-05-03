/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

/**
 * SessionManager
 * @author FantaBlueMystery
 */
public class SessionManager {

	/**
	 * SESSION DATA
	 */
	static private final File SESSION_DATA = new File("./.sessions");

	/**
	 * RANDOM
	 */
	static private final Random RANDOM = new Random();

	/**
	 * TOKEN SIZE
	 */
	static private final int TOKEN_SIZE = 24;

	/**
	 * HEX
	 */
	static private final char[] HEX = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	/**
	 * TOKEN COOKIE
	 */
	static private final String TOKEN_COOKIE = "__SESSION_ID__";

	/**
	 * sessions
	 */
	private static HashMap<String, Session> _sessions = new HashMap<>();

	/**
	 * genSessionToken
	 * @return
	 */
	static private String genSessionToken() {
		StringBuilder sb = new StringBuilder(SessionManager.TOKEN_SIZE);

		for(int i=0; i<SessionManager.TOKEN_SIZE; i++ ) {
			sb.append(SessionManager.HEX[SessionManager.RANDOM.nextInt(SessionManager.HEX.length)]);
		}

		return sb.toString();
	}

	/**
	 * newSessionToken
	 * @return
	 */
	static private String newSessionToken(){
		String token;

		do {
			token = SessionManager.genSessionToken();
		}
		while( SessionManager._sessions.containsKey(token) );

		return token;
	}

	/**
	 * findOrCreate
	 * @param cookies
	 * @return
	 */
	static public synchronized Session findOrCreate(ICookieHandler cookies){
		String token = cookies.get(TOKEN_COOKIE);

		if( token == null ) {
			token = newSessionToken();
			cookies.set(TOKEN_COOKIE, token);
		}

		if( !SessionManager._sessions.containsKey(token) ) {
			SessionManager._sessions.put(token, new Session(token));
		}

		return SessionManager._sessions.get(token);
	}

	/**
	 * destroy
	 * @param token
	 * @param cookies
	 */
	static public void destroy(String token, ICookieHandler cookies){
		SessionManager._sessions.remove(token);
		cookies.delete(TOKEN_COOKIE);
	}

	/**
	 * load
	 * @throws Exception
	 */
	static public void load() throws Exception{
		if( !SessionManager.SESSION_DATA.exists() ) {
			return;
		}

		try( FileInputStream input = new FileInputStream(SESSION_DATA) ) {
			SessionManager._sessions = (HashMap<String, Session>) new ObjectInputStream(input).readObject();
		}
	}

	/**
	 * save
	 * @throws Exception
	 */
	static public void save() throws Exception{
		try( FileOutputStream output = new FileOutputStream(SESSION_DATA) ) {
			new ObjectOutputStream(output).writeObject(SessionManager._sessions);
		}
	}
}
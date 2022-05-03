/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.internal;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Session
 * @author FantaBlueMystery
 */
public class Session implements ISession, Serializable {

	/**
	 * data
	 */
	private HashMap<String, Serializable> _data = new HashMap<>();

	/**
	 * session id
	 */
	private final String _sessionID;

	/**
	 * Session
	 * @param sessionID
	 */
	public Session(String sessionID){
		this._sessionID = sessionID;
	}

	/**
	 * get
	 * @param key
	 * @return
	 */
	@Override
	public Serializable get(String key) {
		return this._data.get(key);
	}

	/**
	 * set
	 * @param key
	 * @param value
	 */
	@Override
	public void set(String key, Serializable value){
		this._data.put(key, value);
	}

	/**
	 * destroy
	 * @param cookies
	 */
	@Override
	public void destroy(ICookieHandler cookies) {
		SessionManager.destroy(this._sessionID, cookies);
	}
}
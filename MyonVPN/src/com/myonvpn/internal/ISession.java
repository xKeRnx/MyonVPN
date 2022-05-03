/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.internal;

import java.io.Serializable;

/**
 * ISession
 * @author FantaBlueMystery
 */
public interface ISession {

	/**
	 * get
	 * @param key
	 * @return
	 */
	public Serializable get(String key);

	/**
	 * set
	 * @param key
	 * @param value
	 */
	public void set(String key, Serializable value);

	/**
	 * destroy
	 * @param cookies
	 */
	public void destroy(ICookieHandler cookies);
}
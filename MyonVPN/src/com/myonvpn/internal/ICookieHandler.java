/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.internal;

/**
 * ICookieHandler
 * @author FantaBlueMystery
 */
public interface ICookieHandler {

	/**
	 * set
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * get
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * delete
	 * @param key
	 */
	public void delete(String key);
}
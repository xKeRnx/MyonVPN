/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.tuntap;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * TunTap
 * @author FantaBlueMystery
 */
public abstract class TunTap {

	/**
	 * ip
	 */
	private byte[] _ip = null;

	/**
	 * loadLib
	 * @param libs
	 * @throws Throwable
	 */
	static void loadLib(String... libs) throws Throwable {
		Throwable e = null;

		for( String lib: libs ) {
			try {
				System.load(new File(lib).getCanonicalPath());
				break;
			}
			catch( Throwable ex ) {
				e = ex;
			}
		}

		if( e != null ) {
			throw e;
		}
	}

	/**
	 * createTunTap
	 * @return
	 * @throws Exception
	 */
	static public TunTap createTunTap() throws Exception {
		String osName = System.getProperty("os.name");

		if( osName.startsWith("Windows") ) {
			return new TunTapWindows();
		}
		else if( osName.equals("Linux") ) {
			return new TunTapLinux();
		}
		else {
			throw new Exception("The operating system " + osName + " is not supported!");
		}
	}

	/**
	 * getDev
	 * @return
	 */
	abstract public String getDev();

	/**
	 * _errorLog
	 * @param message
	 */
	abstract protected void _errorLog(String message);

	/**
	 * close
	 */
	abstract public void close();

	/**
	 * write
	 * @param b
	 * @param len
	 */
	abstract public void write(byte[] b, int len);

	/**
	 * read
	 * @param b
	 * @return
	 */
	abstract public int read(byte[] b);

	/**
	 * setIP
	 * @param ip
	 * @param subnetmask
	 */
	public void setIP(String ip, String subnetmask) {
		try {
			this._ip = InetAddress.getByName(ip).getAddress();
		}
		catch( UnknownHostException ex ) {

		}
	}

	/**
	 * getIPBytes
	 * @return
	 */
	public byte[] getIPBytes() {
		return this._ip;
	}
}
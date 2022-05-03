/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.tuntap;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TunTapLinux
 * @author FantaBlueMystery
 */
public class TunTapLinux extends TunTap {

	/**
	 * Static
	 */
	static {
		try {
			TunTap.loadLib("clib/libTunTapLinux.so", "clib/libTunTapLinux64.so", "clib/libTunTapLinuxMips.so");
		}
		catch( Throwable e ) {
			Logger.getLogger("").log(Level.SEVERE, "Could not load libTunTapLinux.so", e);
		}
	}

	/**
	 * fd
	 */
	private int _fd;

	/**
	 * dev
	 */
    private String _dev;

	/**
	 * TunTapLinux
	 * @throws Exception
	 */
	public TunTapLinux() throws Exception {
		int resulte = this.openTun();

        if( 1 == resulte ) {
			throw new Exception("Could not open '/dev/net/tun!'\n" +
				"Please run this application as root.");
		}

		Logger.getLogger("").log(Level.INFO,
			"libTunTapLinux openTun: " + Integer.toString(resulte) +
			" FD: " + Integer.toString(this._fd) + " DEV: " + this._dev);
    }

	/**
	 * getDev
	 * @return
	 */
	@Override
	public String getDev() {
        return this._dev;
    }

	/**
	 * openTun
	 * @return
	 */
	private native int openTun();

	/**
	 * close
	 */
	@Override
    public native void close();

	/**
	 * write
	 * @param b
	 * @param len
	 */
	@Override
    public native void write(byte[] b, int len);

	/**
	 * read
	 * @param b
	 * @return
	 */
	@Override
    public native int read(byte[] b);

	/**
	 * setIP
	 * @param ip
	 * @param subnetmask
	 */
	@Override
	public void setIP(String ip, String subnetmask) {
		super.setIP(ip, subnetmask);

    	try {
    		Process p = Runtime.getRuntime().exec("ifconfig " + this._dev +
				" " + ip + " netmask " + subnetmask);

			Logger.getLogger("").log(Level.INFO, "IP set successfully ({0})", p.waitFor());
    	}
		catch( IOException | InterruptedException e ) {
			Logger.getLogger("").log(Level.WARNING, "Could not set IP!", e);
    	}
    }

	/**
	 * _errorLog
	 * @param message
	 */
	@Override
	protected void _errorLog(String message) {
		Logger.getLogger("").log(Level.SEVERE, "TunTapLinux: {0}", message);
	}
}

/**
 * MyonVPN
 *
 * @author FantaBlueMystery
 * @copyright 2020 by FantaBlueMystery & KeRn
 * @license http://opensource.org/licenses/lgpl-license.php LGPL - GNU Lesser General Public License
 */
package com.myonvpn.network;

import com.myonvpn.dump.HexDump;
import com.myonvpn.tuntap.TunTap;

/**
 * VPNConnector
 * @author FantaBlueMystery
 */
public class VPNConnector implements Runnable {

	/**
	 * tun tap
	 */
	protected TunTap _tuntap;

	/**
	 * thread
	 */
	protected Thread _thread;

	/**
	 * VPNConnector
	 * @throws Exception
	 */
	public VPNConnector() throws Exception {
		this._tuntap = TunTap.createTunTap();

		this._thread = new Thread(this, "VPNConnector");
		this._thread.start();
	}

	/**
	 * getTunTap
	 * @return
	 */
	public TunTap getTunTap() {
		return this._tuntap;
	}

	/**
	 * receive
	 * @param packet
	 */
	public void receive(byte[] packet) {
		this._tuntap.write(packet, packet.length);
	}

	/**
	 * run
	 */
	@Override
	public void run() {
		byte[] buffer = new byte[2048];

		while( true ) {
			int len = this._tuntap.read(buffer);

			if( len >= 12 ) {
				byte[] packet = new byte[len];

				System.arraycopy(buffer, 0, packet, 0, len);

				// test
				HexDump.dump(packet);
			}
		}
	}
}
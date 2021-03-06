/*******************************************************************************
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc, Eolos IT Corp and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */
package org.restcomm.sbc.bo;

import java.net.InetSocketAddress;

import javax.servlet.sip.Address;
import javax.servlet.sip.ServletParseException;

import org.apache.log4j.Logger;
import org.restcomm.sbc.ConfigurationCache;
import org.restcomm.sbc.managers.NetworkManager;

/**
 * @author  ocarriles@eolos.la (Oscar Andres Carriles)
 * @date    27 jul. 2016 23:58:42
 * @class   Connector.java
 *
 */
public class Connector {
	

	private Sid sid;
	private Sid accountSid;
	private int port;
	private State state;
	private Transport transport;
	private String point;
	private InetSocketAddress outboundInterface;
	private static transient Logger LOG = Logger.getLogger(Connector.class);
	public Connector(final Sid sid, final Sid accountSid, final int port, final Transport transport, final String point, final State state) {
		if(LOG.isInfoEnabled())
			LOG.info("Building Connector on NetworkPoint "+point+" port "+port+" transport "+transport+" state "+state);
		this.sid = sid;
		this.port = port;
		this.state = state;
		this.transport = transport;
		this.point = point;
		this.accountSid = accountSid;
		this.outboundInterface = new InetSocketAddress(NetworkManager.getIpAddress(point), port);
		
	}
	
	public Connector(final int port, final Transport transport, final String point, final State state) {
		this.port = port;
		this.state = state;
		this.transport = transport;
		this.point = point;
		this.outboundInterface = new InetSocketAddress(NetworkManager.getIpAddress(point), port);

		
	}
	
	public static Builder builder() {
        return new Builder();
    }
    
    public enum Transport {
        UDP("UDP"), TCP("TCP"), SCTP("SCTP"), TLS("TLS"), WS("WS"), WSS("WSS");

        private final String text;

        private Transport(final String text) {
            this.text = text;
        }

        public static Transport getValueOf(final String text) {
        	Transport[] values = values();
            for (final Transport value : values) {
                if (value.toString().equals(text)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(text + " is not a valid transport.");
        }

        @Override
        public String toString() {
            return text;
        }
    };
    
    public enum State {
        UP("UP"), DOWN("DOWN");

        private final String text;

        private State(final String text) {
            this.text = text;
        }

        public static State getValueOf(final String text) {
        	State[] values = values();
            for (final State value : values) {
                if (value.toString().equals(text)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(text + " is not a valid state.");
        }

        @Override
        public String toString() {
            return text;
        }
    };
    
	public int getPort() {
		return port;
	}

	public Transport getTransport() {
		return transport;
	}

	public State getState() {
		return state;
	}
	
	public String getPoint() {
		return point;
	}
	
	public String getHost() {
		return NetworkManager.getIpAddress(point);
	}
	
	public Sid getAccountSid() {
		return accountSid;
	}
	
	public Sid getSid() {
		return sid;
	}
	
	public Connector setPort(int port) {
		return new Connector(sid, accountSid, port, transport, point, state);
	}
	
	public Connector setTransport(Transport transPort) {
		return new Connector(sid, accountSid, port, transPort, point, state);
	}
	
	public Connector setState(State state) {
		return new Connector(sid, accountSid, port, transport, point, state);
	}
	
	public Connector setPoint(String point) {
		return new Connector(sid, accountSid, port, transport, point, state);
	}
	
	public Connector setAccountSid(Sid accountSid) {
		return new Connector(sid, accountSid, port, transport, point, state);
	}
	
	public static final class Builder {
		private Sid sid;
		private Sid accountSid;
		private int port;
		private State state;
		private Transport transport;
		private String point;
		

        private Builder() {
            super();
        }

        public Connector build() {
            return new Connector(sid, accountSid, port, transport, point, state);
        }

		public void setTransport(Transport transport) {
			this.transport = transport;
		}
		
		public void setState(State state) {
			this.state = state;
		}
		
		public void setPort(int port) {
			this.port = port;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		
		public void setAccountSid(Sid accountSid) {
			this.accountSid = accountSid;
		}
		
		public void setSid(Sid sid) {
			this.sid = sid;
		}
		
		
		
		
	}

	public String toPrint() {
		return "["+NetworkManager.getIpAddress(point)+":"+transport+":"+port+"] "+point+"/"+((outboundInterface!=null)?outboundInterface.toString():"lazy")+":"+state;
	}

	public InetSocketAddress getOutboundInterface() {
		if(this.outboundInterface==null)
			try {
			this.outboundInterface = new InetSocketAddress(NetworkManager.getIpAddress(point), port);
			} catch (Exception e) {
				LOG.error("Cannot assign an outbound Interface yet!");
			}
		return outboundInterface;
	}

	public Address buildAddress() throws ServletParseException {
		return ConfigurationCache.getSipFactory().createAddress("sip:"+NetworkManager.getIpAddress(point)+":"+port+";transport="+transport.name().toLowerCase());	
	}

}

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
 *******************************************************************************/
package org.restcomm.sbc.router.impl;

import java.util.List;
import javax.servlet.sip.SipURI;

import org.restcomm.sbc.managers.RoutingPolicyFactory;
import org.restcomm.sbc.router.RoutingPolicy;


/**
 * @author  ocarriles@eolos.la (Oscar Andres Carriles)
 * @date    17 mar. 2017 11:03:11
 * @class   HARoutingPolicy.java
 *
 */
public class HARoutingPolicy extends RoutingPolicy {
	
	private List<String> targets;

	public String getName() {
		return RoutingPolicyFactory.HA;
	}

	@Override
	public List<String> getTargets() {
		return targets;
	}

	@Override
	public void setTargets(List<String> targets) {
		this.targets = targets;
		
	}

	@Override
	public SipURI getCandidate() {
		return super.getURI(0);
	}

	
}

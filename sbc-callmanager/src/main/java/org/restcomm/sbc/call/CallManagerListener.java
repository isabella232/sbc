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
package org.restcomm.sbc.call;

import java.util.EventListener;

import org.restcomm.sbc.call.Call;

/**
 * @author  ocarriles@eolos.la (Oscar Andres Carriles)
 * @date    16 feb. 2017 8:20:43
 * @class   CallManagerListener.java
 *
 */
public interface CallManagerListener extends EventListener {
	
	void onCallCreated(Call call);
	void onCallIncoming(Call call);
	void onCallDialing(Call call);	
	void onCallAnswered(Call call);
	void onCallNotAnswered(Call call);
	void onCallNotFound(Call call);
	void onCallReleased(Call call);
	void onCallRejected(Call call);
	void onCallBusy(Call call);
	void onCallFailed(Call call);	
	void onCallCanceled(Call call);	
		
	
}

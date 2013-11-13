package main.java.com.jamobox.jamchatcore;

/**
 * JamChat_Core
 * Copyright (C) 2013 Pete Wicken
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see [http://www.gnu.org/licenses/].
 */

/**
 * This interface contains the protocol codes that should be
 * sent to the server, as well as the codes that will be received from
 * a server that correctly adheres to the protocol definition.
 *
 * @author Pete Wicken
 */
public abstract interface ServerCodes {

    /* From server */
    public static final String PING_RESPONSE = "PONG";

    /* To server */
    public static final String DISCONNECT = "DISCONNECT";
    public static final String USER_NAME = "USERNAME";

}

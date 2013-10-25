package main.java.com.jamobox.jamchat.server;

/**
 * JamChat
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
 * A server is any node that we can connect to using the client. Servers
 * manage client tracking and handle establishing connections between clients.
 * </p>
 * This is an interface as to future-proof in the event multiple server
 * connection support is implemented.
 *
 * @author Pete Wicken
 */
public abstract interface Server {

    /**
     * @return The unique server name.
     */
    public String getName();

    /**
     * @return The IP address or hostname of the server.
     */
    public String getAddress();

    /**
     * @return The port the server is listening on.
     */
    public int getPort();

    /**
     * Test if connection to server is alive or not.
     *
     * @return true if alive, false if not.
     */
    public boolean isAlive();

    /**
     * Send a ping to the server.
     */
    public void ping() throws Exception;

}


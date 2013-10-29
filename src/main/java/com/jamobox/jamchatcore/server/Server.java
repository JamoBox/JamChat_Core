package main.java.com.jamobox.jamchatcore.server;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A server is any node that we can connect to using the client. Servers
 * manage client tracking and handle establishing connections between clients.
 * </p>
 * This is an interface as to future-proof in the event multiple server
 * connection support is implemented.
 *
 * @author Pete Wicken
 */
public abstract class Server extends Socket {

    /**
     * @return The unique server name.
     */
    public abstract String getName();

    /**
     * @return The IP address or hostname of the server.
     */
    public abstract String getAddress();

    /**
     * @return The port the server is listening on.
     */
    public abstract int getPort();

    /**
     * Send a ping to the server.
     *
     * @return The response time.
     */
    public abstract long ping() throws IOException;

    /**
     * Get the input stream from the server.
     *
     * @return Reader from socket.
     * @throws IOException
     */
    public BufferedReader getServerReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}

package main.java.com.jamobox.jamchatcore;

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

import main.java.com.jamobox.jamchatcore.server.Server;

import java.io.IOException;
import java.net.Socket;

/**
 * Contains methods for establishing connection with a server
 * and sending required data.
 *
 * @author Pete Wicken
 */
public class Connector {

    private static Socket serverSock;

    public static Socket getSocket() {
        if(serverSock != null)
            return serverSock;
        else
            return null;
    }

    /**
     * Attempts to connect to the given Server.
     *
     * @param server The server to connect to
     * @return The connection status
     */
    public Status connect(Server server) {
        try {
            serverSock = new Socket(server.getAddress(), server.getPort());
            if (serverSock.isConnected())
                return Status.SERV_CONNECTED;
            else
                return Status.ERR_SERV_CONNECT;
        } catch (IOException e) {
            e.printStackTrace();
            return Status.ERR_SERV_CONNECT;
        }
    }

    /**
     * Attempts to disconnect from the given Server.
     *
     * @param server The server to disconnect from
     * @return The connection status
     */
    public Status disconnect(Server server) {
        if (serverSock != null)
            try {
                serverSock.close();
                return Status.SERV_DISCONNECTED;
            } catch (IOException e) {
                e.printStackTrace();
                return Status.ERR_SERV_DISCONNECT;
            }
        else
            return Status.ERR_NOSOCK;
    }

}


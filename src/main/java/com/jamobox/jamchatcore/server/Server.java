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

import main.java.com.jamobox.jamchatcore.ServerCodes;
import main.java.com.jamobox.jamchatcore.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
     * Creates a new Server object and attempts to connect to it.
     *
     * @param address The server address to connect to.
     * @param port The port to connect to on the server.
     * @throws IOException
     */
    public Server(String address, int port) throws IOException {
        super(address, port);
    }
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
     * //TODO: Make this work better
     *
     * @return The response time.
     */
    public long ping() throws IOException {
        long startTime = System.currentTimeMillis();
        sendMessage("PING");
        if (ServerReader.getCurrentLine() != null)
            while (!(ServerReader.getCurrentLine()[0].equalsIgnoreCase(ServerCodes.PING_RESPONSE)))
                if ((System.currentTimeMillis() - startTime) < 30000) // 30 second time-out
                    continue;
                else
                    return -1;
        return (System.currentTimeMillis() - startTime);
    }

    /**
     * Get the input stream from the server.
     *
     * @return Reader from socket.
     * @throws IOException
     */
    public BufferedReader getServerReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public PrintWriter getServerWriter() throws IOException {
        return new PrintWriter(this.getOutputStream());
    }

    /**
     * Send the server a message.
     *
     * @param message the message to be sent to the server
     */
    public void sendMessage(String message) {
        try {
            ServerWriter writer = new ServerWriter(this);
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Status getConnectStatus() {
        return this.isConnected() ? Status.SERV_CONNECTED : Status.ERR_SERV_CONNECT;
    }

    public Status disconnect(Server server) {
        if (this.isConnected())
            try {
                this.close();
                return Status.SERV_DISCONNECTED;
            } catch (IOException e) {
                e.printStackTrace();
                return Status.ERR_SERV_DISCONNECT;
            }
        else
            return Status.ERR_NOSOCK;
    }

}

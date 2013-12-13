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

    private BufferedReader reader;
    private PrintWriter writer;

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
     * Sends a ping message to the server and calculates the
     * response time. Uses a default timeout of 30 seconds before
     * returning escape code -1 for an unresponsive server.
     *
     * @return The response time.
     */
    public long ping() throws IOException {
        return ping(30000); // 30 seconds
    }

    //TODO: This method needs ALOT of work done on it to make it more effective/accurate.
    /**
     * Sends a ping message to the server and calculates the
     * response time. The request will timeout after the amount of time
     * taken is longer than the timeout limit; it will then return the escape
     * code -1 to signify an unresponsive server.
     *
     * @param timeout The amount of time (ms) until the request times out.
     * @return The response time.
     */
    public long ping(long timeout) throws IOException {
        long startTime = System.currentTimeMillis();
        sendMessage(ServerCodes.PING_REQUEST);
        if (ServerReader.getCurrentLine() != null)
            while (!(ServerReader.getCurrentLine()[0].equalsIgnoreCase(ServerCodes.PING_RESPONSE)))
                if ((System.currentTimeMillis() - startTime) < timeout)
                    continue;
                else
                    return -1;
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
        if (reader == null)
            reader = new BufferedReader(new InputStreamReader(this.getInputStream()));

        return reader;
    }
    /**
     * Get the output stream to the server.
     *

     * @return Writer to socket.
     * @throws IOException
     */
    public PrintWriter getServerWriter() throws IOException {
        if (writer == null)
            writer = new PrintWriter(this.getOutputStream());

        return writer;
    }

    /**
     * Attempts to send the server the given message. Messages
     * must always begin with their corresponding request code
     * so the server can identify the desired action.
     *
     * @param message the message to be sent to the server
     */
    public void sendMessage(String message) throws IOException {
        ServerWriter.write(message);
    }

    /**
     * @return The connection status of the server.
     */
    public Status getConnectStatus() {
        return this.isConnected() ? Status.SERV_CONNECTED : Status.ERR_SERV_CONNECT;
    }

    /**
     * Attempts to disconnect from the server. The disconnection is handled by
     * the server rather than the client. This method is preferred over forceDisconnect().
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        ServerWriter.write(ServerCodes.DISCONNECT);
    }

    /**
     * Forces a disconnection from the server. This differs from disconnect()
     * as the disconnection is handled on the client's side, as opposed to the server's
     * side. This should only be used in a case where disconnect does not suffice.
     */
    public void forceDisconnect() {
        try {
            this.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

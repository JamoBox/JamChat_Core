package test.java.serverconnect;

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

import main.java.com.jamobox.jamchatcore.Connector;
import main.java.com.jamobox.jamchatcore.server.Server;
import main.java.com.jamobox.jamchatcore.server.ServerReader;
import main.java.com.jamobox.jamchatcore.server.SocketIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ChatServer extends Server {

    private final String name = "ChatServer";
    private String address;
    private int port;
    private SocketIO out;
    private Connector connector;

    private final int timeout = 3000;

    public ChatServer(String address, int port) {
        setAddress(address);
        setPort(port);
        connector = new Connector();
        try {
            connector.connect(this);
            out = new SocketIO(Connector.getSocket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public long ping() throws IOException {
        long start = System.currentTimeMillis();
        out.write("PING");
        if (ServerReader.getCurrentLine() != null) {
            System.out.println(ServerReader.getCurrentLine()[0]);
            while (!(ServerReader.getCurrentLine()[0].equalsIgnoreCase("PONG")))
                if ((System.currentTimeMillis() - start) < 30000)
                    System.out.print(".");
                else
                    System.out.println("Ping timeout. Server unresponsive");
        }
        return (System.currentTimeMillis() - start);
    }

}

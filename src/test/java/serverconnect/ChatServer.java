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

import main.java.com.jamobox.jamchatcore.server.Server;
import main.java.com.jamobox.jamchatcore.server.ServerWriter;

import java.io.IOException;

public class ChatServer extends Server {

    private final String name = "ChatServer";
    private String address;
    private int port;
    private ServerWriter out;

    private final int timeout = 3000;

    public ChatServer(String address, int port) throws IOException {
        super(address, port);
        setAddress(address);
        setPort(port);
        out = new ServerWriter(this);
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

}

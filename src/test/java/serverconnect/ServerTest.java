package test.java.serverconnect;

import main.java.com.jamobox.jamchatcore.server.Server;
import main.java.com.jamobox.jamchatcore.server.ServerReader;

import java.io.IOException;

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

public class ServerTest {

    public static void main(String[] args) throws IOException {
        Server server = (Server) null;
        String address = "127.0.0.1";
        int port = 23239;

        try {
            server = new ChatServer(address, port);
        } catch (IOException e) {
            System.out.println("No JamChat Server running on "+address+":"+port+"");
            System.out.println("Exiting...");
            System.exit(1);
        }
        switch (server.getConnectStatus()) {
            case SERV_CONNECTED:
                System.out.println("Connected");

                Thread readThread = new Thread(new ServerReader(server));
                try {
                    server.sendMessage("USERNAME JamoBox");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println("Pinging "+server.getName()+"("+server.getAddress()+":"+server.getPort()+")");
                    long pingTime = server.ping();
                    System.out.println("Ping Response: "+pingTime);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ERR_NOSOCK:
                System.out.println("Null socket");
                break;
            case SERV_DISCONNECTED:
            case ERR_SERV_CONNECT:
            default:
                System.out.println("Connect failed");
                break;
        }

        server.disconnect();
    }

}


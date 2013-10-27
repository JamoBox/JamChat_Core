package main.java.com.jamobox.jamchat.test.main.serverconnect;

import main.java.com.jamobox.jamchat.Connector;
import main.java.com.jamobox.jamchat.Status;
import main.java.com.jamobox.jamchat.server.ChatServer;
import main.java.com.jamobox.jamchat.server.Server;

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

    public static void main(String[] args) {
        Status connectStat = Status.SERV_DISCONNECTED;

        Server server = new ChatServer("127.0.0.1", 23239);
        Connector serverConnector = new Connector();

        connectStat = serverConnector.connect(server);

        switch (connectStat) {
            case SERV_CONNECTED:
                System.out.println("connected");
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
    }

}


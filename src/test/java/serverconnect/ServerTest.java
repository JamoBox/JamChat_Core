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

/**
 * This class is designed to implement and test the methods used in the
 * main package. It also serves as an example for how clients could (and in some cases
 * <b>should</b> ) implement the JamChat Core library. For example, the flow of connection
 * to a server is defined here, where we send a username before attempting any other actions.
 *
 * @author Pete Wicken
 */

import main.java.com.jamobox.jamchatcore.ServerCodes;
import main.java.com.jamobox.jamchatcore.server.Server;
import main.java.com.jamobox.jamchatcore.server.ServerReader;

import java.io.IOException;
import java.util.Scanner;


public class ServerTest {

    public static void main(String[] args) throws IOException {
        Server server = (Server) null;
        String address = "127.0.0.1";
        String username = "JamoBox";
        int port = 23239;

        try {
            server = new ChatServer(address, port);
        } catch (IOException e) {
            System.out.printf("No JamChat Server running on %s:%s\n", address, port);
            System.out.println("Exiting...");
            System.exit(1);
        }
        switch (server.getConnectStatus()) {
            case SERV_CONNECTED:
                System.out.println("Connected");

                Thread readThread = new Thread(new ServerReader(server));
                try {
                    server.sendMessage(ServerCodes.USER_NAME+" "+username);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*try {
                    System.out.printf("Pinging %s (%s:%s)\n", server.getName(), server.getAddress(), server.getPort());
                    long pingTime = server.ping();
                    System.out.println("Ping Response: "+pingTime);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
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

        boolean readInput = true;
        Scanner in = new Scanner(System.in);
        while (readInput) {
            String s = in.nextLine();
            if (s.equalsIgnoreCase("exit")) {
                readInput = false;
            } else {
                server.sendMessage(s);
                ServerReader.getCurrentLine();
            }

        }

        server.disconnect();
    }

}


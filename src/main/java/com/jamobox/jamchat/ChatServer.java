package main.java.com.jamobox.jamchat;

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

//NOTE: This *could* be moved to the GUI instead of being in the core
public class ChatServer implements Server {

    private final String name = "ChatServer";
    private String address;
    private int port;

    public ChatServer(String address) {
        setAddress(address);
        setPort(23239); // Default port for JamChat
    }

    public ChatServer(String address, int port) {
        setAddress(address);
        setPort(port);
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
        return null;
    }

    public int getPort() {
        return 0;
    }

    public void ping() throws Exception {
        //TODO: send a ping
    }

    public boolean isAlive() {
        try {
            ping();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

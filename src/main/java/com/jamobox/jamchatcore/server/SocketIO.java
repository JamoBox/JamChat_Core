package main.java.com.jamobox.jamchatcore.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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

public class SocketIO {

    private Socket socket;
    private PrintWriter out;
    private DataInputStream ioStream;

    public SocketIO(Socket socket) throws IOException {
        this.socket = socket;

        out = new PrintWriter(socket.getOutputStream());
        ioStream = new DataInputStream(socket.getInputStream());
    }

    public void write(String s) throws IOException {
        out.write(s);
        out.flush();
    }

}


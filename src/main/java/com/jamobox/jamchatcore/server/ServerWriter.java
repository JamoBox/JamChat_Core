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

import java.io.IOException;
import java.io.PrintWriter;


public class ServerWriter {

    private Server server;
    private PrintWriter out;

    public ServerWriter(Server server) throws IOException {
        this.server = server;
        out = new PrintWriter(server.getServerWriter());
    }

    public void write(String s) throws IOException {
        out.write(s);
        out.flush();
    }

}

package main.java.com.jamobox.jamchatcore.server;

/**
 * JamChat_Core
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

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Handles reading server input streams
 * TODO: Create a cleaner way of getting the input. Perhaps a temp file may be useful.
 *
 * @author Pete Wicken
 */
public class ServerReader implements Runnable {

    private static String[] currentLine = null;
    private Server server;

    public ServerReader(Server server) {
        this.server = server;
    }

    public static String[] getCurrentLine() {
        return currentLine;
    }

    public void run() {
        try {
            BufferedReader reader = server.getServerReader();
            String[] input;
            while ((input = reader.readLine().split(" ")) != null) {
                currentLine = input;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


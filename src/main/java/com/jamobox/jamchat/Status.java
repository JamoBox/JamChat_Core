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

/**
 * Contains enumerated values to determine connection status.
 *
 * @author Pete Wicken
 */
public enum Status {

    SERV_CONNECTED,
    SERV_DISCONNECTED,
    ERR_SERV_CONNECT,
    ERR_SERV_DISCONNECT,
    ERR_NOSOCK

}

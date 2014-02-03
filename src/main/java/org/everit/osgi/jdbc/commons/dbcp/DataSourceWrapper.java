/**
 * This file is part of Pooled DatataSource Components.
 *
 * Pooled DatataSource Components is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Pooled DatataSource Components is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Pooled DatataSource Components.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.jdbc.commons.dbcp;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DataSourceWrapper implements DataSource {

    private final DataSource wrapped;

    public DataSourceWrapper(DataSource wrapped) {
        this.wrapped = wrapped;
    }

    public PrintWriter getLogWriter() throws SQLException {
        return wrapped.getLogWriter();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrapped.unwrap(iface);
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        wrapped.setLogWriter(out);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrapped.isWrapperFor(iface);
    }

    public Connection getConnection() throws SQLException {
        return new ConnectionWrapper(wrapped.getConnection());
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        wrapped.setLoginTimeout(seconds);
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return new ConnectionWrapper(wrapped.getConnection(username, password));
    }

    public int getLoginTimeout() throws SQLException {
        return wrapped.getLoginTimeout();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return wrapped.getParentLogger();
    }

}

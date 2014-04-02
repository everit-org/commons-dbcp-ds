/**
 * This file is part of Everit - Commons DBCP Components.
 *
 * Everit - Commons DBCP Components is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Commons DBCP Components is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Commons DBCP Components.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.jdbc.commons.dbcp.internal;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;

public class BasicSimpleDataSource extends BasicDataSource {

    private DataSource nonPoolingDataSource;

    /**
     * Creating a connection factory based on the provided DataSource.
     */
    @Override
    protected ConnectionFactory createConnectionFactory() throws SQLException {
        return new DataSourceConnectionFactory(nonPoolingDataSource);
    }

    public void setNonPoolingDataSource(DataSource nonPoolDataSource) {
        this.nonPoolingDataSource = nonPoolDataSource;
    }
}

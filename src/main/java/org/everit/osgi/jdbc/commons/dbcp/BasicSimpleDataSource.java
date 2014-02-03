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
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DataSourceConnectionFactory;

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

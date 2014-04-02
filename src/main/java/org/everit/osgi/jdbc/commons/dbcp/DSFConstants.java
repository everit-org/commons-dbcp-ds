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
package org.everit.osgi.jdbc.commons.dbcp;

public class DSFConstants {

    public static final String PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED = "accessToUnderlyingConnectionAllowed";

    public static final String PROP_CONNECTION_INIT_SQLS = "connectionInitSqls";

    public static final String PROP_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";

    public static final String PROP_ENABLE_AUTO_COMMIT_ON_RETURN = "enableAutoCommitOnReturn";

    public static final String PROP_DEFAULT_CATALOG = "defaultCatalog";

    public static final String PROP_DEFAULT_READ_ONLY = "defaultReadOnly";

    public static final String PROP_DEFAULT_QUERY_TIMEOUT = "defaultQueryTimeout";

    public static final String PROP_DEFAULT_TRANSACTION_ISOLATION = "defaultTransactionIsolation";

    public static final String PROP_INITIAL_SIZE = "initialSize";

    public static final String PROP_LOG_ABANDONED = "logAbandoned";

    public static final String PROP_MAX_IDLE = "maxIdle";

    public static final String PROP_MAX_OPEN_PREPARED_STATEMENTS = "maxOpenPreparedStatements";

    public static final String PROP_MAX_TOTAL = "maxTotal";

    public static final String PROP_MAX_WAIT_MILLIS = "maxWaitMillis";

    public static final String PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";

    public static final String PROP_MIN_IDLE = "minIdle";

    public static final String PROP_NUM_TESTS_PER_EVICTION_RUN = "numTestsPerEvictionRun";

    public static final String PROP_POOL_PREPARED_STATEMENTS = "poolPreparedStatements";

    public static final String PROP_REMOVE_ABANDONED_ON_BORROW = "removeAbandonedOnBorrow";

    public static final String PROP_REMOVE_ABANDONED_ON_MAINTENANCE = "removeAbandonedOnMaintenance";

    public static final String PROP_REMOVE_ABANDONED_TIMEOUT = "removeAbandonedTimeout";

    public static final String PROP_TEST_ON_CREATE = "testOnCreate";

    public static final String PROP_TEST_ON_BORROW = "testOnBorrow";

    public static final String PROP_TEST_ON_RETURN = "testOnReturn";

    public static final String PROP_TEST_WHILE_IDLE = "testWhileIdle";

    public static final String PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "timeBetweenEvictionRunsMillis";

    public static final String PROP_VALIDATION_QUERY = "validationQuery";

    public static final String PROP_VALIDATION_QUERY_TIMEOUT = "validationQueryTimeout";

    public static final String VALUE_READ_COMMITED = "readCommited";

    public static final String VALUE_READ_UNCOMMITTED = "readUncommited";

    public static final String VALUE_REPEATABLE_READ = "repeatableRead";

    public static final String VALUE_SERIALIZABLE = "serializable";

    public static final String VALUE_TRANSACTION_NONE = "none";

    public static final String COMPONENT_NAME_DATASOURCE = "org.everit.osgi.jdbc.commons.dbcp.DataSource";

    public static final String COMPONENT_NAME_MANAGED_DATASOURCE = "org.everit.osgi.jdbc.commons.dbcp.ManagedDataSource";

    public static final String BOOLEAN_OPT_TRUE = "true";

    public static final String BOOLEAN_OPT_FALSE = "false";

    public static final String BOOLEAN_OPT_DEFAULT = "default";

    public static final String PROP_XA_DATASOURCE_TARGET = "xaDataSource.target";

    public static final String PROP_TRANSACTION_MANAGER_TARGET = "transactionManager.target";

    public static final String PROP_CACHE_STATE = "cacheState";

    public static final String PROP_LIFO = "lifo";

    public static final String PROP_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = "softMinEvictableIdleTimeMillis";

    public static final String PROP_MAX_CONN_LIFETIME_MILLIS = "maxConnLifetimeMillis";

    public static final String PROP_JMX_NAME = "jmxName";

    public static final String PROP_ROLLBACK_ON_RETURN = "rollbackOnReturn";

    public static final String PROP_ABANDONED_USAGE_TRACKING = "abandonedUsageTracking";
}

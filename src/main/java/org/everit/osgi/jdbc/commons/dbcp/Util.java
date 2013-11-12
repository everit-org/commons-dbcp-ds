package org.everit.osgi.jdbc.commons.dbcp;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

public final class Util {
    
    public static final String PROP_LOG_ABANDONED = "logAbandoned";
    
    public static final String PROP_REMOVE_ABANDONED_TIMEOUT = "removeAbandonedTimeout";
    
    public static final String PROP_REMOVE_ABANDONED = "removeAbandoned";
    
    public static final String PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED = "accessToUnderlyingConnectionAllowed";
    
    public static final String PROP_CONNECTION_INIT_SQLS = "connectionInitSqls";

    public static final String PROP_VALIDATION_QUERY_TIMEOUT = "validationQueryTimeout";

    public static final String PROP_VALIDATION_QUERY = "validationQuery";

    public static final String PROP_TEST_WHILE_IDLE = "testWhileIdle";

    public static final String PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";

    public static final String PROP_NUM_TESTS_PER_EVICTION_RUN = "numTestsPerEvictionRun";

    public static final String PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "timeBetweenEvictionRunsMillis";

    public static final String PROP_TEST_ON_RETURN = "testOnReturn";

    public static final String PROP_TEST_ON_BORROW = "testOnBorrow";

    public static final String PROP_MAX_OPEN_PREPARED_STATEMENTS = "maxOpenPreparedStatements";

    public static final String PROP_POOL_PREPARED_STATEMENTS = "poolPreparedStatements";

    public static final String PROP_MAX_WAIT = "maxWait";

    public static final String PROP_INITIAL_SIZE = "initialSize";

    public static final String PROP_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";

    public static final String PROP_DEFAULT_READ_ONLY = "defaultReadOnly";

    public static final String VALUE_TRANSACTION_NONE = "none";

    public static final String VALUE_READ_COMMITED = "readCommited";

    public static final String VALUE_READ_UNCOMMITTED = "readUncommited";

    public static final String VALUE_REPEATABLE_READ = "repeatableRead";

    public static final String VALUE_SERIALIZABLE = "serializable";

    public static final String VALUE_TRANSACTION_READ_COMMITED = "read_commited";

    public static final String PROP_DEFAULT_TRANSACTION_ISOLATION = "defaultTransactionIsolation";

    public static final String PROP_DEFAULT_CATALOG = "defaultCatalog";

    public static final String PROP_MAX_ACTIVE = "maxActive";

    public static final String PROP_MAX_IDLE = "maxIdle";

    public static final String PROP_MIN_IDLE = "minIdle";

    private Util() {
    }
}

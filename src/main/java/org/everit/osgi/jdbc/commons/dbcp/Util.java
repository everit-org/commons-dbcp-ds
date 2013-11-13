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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

public final class Util {

    public static final String PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED = "accessToUnderlyingConnectionAllowed";

    public static final String PROP_CONNECTION_INIT_SQLS = "connectionInitSqls";

    public static final String PROP_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";

    public static final String PROP_DEFAULT_CATALOG = "defaultCatalog";

    public static final String PROP_DEFAULT_READ_ONLY = "defaultReadOnly";

    public static final String PROP_DEFAULT_TRANSACTION_ISOLATION = "defaultTransactionIsolation";

    public static final String PROP_INITIAL_SIZE = "initialSize";

    public static final String PROP_LOG_ABANDONED = "logAbandoned";

    public static final String PROP_MAX_ACTIVE = "maxActive";

    public static final String PROP_MAX_IDLE = "maxIdle";

    public static final String PROP_MAX_OPEN_PREPARED_STATEMENTS = "maxOpenPreparedStatements";

    public static final String PROP_MAX_WAIT = "maxWait";

    public static final String PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";

    public static final String PROP_MIN_IDLE = "minIdle";

    public static final String PROP_NUM_TESTS_PER_EVICTION_RUN = "numTestsPerEvictionRun";

    public static final String PROP_POOL_PREPARED_STATEMENTS = "poolPreparedStatements";

    public static final String PROP_REMOVE_ABANDONED = "removeAbandoned";

    public static final String PROP_REMOVE_ABANDONED_TIMEOUT = "removeAbandonedTimeout";

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

    public static void applyPropertiesOnBasicDataSource(final BasicDataSource basicDataSource,
            final Map<String, Object> componentProperties) {

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED,
                Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_DEFAULT_AUTO_COMMIT, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_DEFAULT_CATALOG, String.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_DEFAULT_READ_ONLY, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_INITIAL_SIZE, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_LOG_ABANDONED, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MAX_ACTIVE, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MAX_IDLE, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MAX_OPEN_PREPARED_STATEMENTS, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MAX_WAIT, Long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS, Long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_MIN_IDLE, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_NUM_TESTS_PER_EVICTION_RUN, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_POOL_PREPARED_STATEMENTS, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_REMOVE_ABANDONED, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_REMOVE_ABANDONED_TIMEOUT, Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_TEST_ON_BORROW, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_TEST_ON_RETURN, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_TEST_WHILE_IDLE, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS, Long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_VALIDATION_QUERY, String.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, PROP_VALIDATION_QUERY_TIMEOUT, Integer.class);

        Object connectionInitSqlsObject = componentProperties.get(PROP_CONNECTION_INIT_SQLS);
        if (connectionInitSqlsObject != null) {
            if (!(connectionInitSqlsObject instanceof Collection)) {
                throw new RuntimeException("Expected type of " + PROP_CONNECTION_INIT_SQLS + "' is Collection but "
                        + connectionInitSqlsObject.getClass() + " provided");
            }

            @SuppressWarnings("unchecked")
            Collection<String> connectionInitSqls = (Collection<String>) connectionInitSqlsObject;
            basicDataSource.setConnectionInitSqls(connectionInitSqls);
        }

        Object transactionIsolationObject = componentProperties.get(PROP_DEFAULT_TRANSACTION_ISOLATION);
        if (transactionIsolationObject != null) {
            if (VALUE_READ_COMMITED.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            } else if (VALUE_READ_UNCOMMITTED.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            } else if (VALUE_REPEATABLE_READ.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            } else if (VALUE_SERIALIZABLE.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            } else if (VALUE_TRANSACTION_NONE.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_NONE);
            } else {
                throw new RuntimeException("Unknown value '" + transactionIsolationObject + "' for the property key '"
                        + PROP_DEFAULT_TRANSACTION_ISOLATION + " during datasource configuration.");
            }

        }
    }

    private static String convertPropNameToMethodName(final String propName) {
        return "set" + Character.toUpperCase(propName.charAt(0)) + propName.substring(1);
    }

    private static Class<?> convertToParamType(Class<?> nonPrimitiveType) {
        if (nonPrimitiveType.isAssignableFrom(Boolean.class)) {
            return boolean.class;
        }
        if (nonPrimitiveType.isAssignableFrom(Integer.class)) {
            return int.class;
        }
        if (nonPrimitiveType.isAssignableFrom(Long.class)) {
            return long.class;
        }
        return nonPrimitiveType;
    }

    private static final <T> void setPropertyIfNotNull(final BasicDataSource dataSource,
            final Map<String, Object> properties, String propKey, Class<T> propType) {

        Object propValueObject = properties.get(propKey);
        if (propValueObject == null) {
            return;
        }
        if (!propType.isAssignableFrom(propValueObject.getClass())) {
            throw new RuntimeException("Expected type of property '" + propKey + "' is '" + propType
                    + "' while the component property has the type '" + propValueObject.getClass() + "'");
        }

        Class<?> paramType = convertToParamType(propType);
        String methodName = convertPropNameToMethodName(propKey);
        try {
            Method method = dataSource.getClass().getMethod(methodName, paramType);
            method.invoke(dataSource, propValueObject);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Error during setting property '" + propKey + "' of datasource", e);
        } catch (SecurityException e) {
            throw new RuntimeException("Error during setting property '" + propKey + "' of datasource", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error during setting property '" + propKey + "' of datasource", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error during setting property '" + propKey + "' of datasource", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Error during setting property '" + propKey + "' of datasource", e);
        }
    }

    public static void addReferenceIdsToServiceProperties(String prefix, Map<String, Object> referenceProps,
            Dictionary<String, Object> serviceProps) {

        Object serviceId = referenceProps.get("service.id");
        if (serviceId != null) {
            serviceProps.put(prefix + ".service.id", serviceId);
        }
        
        Object servicedPid = referenceProps.get("service.pid");
        if (servicedPid != null) {
            serviceProps.put(prefix + ".service.pid", servicedPid);
        }
    }

    private Util() {
    }
}

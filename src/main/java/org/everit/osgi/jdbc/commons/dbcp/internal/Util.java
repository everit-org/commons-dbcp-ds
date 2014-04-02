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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.everit.osgi.jdbc.commons.dbcp.DSFConstants;

public final class Util {

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

    public static void applyPropertiesOnBasicDataSource(final BasicDataSource basicDataSource,
            final Map<String, Object> componentProperties) {

        setPropertyIfNotNull(basicDataSource, componentProperties,
                DSFConstants.PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_DEFAULT_AUTO_COMMIT, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_DEFAULT_CATALOG, String.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_DEFAULT_READ_ONLY, Boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_DEFAULT_QUERY_TIMEOUT,
                Integer.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_INITIAL_SIZE, int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_LOG_ABANDONED, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MAX_TOTAL, int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MAX_IDLE, int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MAX_OPEN_PREPARED_STATEMENTS,
                int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MAX_WAIT_MILLIS, long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS,
                long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MIN_IDLE, int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_NUM_TESTS_PER_EVICTION_RUN,
                int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_POOL_PREPARED_STATEMENTS,
                boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_REMOVE_ABANDONED_ON_BORROW,
                boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_REMOVE_ABANDONED_ON_MAINTENANCE,
                boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_REMOVE_ABANDONED_TIMEOUT,
                int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_TEST_ON_BORROW, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_TEST_ON_RETURN, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_TEST_WHILE_IDLE, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
                long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_VALIDATION_QUERY, String.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_VALIDATION_QUERY_TIMEOUT,
                int.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_LIFO, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_CACHE_STATE, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_TEST_ON_CREATE, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties,
                DSFConstants.PROP_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS, long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_MAX_CONN_LIFETIME_MILLIS,
                long.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_JMX_NAME, String.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_ENABLE_AUTO_COMMIT_ON_RETURN,
                boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_ROLLBACK_ON_RETURN, boolean.class);

        setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.PROP_ABANDONED_USAGE_TRACKING,
                boolean.class);

        Object connectionInitSqlsObject = componentProperties.get(DSFConstants.PROP_CONNECTION_INIT_SQLS);
        if (connectionInitSqlsObject != null) {
            if (!(connectionInitSqlsObject instanceof Collection)) {
                throw new RuntimeException("Expected type of " + DSFConstants.PROP_CONNECTION_INIT_SQLS
                        + "' is Collection but "
                        + connectionInitSqlsObject.getClass() + " provided");
            }

            @SuppressWarnings("unchecked")
            Collection<String> connectionInitSqls = (Collection<String>) connectionInitSqlsObject;
            basicDataSource.setConnectionInitSqls(connectionInitSqls);
        }

        Object transactionIsolationObject = componentProperties.get(DSFConstants.PROP_DEFAULT_TRANSACTION_ISOLATION);
        if (transactionIsolationObject != null) {
            if (DSFConstants.VALUE_READ_COMMITED.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            } else if (DSFConstants.VALUE_READ_UNCOMMITTED.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            } else if (DSFConstants.VALUE_REPEATABLE_READ.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            } else if (DSFConstants.VALUE_SERIALIZABLE.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            } else if (DSFConstants.VALUE_TRANSACTION_NONE.equals(transactionIsolationObject)) {
                basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_NONE);
            } else {
                throw new RuntimeException("Unknown value '" + transactionIsolationObject + "' for the property key '"
                        + DSFConstants.PROP_DEFAULT_TRANSACTION_ISOLATION + " during datasource configuration.");
            }

        }
    }

    private static String convertPropNameToMethodName(final String propName) {
        return "set" + Character.toUpperCase(propName.charAt(0)) + propName.substring(1);
    }

    private static Class<?> convertToConfigValueType(Class<?> originalType) {
        if (boolean.class.isAssignableFrom(originalType)) {
            return Boolean.class;
        }
        if (int.class.isAssignableFrom(originalType)) {
            return Integer.class;
        }
        if (long.class.isAssignableFrom(originalType)) {
            return Long.class;
        }
        return originalType;
    }

    private static final <T> void setPropertyIfNotNull(final BasicDataSource dataSource,
            final Map<String, Object> properties, String propKey, Class<T> paramType) {

        Object propValueObject = properties.get(propKey);
        propValueObject = convertConfigurationValueIfNecessary(paramType, propValueObject);
        if (propValueObject == null) {
            return;
        }

        Class<?> propType = convertToConfigValueType(paramType);
        if (!propType.isAssignableFrom(propValueObject.getClass())) {
            throw new RuntimeException("Expected type of property '" + propKey + "' is '" + propType
                    + "' while the component property has the type '" + propValueObject.getClass() + "'");
        }

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

    private static Object convertConfigurationValueIfNecessary(Class<?> paramType, Object configValue) {
        if (configValue == null || (configValue instanceof String && ((String) configValue).trim().equals(""))) {
            return null;
        }
        if (paramType.isAssignableFrom(Integer.class)) {
            if (configValue instanceof String) {
                return Integer.valueOf((String) configValue);
            }
        } else if (paramType.isAssignableFrom(Long.class)) {
            if (configValue instanceof String) {
                return Long.valueOf((String) configValue);
            }
        } else if (paramType.isAssignableFrom(Boolean.class)) {
            if (configValue instanceof String) {
                if (configValue.equals(DSFConstants.BOOLEAN_OPT_DEFAULT)) {
                    return null;
                }
                return Boolean.valueOf((String) configValue);
            }
        }
        return configValue;
    }

    private Util() {
    }
}

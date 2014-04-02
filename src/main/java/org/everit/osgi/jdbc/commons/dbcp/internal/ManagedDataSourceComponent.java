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
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;

import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyOption;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.everit.osgi.jdbc.commons.dbcp.DSFConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

@org.apache.felix.scr.annotations.Properties({
        @Property(name = DSFConstants.PROP_XA_DATASOURCE_TARGET),
        @Property(name = DSFConstants.PROP_DEFAULT_AUTO_COMMIT, value = DSFConstants.BOOLEAN_OPT_DEFAULT, options = {
                @PropertyOption(value = "%default", name = DSFConstants.BOOLEAN_OPT_DEFAULT),
                @PropertyOption(value = "%true", name = DSFConstants.BOOLEAN_OPT_TRUE),
                @PropertyOption(value = "%false", name = DSFConstants.BOOLEAN_OPT_FALSE) }),
        @Property(name = DSFConstants.PROP_ENABLE_AUTO_COMMIT_ON_RETURN, boolValue = true),
        @Property(name = DSFConstants.PROP_ROLLBACK_ON_RETURN, boolValue = true),
        @Property(name = DSFConstants.PROP_DEFAULT_READ_ONLY, value = DSFConstants.BOOLEAN_OPT_DEFAULT, options = {
                @PropertyOption(value = "%default", name = DSFConstants.BOOLEAN_OPT_DEFAULT),
                @PropertyOption(value = "%true", name = DSFConstants.BOOLEAN_OPT_TRUE),
                @PropertyOption(value = "%false", name = DSFConstants.BOOLEAN_OPT_FALSE) }),
        @Property(name = DSFConstants.PROP_DEFAULT_TRANSACTION_ISOLATION, value = DSFConstants.VALUE_READ_COMMITED,
                options = {
                        @PropertyOption(value = "%transaction_isolation.none",
                                name = DSFConstants.VALUE_TRANSACTION_NONE),
                        @PropertyOption(value = "%transaction_isolation.read_uncommited",
                                name = DSFConstants.VALUE_READ_UNCOMMITTED),
                        @PropertyOption(value = "%transaction_isolation.read_commited",
                                name = DSFConstants.VALUE_READ_COMMITED),
                        @PropertyOption(value = "%transaction_isolation.repeatable_read",
                                name = DSFConstants.VALUE_REPEATABLE_READ),
                        @PropertyOption(value = "%transaction_isolation.serializable",
                                name = DSFConstants.VALUE_SERIALIZABLE) }),
        @Property(name = DSFConstants.PROP_DEFAULT_CATALOG),
        @Property(name = DSFConstants.PROP_MAX_TOTAL, intValue = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL),
        @Property(name = DSFConstants.PROP_MAX_IDLE, intValue = GenericObjectPoolConfig.DEFAULT_MAX_IDLE),
        @Property(name = DSFConstants.PROP_MIN_IDLE, intValue = GenericObjectPoolConfig.DEFAULT_MIN_IDLE),
        @Property(name = DSFConstants.PROP_INITIAL_SIZE, intValue = 0),
        @Property(name = DSFConstants.PROP_CACHE_STATE, boolValue = true),
        @Property(name = DSFConstants.PROP_LIFO, boolValue = BaseObjectPoolConfig.DEFAULT_LIFO),
        @Property(name = DSFConstants.PROP_MAX_CONN_LIFETIME_MILLIS, longValue = -1),
        @Property(name = DSFConstants.PROP_MAX_WAIT_MILLIS, longValue = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS),
        @Property(name = DSFConstants.PROP_POOL_PREPARED_STATEMENTS, boolValue = false),
        @Property(name = DSFConstants.PROP_MAX_OPEN_PREPARED_STATEMENTS,
                intValue = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL),
        @Property(name = DSFConstants.PROP_TEST_ON_CREATE, boolValue = false),
        @Property(name = DSFConstants.PROP_TEST_ON_BORROW, boolValue = true),
        @Property(name = DSFConstants.PROP_TEST_ON_RETURN, boolValue = false),
        @Property(name = DSFConstants.PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
                longValue = GenericObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS),
        @Property(name = DSFConstants.PROP_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
                longValue = BaseObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS),
        @Property(name = DSFConstants.PROP_NUM_TESTS_PER_EVICTION_RUN,
                intValue = GenericObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN),
        @Property(name = DSFConstants.PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS,
                longValue = GenericObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS),
        @Property(name = DSFConstants.PROP_TEST_WHILE_IDLE, boolValue = false),
        @Property(name = DSFConstants.PROP_VALIDATION_QUERY),
        @Property(name = DSFConstants.PROP_VALIDATION_QUERY_TIMEOUT, intValue = -1),
        @Property(name = DSFConstants.PROP_CONNECTION_INIT_SQLS, unbounded = PropertyUnbounded.VECTOR),
        @Property(name = DSFConstants.PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED, boolValue = false),
        @Property(name = DSFConstants.PROP_REMOVE_ABANDONED_ON_MAINTENANCE, boolValue = false),
        @Property(name = DSFConstants.PROP_REMOVE_ABANDONED_ON_BORROW, boolValue = false),
        @Property(name = DSFConstants.PROP_REMOVE_ABANDONED_TIMEOUT, intValue = 300),
        @Property(name = DSFConstants.PROP_LOG_ABANDONED, boolValue = false),
        @Property(name = DSFConstants.PROP_ABANDONED_USAGE_TRACKING, boolValue = false),
        @Property(name = DSFConstants.PROP_JMX_NAME),
        @Property(name = DSFConstants.PROP_TRANSACTION_MANAGER_TARGET) })
@Component(name = DSFConstants.COMPONENT_NAME_MANAGED_DATASOURCE, metatype = true, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE)
public class ManagedDataSourceComponent {

    @Reference(policy = ReferencePolicy.STATIC)
    private XADataSource xaDataSource;

    @Reference(policy = ReferencePolicy.STATIC)
    private TransactionManager transactionManager;

    private ServiceRegistration<DataSource> serviceRegistration;

    private BasicManagedDataSource managedDataSource = null;

    private Map<String, Object> tmServiceProperties = null;

    private Map<String, Object> xaDataSourceServiceProperties = null;

    @Activate
    public void activate(BundleContext bundleContext, Map<String, Object> componentProperties) {
        managedDataSource = new BasicManagedDataSource();
        managedDataSource.setXaDataSourceInstance(xaDataSource);
        managedDataSource.setTransactionManager(transactionManager);
        Util.applyPropertiesOnBasicDataSource(managedDataSource, componentProperties);

        Dictionary<String, Object> serviceProperties = new Hashtable<String, Object>(componentProperties);
        Util.addReferenceIdsToServiceProperties("xaDataSource", xaDataSourceServiceProperties, serviceProperties);
        Util.addReferenceIdsToServiceProperties("transactionManager", tmServiceProperties, serviceProperties);

        serviceRegistration = bundleContext.registerService(DataSource.class, managedDataSource, serviceProperties);
    }

    public void bindXaDataSource(XADataSource xaDataSource, Map<String, Object> xaDataSourceServiceProperties) {
        this.xaDataSource = xaDataSource;
        this.xaDataSourceServiceProperties = xaDataSourceServiceProperties;
    }

    public void bindTransactionManager(TransactionManager transactionManager, Map<String, Object> tmServiceProperties) {
        this.transactionManager = transactionManager;
        this.tmServiceProperties = tmServiceProperties;
    }

    @Deactivate
    public void deActivate(Map<String, Object> componentProperties) {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        if (managedDataSource != null) {
            try {
                managedDataSource.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error during closing data source at component "
                        + componentProperties.get("service.pid"));
            }
        }
    }
}

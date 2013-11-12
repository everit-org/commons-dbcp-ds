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

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyOption;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

@org.apache.felix.scr.annotations.Properties({
        @Property(name = Util.PROP_DEFAULT_AUTO_COMMIT, boolValue = true),

        @Property(name = Util.PROP_DEFAULT_READ_ONLY, boolValue = false),

        @Property(name = Util.PROP_DEFAULT_TRANSACTION_ISOLATION, value = Util.VALUE_READ_COMMITED, options = {
                @PropertyOption(value = "%transaction_isolation.none", name = Util.VALUE_TRANSACTION_NONE),
                @PropertyOption(value = "%transaction_isolation.read_uncommited", name = Util.VALUE_READ_UNCOMMITTED),
                @PropertyOption(value = "%transaction_isolation.read_commited", name = Util.VALUE_READ_COMMITED),
                @PropertyOption(value = "%transaction_isolation.repeatable_read", name = Util.VALUE_REPEATABLE_READ),
                @PropertyOption(value = "%transaction_isolation.serializable", name = Util.VALUE_SERIALIZABLE) }),

        @Property(name = Util.PROP_DEFAULT_CATALOG),

        @Property(name = Util.PROP_MAX_ACTIVE, intValue = GenericObjectPool.DEFAULT_MAX_ACTIVE),

        @Property(name = Util.PROP_MAX_IDLE, intValue = GenericObjectPool.DEFAULT_MAX_IDLE),

        @Property(name = Util.PROP_MIN_IDLE, intValue = GenericObjectPool.DEFAULT_MIN_IDLE),

        @Property(name = Util.PROP_INITIAL_SIZE, intValue = 0),

        @Property(name = Util.PROP_MAX_WAIT, longValue = GenericObjectPool.DEFAULT_MAX_WAIT),

        @Property(name = Util.PROP_POOL_PREPARED_STATEMENTS, boolValue = false),

        @Property(name = Util.PROP_MAX_OPEN_PREPARED_STATEMENTS, intValue = GenericKeyedObjectPool.DEFAULT_MAX_TOTAL),

        @Property(name = Util.PROP_TEST_ON_BORROW, boolValue = true),

        @Property(name = Util.PROP_TEST_ON_RETURN, boolValue = false),

        @Property(name = Util.PROP_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
                longValue = GenericObjectPool.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS),

        @Property(name = Util.PROP_NUM_TESTS_PER_EVICTION_RUN,
                intValue = GenericObjectPool.DEFAULT_NUM_TESTS_PER_EVICTION_RUN),

        @Property(name = Util.PROP_MIN_EVICTABLE_IDLE_TIME_MILLIS,
                longValue = GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS),

        @Property(name = Util.PROP_TEST_WHILE_IDLE, boolValue = false),

        @Property(name = Util.PROP_VALIDATION_QUERY),

        @Property(name = Util.PROP_VALIDATION_QUERY_TIMEOUT, longValue = -1),

        @Property(name = Util.PROP_CONNECTION_INIT_SQLS, unbounded = PropertyUnbounded.VECTOR),

        @Property(name = Util.PROP_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED, boolValue = false),

        @Property(name = Util.PROP_REMOVE_ABANDONED, boolValue = false),

        @Property(name = Util.PROP_REMOVE_ABANDONED_TIMEOUT, intValue = 300),

        @Property(name = Util.PROP_LOG_ABANDONED, boolValue = false) })
@Component(label = "Pooled-DataSource (Everit)", metatype = true, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE)
public class DataSourceComponent {

    @Property(name = "dataSource.target", label = "DataSource Filter",
            description = "Filter of the non-pooled DataSource OSGi service")
    @Reference(policy = ReferencePolicy.STATIC)
    private DataSource dataSource;

    private ServiceRegistration<DataSource> serviceRegistration;

    private Map<String, Object> dataSourceProperties;

    @Activate
    public void activate(BundleContext bundleContext, Map<String, Object> componentProperties) {

    }

    public void bindDataSource(DataSource dataSource, Map<String, Object> serviceProperties) {
        this.dataSource = dataSource;
        this.dataSourceProperties = serviceProperties;
    }

    @Deactivate
    public void deActivate() {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}

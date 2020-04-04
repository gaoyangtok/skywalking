/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.core.source;

import org.apache.skywalking.apm.network.common.v3.ServiceType;
import org.apache.skywalking.oap.server.core.UnexpectedException;

/**
 * Node type describe which kind of node of Service or Network address represents to.
 * <p>
 * The value comes from 'org.apache.skywalking.apm.network.language.agent.SpanLayer' at first place, but most likely it
 * will extend and be used directly from different sources, such as Mesh.
 */
public enum NodeType {
    /**
     * <code>Unknown = 0;</code>
     */
    Normal(0),
    /**
     * <code>Database = 1;</code>
     */
    Database(1),
    /**
     * <code>RPCFramework = 2;</code>
     */
    RPCFramework(2),
    /**
     * <code>Http = 3;</code>
     */
    Http(3),
    /**
     * <code>MQ = 4;</code>
     */
    MQ(4),
    /**
     * <code>Cache = 5;</code>
     */
    Cache(5),
    /**
     * <code>Browser = 6;</code>
     */
    Browser(6), UNRECOGNIZED(-1);

    private final int value;

    NodeType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static NodeType valueOf(int value) {
        switch (value) {
            case 0:
                return Normal;
            case 1:
                return Database;
            case 2:
                return RPCFramework;
            case 3:
                return Http;
            case 4:
                return MQ;
            case 5:
                return Cache;
            case -1:
                return UNRECOGNIZED;
            default:
                throw new UnexpectedException("Unknown NodeType value");
        }
    }

    /**
     * Right now, spanLayerValue is exact same as NodeType value.
     */
    public static NodeType fromSpanLayerValue(int spanLayerValue) {
        return valueOf(spanLayerValue);
    }

    public static NodeType fromRegisterServiceType(ServiceType serviceType) {
        switch (serviceType) {
            case normal:
                return Normal;
            case database:
                return Database;
            case cache:
                return Cache;
            case mq:
                return MQ;
            case browser:
                return Browser;
            default:
                return Normal;
        }
    }
}

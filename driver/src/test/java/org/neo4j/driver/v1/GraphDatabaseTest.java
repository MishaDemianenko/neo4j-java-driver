/**
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neo4j.driver.v1;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

import org.neo4j.driver.internal.DirectDriver;
import org.neo4j.driver.internal.RoutingDriver;
import org.neo4j.driver.v1.util.StubServer;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class GraphDatabaseTest
{
    @Test
    public void boltSchemeShouldInstantiateDirectDriver()
    {
        // Given
        URI uri = URI.create( "bolt://localhost:7687" );

        // When
        Driver driver = GraphDatabase.driver( uri );

        // Then
        assertThat( driver, instanceOf( DirectDriver.class ) );

    }

    @Ignore
    public void boltPlusDiscoverySchemeShouldInstantiateClusterDriver() throws IOException, InterruptedException, StubServer.ForceKilled
    {
        // Given
        StubServer server = StubServer.start( "../driver/src/test/resources/discover_servers.script" );
        URI uri = URI.create( "bolt+routing://localhost:7687" );

        // When
        Driver driver = GraphDatabase.driver( uri );

        // Then
        assertThat( driver, instanceOf( RoutingDriver.class ) );

        // Finally
        driver.close();
        assertThat( server.exitStatus(), equalTo( 0 ) );
    }

}
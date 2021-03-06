/*
 * Copyright 2017 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package azkaban.project;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.util.Map;
import org.junit.Test;


public class ProjectSpecTest {

  /**
   * Loads spec.yaml from test/resources and asserts properties
   */
  @Test
  public void testSpecLoad() throws Exception {
    final ClassLoader classLoader = getClass().getClassLoader();
    final File file = new File(classLoader.getResource("spec.yml").getFile());
    final ProjectSpec spec = new ProjectSpecLoader().load(file);

    assertEquals("1.0", spec.getVersion());

    final Map<String, URI> fetchMap = spec.getPreExec().getFetch();
    final URI sampleUri = new URI(
        "http://central.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar");
    assertEquals(sampleUri, fetchMap.get("lib"));
    assertEquals(sampleUri, fetchMap.get("path/to/foo"));
  }
}

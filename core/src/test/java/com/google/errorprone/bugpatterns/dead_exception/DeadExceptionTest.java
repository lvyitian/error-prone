/*
 * Copyright 2012 Google Inc. All Rights Reserved.
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

package com.google.errorprone.bugpatterns.dead_exception;

import com.google.errorprone.CompilationHelper;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author alexeagle@google.com (Alex Eagle)
 */
public class DeadExceptionTest {

  private CompilationHelper compilationHelper;

  @Before
  public void setUp() {
    compilationHelper = new CompilationHelper(new DeadException.Scanner());
  }

  @Test
  public void testPositiveCase() throws Exception {
    compilationHelper.assertCompileFailsDiffMessages(
        new File(this.getClass().getResource("PositiveCases.java").toURI()),
        "Did you mean 'throw new RuntimeException",
        "Did you mean",
        "Did you mean 'throw new InterruptedException",
        "Did you mean 'throw new ArithmeticException");
  }

  @Test public void testNegativeCase() throws Exception {
    compilationHelper.assertCompileSucceeds(
        new File(this.getClass().getResource("NegativeCases.java").toURI()));
  }
}

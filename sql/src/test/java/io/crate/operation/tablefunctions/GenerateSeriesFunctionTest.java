/*
 * Licensed to Crate under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Crate licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial
 * agreement.
 */

package io.crate.operation.tablefunctions;

import org.junit.Test;

public class GenerateSeriesFunctionTest extends AbstractTableFunctionsTest {

    private void generateSeries(int start, int stop, int step) {
        StringBuilder sb = new StringBuilder();
        int amountRepetitions = (stop-start)/step;
        int j = start;
        for(int i = 0; i <= amountRepetitions; i++) {
            sb.append(j+"\n");
            j += step;
        }
        assertExecute("generate_series("+start+"::integer,"+stop+"::integer,"+step+"::integer)", sb.toString());
    }

    private void generateSeries(long start, long stop, long step) {
        StringBuilder sb = new StringBuilder();
        int amountRepetitions = (int) ((stop-start)/step);
        long j = start;
        for(int i = 0; i <= amountRepetitions; i++) {
            sb.append(j+"\n");
            j += step;
        }
        assertExecute("generate_series("+start+"::long,"+stop+"::long,"+step+"::long)", sb.toString());
    }

    private void generateSeries(float start, float stop, float step) {
        StringBuilder sb = new StringBuilder();
        int amountRepetitions = (int) ((stop-start)/step);
        float j = start;
        for(int i = 0; i <= amountRepetitions; i++) {
            sb.append(j+"\n");
            j += step;
        }
        assertExecute("generate_series("+start+"::float,"+stop+"::float,"+step+"::float)", sb.toString());
    }

    private void generateSeries(double start, double stop, double step) {
        StringBuilder sb = new StringBuilder();
        int amountRepetitions = (int) ((stop-start)/step);
        double j = start;
        for(int i = 0; i <= amountRepetitions; i++) {
            sb.append(j+"\n");
            j += step;
        }
        assertExecute("generate_series("+start+"::double,"+stop+"::double,"+step+"::double)", sb.toString());
    }


    @Test
    public void testNormalCaseWithDefaultStep() throws Exception {
        generateSeries(0, 1000, 1);
        generateSeries((long)0, 1000, 1);
        generateSeries((float)0, 1000, 1);
        generateSeries((double)0, 1000, 1);
    }

    @Test
    public void testNormalCaseWithCustomStep() throws Exception {
        generateSeries(0, 1000, 5);
        generateSeries((long)0, 1000, 5);
        generateSeries((float)0, 1000, 5);
        generateSeries((double)0, 1000, 5);
    }

    @Test
    public void testBigNormalCase() throws Exception {
        generateSeries(0, Integer.MAX_VALUE, 10000);
        generateSeries((long)0, (long)Integer.MAX_VALUE*2, 10000);
        generateSeries((float)0, Integer.MAX_VALUE, 10000.1);
        generateSeries((double)0, Integer.MAX_VALUE*2, 10000.1);
    }

    @Test
    public void testNegativeCase() throws Exception {
        generateSeries(1000, 0, -1);
        generateSeries((long)1000, 0, -1);
        generateSeries((float)1000, 0, -1.1);
        generateSeries((double)1000, 0, -1.1);
    }

    @Test
    public void testNegativeCaseWithBiggerStep() throws Exception {
        generateSeries(1000, 0, -10);
        generateSeries((long)1000, 0, -10);
        generateSeries((float)1000, 0, -10.1f);
        generateSeries((double)1000, 0, -10.1);
    }

    @Test
    public void testBigNegativeCase() throws Exception {
        generateSeries(Integer.MAX_VALUE, 0, -10000);
        generateSeries((long)Integer.MAX_VALUE*2, 0, -10000);
        generateSeries((float)Integer.MAX_VALUE, 0, -10000.1f);
        generateSeries((double)Integer.MAX_VALUE*2, 0, -10000.1);
    }

    @Test
    public void testBiggerStepThanStop() throws Exception {
        generateSeries(0, 10, 100);
        generateSeries((long)0, 10, 100);
        generateSeries((float)0, 10, 100.1f);
        generateSeries((double)0, 10, 100.1);
    }
}
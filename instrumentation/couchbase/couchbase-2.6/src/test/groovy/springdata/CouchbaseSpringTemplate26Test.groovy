/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package springdata

import io.opentelemetry.auto.test.asserts.TraceAssert

class CouchbaseSpringTemplate26Test extends AbstractCouchbaseSpringTemplateTest {
  @Override
  void assertCouchbaseCall(TraceAssert trace, int index, Object name, String bucketName = null, Object parentSpan = null) {
    CouchbaseSpanUtil.assertCouchbaseCall(trace, index, name, bucketName, parentSpan)
  }
}

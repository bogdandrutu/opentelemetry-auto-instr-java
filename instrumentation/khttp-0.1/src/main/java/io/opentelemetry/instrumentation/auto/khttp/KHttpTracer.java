/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.auto.khttp;

import static io.opentelemetry.instrumentation.auto.khttp.KHttpHeadersInjectAdapter.SETTER;

import io.opentelemetry.context.propagation.TextMapPropagator.Setter;
import io.opentelemetry.instrumentation.api.tracer.HttpClientTracer;
import io.opentelemetry.instrumentation.auto.api.CallDepthThreadLocalMap;
import io.opentelemetry.instrumentation.auto.api.CallDepthThreadLocalMap.Depth;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import khttp.KHttp;
import khttp.responses.Response;

public class KHttpTracer extends HttpClientTracer<RequestWrapper, Map<String, String>, Response> {
  public static final KHttpTracer TRACER = new KHttpTracer();

  public Depth getCallDepth() {
    return CallDepthThreadLocalMap.getCallDepth(KHttp.class);
  }

  @Override
  protected String method(RequestWrapper requestWrapper) {
    return requestWrapper.method;
  }

  @Override
  protected URI url(RequestWrapper requestWrapper) throws URISyntaxException {
    return new URI(requestWrapper.uri);
  }

  @Override
  protected Integer status(Response response) {
    return response.getStatusCode();
  }

  @Override
  protected String requestHeader(RequestWrapper requestWrapper, String name) {
    return requestWrapper.headers.get(name);
  }

  @Override
  protected String responseHeader(Response response, String name) {
    return response.getHeaders().get(name);
  }

  @Override
  protected Setter<Map<String, String>> getSetter() {
    return SETTER;
  }

  @Override
  protected String getInstrumentationName() {
    return "io.opentelemetry.auto.khttp-0.1";
  }
}

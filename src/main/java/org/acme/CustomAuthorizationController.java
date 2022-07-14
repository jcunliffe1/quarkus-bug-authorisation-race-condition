package org.acme;

import io.quarkus.security.spi.runtime.AuthorizationController;
import io.quarkus.vertx.http.runtime.CurrentVertxRequest;
import io.vertx.core.http.HttpServerRequest;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

import io.vertx.ext.web.RoutingContext;

/**
 * @author FB9G9LT Hari
 * *
 * History
 * *
 * 17-Feb-2021 FB9G9LT Initial Version
 */
@Alternative
@Priority(Interceptor.Priority.LIBRARY_AFTER)
@ApplicationScoped
@ActivateRequestContext
public class CustomAuthorizationController extends AuthorizationController {

	@Inject
	CurrentVertxRequest currentVertxRequest;

	@Inject
	RoutingContext rc;

	@Override
	@ActivateRequestContext
	public boolean isAuthorizationEnabled() {
		final Instance<HttpServerRequest> select = CDI
				.current()
				.select(HttpServerRequest.class);
		HttpServerRequest HSR = select.isResolvable() ? select.get() : null;
		final Instance<javax.servlet.http.HttpServletRequest> select2 = CDI
				.current()
				.select(javax.servlet.http.HttpServletRequest.class);
		javax.servlet.http.HttpServletRequest HSR2 = select2.isResolvable() ? select2.get() : null;
		final Instance<org.jboss.resteasy.spi.HttpRequest> select3 = CDI
				.current()
				.select(org.jboss.resteasy.spi.HttpRequest.class);
		org.jboss.resteasy.spi.HttpRequest HSR3 = select3.isResolvable()
				? select3.get()
				: null;
		final Instance<javax.ws.rs.core.Request> select4 = CDI
				.current()
				.select(javax.ws.rs.core.Request.class);
		javax.ws.rs.core.Request HSR4 = select4.isResolvable()
				? select4.get()
				: null;
		final Instance<io.vertx.core.spi.observability.HttpRequest> select5 = CDI
				.current()
				.select(io.vertx.core.spi.observability.HttpRequest.class);
		io.vertx.core.spi.observability.HttpRequest HSR5 = select5.isResolvable()
				? select5.get()
				: null;
		return false;
	}
}

/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.softvision.example.springboot.logging;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.function.Predicate;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

/**
 * Base class for <code>Filter</code>s that perform logging operations before and after a request is processed.
 *
 * <p>Subclasses should override the <code>beforeRequest(HttpServletRequest, String)</code> and
 * <code>afterRequest(HttpServletRequest, String)</code> methods to perform the actual logging around the request.
 *
 * <p>Subclasses are passed the message to write to the log in the <code>beforeRequest</code> and
 * <code>afterRequest</code> methods. By default, only the URI of the request is logged. However, setting the
 * <code>includeQueryString</code> property to <code>true</code> will cause the query string of the request to be
 * included also. The payload (body) of the request can be logged via the <code>includePayload</code> flag. Note that
 * this will only log that which is read, which might not be the entire payload.
 *
 * <p>Prefixes and suffixes for the before and after messages can be configured using the
 * <code>beforeMessagePrefix</code>, <code>afterMessagePrefix</code>, <code>beforeMessageSuffix</code> and
 * <code>afterMessageSuffix</code> properties,
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @see #beforeRequest
 * @see #afterRequest
 * @since 1.2.5
 */
public  class RequestResponseLoggingFilter extends AbstractRequestResponseLoggingFilter {

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isDebugEnabled();
    }

    /**
     * Writes a log message before the request is processed.
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.debug(message);
    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.debug(message);
    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterResponse(HttpServletResponse response, String message) {
        logger.debug(message);
    }
}
package com.example.study.config

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import org.springframework.web.util.WebUtils
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.*

class ResponseRequestLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)
        val start = System.currentTimeMillis()
        filterChain.doFilter(requestWrapper, responseWrapper)
        val end = System.currentTimeMillis()

        com.example.study.log.logger.info{
            """
                "[REQUEST] ${request.method} - ${request.requestURI} ${responseWrapper.status} - ${(end - start) / 1000.0}
                "Headers : ${getHeaders(request)}
                "Request : ${getRequestBody(requestWrapper)}
                "Response : ${getResponseBody(responseWrapper)}
            """.trimIndent()
        }
    }

    private fun getHeaders(request: HttpServletRequest): Map<Any, Any> {
        val headerMap: MutableMap<Any, Any> = HashMap<Any, Any>()
        val headerArray: Enumeration<*> = request.headerNames
        while (headerArray.hasMoreElements()) {
            val headerName = headerArray.nextElement() as String
            headerMap[headerName] = request.getHeader(headerName)
        }
        return headerMap
    }

    private fun getRequestBody(request: ContentCachingRequestWrapper): String {
        val wrapper = WebUtils.getNativeRequest(
            request,
            ContentCachingRequestWrapper::class.java
        )
        if (wrapper != null) {
            val buf = wrapper.contentAsByteArray
            if (buf.size > 0) {
                try {
                    return String(buf, 0, buf.size, charset(wrapper.characterEncoding))
                } catch (e: UnsupportedEncodingException) {
                    return " - "
                }
            }
        }
        return " - "
    }

    @Throws(IOException::class)
    private fun getResponseBody(response: HttpServletResponse): String {
        var payload: String? = null
        val wrapper = WebUtils.getNativeResponse(
            response,
            ContentCachingResponseWrapper::class.java
        )
        if (wrapper != null) {
            val buf = wrapper.contentAsByteArray
            if (buf.size > 0) {
                payload = String(buf, 0, buf.size, charset(wrapper.characterEncoding))
                wrapper.copyBodyToResponse()
            }
        }
        return payload ?: " - "
    }
}

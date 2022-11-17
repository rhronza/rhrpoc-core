package cz.hronza.rhrpoc.core.common.utils;

import cz.hronza.rhrpoc.core.common.constant.RestConstant;
import cz.hronza.rhrpoc.core.common.exception.RhrPocExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

public class RequestUtils {
    private static final Logger log = LoggerFactory.getLogger(RhrPocExceptionHandler.class);

    private static HttpServletRequest initHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        } catch (Exception ex) {
            log.error("Can't initialize HttpServletRequest object, return null");
            return null;
        }
    }

    public static String getCorrelationId() {
        HttpServletRequest httpServletRequest = initHttpServletRequest();
        return getCorrelationId(httpServletRequest);
    }

    public static String getCorrelationId(HttpServletRequest httpServletRequest) {
        String correlationIdValue = getHeader(httpServletRequest, RestConstant.CORRELATION_ID_HEADER_NAME);
        return correlationIdValue == null ? getRandomUuid() : correlationIdValue;
    }

    private static String getHeader(HttpServletRequest request, String headerName) {
        return request != null ? request.getHeader(headerName) : null;
    }

    public static String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

}

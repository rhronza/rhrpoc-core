package cz.hronza.rhrpoc.core.api.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;

/**
 * HandlerInterceptor   je interface a má 3 metody: preHandle, postHandle, afterCompletion
 */

public class PocCoreHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(PocCoreHandlerInterceptor.class);

    /**
     * tyhle endpoity jsou chráněné:
     */
    private static final List<Predicate<String>> UNPROTECTED_PREDICATE_URI_ITEMS = Arrays.asList(
            uri -> uri.startsWith("/calculation"),
            uri -> uri.startsWith("/plus-days-for-offsetdatetime")
    );

    private final String basicAuthorizationApplication;

    public PocCoreHandlerInterceptor(String user, String password) {

        basicAuthorizationApplication = "Basic " + Base64
                .getEncoder()
                .encodeToString(String.format("%s:%s", user, password).getBytes()
                );

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (isUriProtected(request.getRequestURI()) && !basicAuthorizationApplication.equals(authorizationHeader)) {
            log.warn("authorization is off");
//            log.info("authorization is ON");
//            throw new PocAccessForbidenException();
        }
        return true;
    }

    private boolean isUriProtected(String inputUri) {
        return UNPROTECTED_PREDICATE_URI_ITEMS.stream().noneMatch(uriPredicate -> uriPredicate.test(inputUri));
    }
}

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
            uri -> uri.startsWith("/actuator"),
            uri -> uri.startsWith("/version")
    );

    private final String basicAuthorizationApplication;

    public PocCoreHandlerInterceptor(String user, String password) {
        //TODO vyhodit:
        log.info("{}:{}", user, password);

        basicAuthorizationApplication = "Basic " + Base64
                .getEncoder()
                .encodeToString(String.format("%s:%s", user, password).getBytes()
                );

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (isUriProtected(request.getRequestURI()) && !basicAuthorizationApplication.equals(authorizationHeader)) {
            // FIXME tohle odkomentovat aby fungovala Basic Auth:
            // throw new PocAccessForbidenException();
            return true;
        } else return true;
    }

    private boolean isUriProtected(String inputUri) {
        return UNPROTECTED_PREDICATE_URI_ITEMS.stream().noneMatch(uriPredicate -> uriPredicate.test(inputUri));
    }
}

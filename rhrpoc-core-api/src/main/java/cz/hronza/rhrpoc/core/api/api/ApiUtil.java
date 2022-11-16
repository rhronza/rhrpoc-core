package cz.hronza.rhrpoc.core.api.api;

import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public ApiUtil() {
    }

    public static void setSomeResponse(NativeWebRequest nativeWebRequest, String contentType, String someJson) {
        try {
            HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
            response.addHeader("Content-Type", contentType);
            response.getWriter().println(someJson);
            response.setCharacterEncoding("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

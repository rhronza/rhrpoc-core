package cz.hronza.rhrpoc.core.api.configuration;

import cz.hronza.rhrpoc.core.api.RhrPocCoreApiPackage;
import cz.hronza.rhrpoc.core.api.interceptor.PocCoreHandlerInterceptor;
import cz.hronza.rhrpoc.core.common.configuration.RhrPocCoreCommonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan(basePackageClasses = RhrPocCoreApiPackage.class)
@Import(RhrPocCoreCommonConfiguration.class)
public class RhrpocCoreApiConfiguration implements WebMvcConfigurer {

    private static final System.Logger logger = System.getLogger(RhrpocCoreApiConfiguration.class.getSimpleName());

    private final PocCoreHandlerInterceptor pocCoreHandlerInterceptor;

    public RhrpocCoreApiConfiguration(@Value("${poc.basic.auth.user}") String user,
                                      @Value("${poc.basic.auth.password}") String password) {
        this.pocCoreHandlerInterceptor = new PocCoreHandlerInterceptor(user, password);
        logger.log(System.Logger.Level.INFO, "interceptor initialized");
    }

    /**
     * add interceptor do WebMvc
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pocCoreHandlerInterceptor);
        logger.log(System.Logger.Level.INFO, "interceptor added");
    }
}

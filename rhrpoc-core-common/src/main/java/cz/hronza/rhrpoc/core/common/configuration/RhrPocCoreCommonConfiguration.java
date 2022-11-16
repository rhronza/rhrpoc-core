package cz.hronza.rhrpoc.core.common.configuration;

import cz.hronza.rhrpoc.core.common.RhrPocCoreCommonPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = RhrPocCoreCommonPackage.class)
public class RhrPocCoreCommonConfiguration {
}

package cz.hronza.rhrpoc.core.api.configuration;

import cz.hronza.rhrpoc.core.api.RhrPocCoreApiPackage;
import cz.hronza.rhrpoc.core.common.configuration.RhrPocCoreCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RhrPocCoreCommonConfiguration.class)
@ComponentScan(basePackageClasses = RhrPocCoreApiPackage.class)
public class RhrpocCoreApiConfiguration {
}

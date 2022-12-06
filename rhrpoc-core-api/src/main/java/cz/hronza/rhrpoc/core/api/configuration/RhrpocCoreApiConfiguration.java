package cz.hronza.rhrpoc.core.api.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.hronza.rhrpoc.core.api.RhrPocCoreApiPackage;
import cz.hronza.rhrpoc.core.api.interceptor.PocCoreHandlerInterceptor;
import cz.hronza.rhrpoc.core.common.configuration.RhrPocCoreCommonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Configuration
@ComponentScan(basePackageClasses = RhrPocCoreApiPackage.class)
@Import(RhrPocCoreCommonConfiguration.class)
public class RhrpocCoreApiConfiguration implements WebMvcConfigurer {


    private static final System.Logger logger = System.getLogger(RhrpocCoreApiConfiguration.class.getSimpleName());
    public static final String ERROR_MESSAGE_PATTERN = "%s=%s";

    private final PocCoreHandlerInterceptor pocCoreHandlerInterceptor;

    private final ObjectMapper objectMapper;

    private static final OffsetDateTime OFFSET_DATE_TIME_MIN = localdateTimeToOffsetDateTime(
            LocalDateTime.of(LocalDate.of(1900, 1, 3), LocalTime.MIN));

    private static final OffsetDateTime OFFSET_DATE_TIME_MAX = localdateTimeToOffsetDateTime(
            LocalDateTime.of(LocalDate.of(2900, 12, 31), LocalTime.MAX));


    public RhrpocCoreApiConfiguration(@Value("${poc.basic.auth.user}") String user,
                                      @Value("${poc.basic.auth.password}") String password,
                                      ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.pocCoreHandlerInterceptor = new PocCoreHandlerInterceptor(user, password);
        logger.log(System.Logger.Level.INFO, "interceptor initialized");
    }

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(initSimpleModelue());
        objectMapper.disable(MapperFeature.ALLOW_COERCION_OF_SCALARS);
    }

    private SimpleModule initSimpleModelue() {
        return new SimpleModule()
                .addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
                    @Override
                    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                        String inputValueAsString = jsonParser.getValueAsString();
                        logger.log(System.Logger.Level.INFO, String.format(ERROR_MESSAGE_PATTERN, "inputValueAsString", inputValueAsString));
                        logger.log(System.Logger.Level.INFO, String.format(ERROR_MESSAGE_PATTERN, "deserialized value", inputValueAsString != null ? OffsetDateTime.parse(inputValueAsString, DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null));
                        return inputValueAsString != null ? OffsetDateTime.parse(inputValueAsString, DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null;
                    }
                })
                .addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
                    @Override
                    public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                        logger.log(System.Logger.Level.INFO, String.format(ERROR_MESSAGE_PATTERN, "serialized value", offsetDateTimeToString(offsetDateTime)));
                        jsonGenerator.writeString(offsetDateTimeToString(offsetDateTime));
                    }
                });
    }

    private String offsetDateTimeToString(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null)
            return null;
        else if (offsetDateTime.isBefore(OFFSET_DATE_TIME_MIN) || offsetDateTime.isAfter(OFFSET_DATE_TIME_MAX))
            return String.format("%s%s", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime), "+02:00");
        else
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime);
    }

    private static OffsetDateTime localdateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atZone(ZoneId.of("Europe/Prague")).toOffsetDateTime() : null;
    }

    /**
     * add interceptor do WebMvc
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pocCoreHandlerInterceptor);
        logger.log(System.Logger.Level.INFO, "interceptor added");
    }
}

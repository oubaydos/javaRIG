package io.javarig.config;


import io.javarig.RandomInstanceGenerator;
import io.javarig.testclasses.ConfigurationTestClass;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;


@Slf4j
public class ConfigurationTest {

    @Test
    public void shouldGenerateAnObjectRespectingTheGivenConfig() { 
        // Given
        Class<?> type = ConfigurationTestClass.class;
        Configuration configuration = Configuration.builder()
            .maxSizeExclusive(15)
            .minSizeInclusive(5)
            .regexPattern("abc+")
            .build();
        RandomInstanceGenerator generator = new RandomInstanceGenerator(configuration);
        // When
        Object generatedObject = generator.generate(type);
        log.info("shouldGenerateAnObjectRespectingTheGivenConfig : {}", generatedObject);
        // Then
        assertThat(generatedObject).isInstanceOf(type);
        // pattern config 
        assertThat(generatedObject).extracting("s")
            .asString()
            .hasSizeBetween(configuration.getMinSizeInclusive(), configuration.getMaxSizeExclusive() - 1)
            .matches(configuration.getRegexPattern());
        // pattern config 
        assertThat(generatedObject).extracting("l")
            .asList()
            .hasSizeBetween(configuration.getMinSizeInclusive(), configuration.getMaxSizeExclusive() - 1);
        // other configs
    }

    @Test
    public void shouldProrioritizeWithSizeConfigOverGeneralConfig() { 
        // Given
        Class<?> type = String.class;
        Integer generalMinSize = 5;
        Integer generalMaxSize = 10;
        Configuration configuration = Configuration.builder()
            .maxSizeExclusive(generalMaxSize)
            .minSizeInclusive(generalMinSize)
            .regexPattern("abc+")
            .build();
        
        RandomInstanceGenerator generator = new RandomInstanceGenerator(configuration);
        Integer newMinSize = generalMaxSize + 2;
        Integer newMaxSize = newMinSize + 5;
        // When
        Object generatedObject = generator.withSize(newMinSize, newMaxSize).generate(type);
        log.info("shouldProrioritizeWithSizeConfigOverGeneralConfig : {}", generatedObject);
        // Then
        assertThat(generatedObject).isInstanceOf(type);
        assertThat(generatedObject)
            .asString()
            .matches(configuration.getRegexPattern())
            .hasSizeBetween(newMinSize, newMaxSize - 1);
    }

    @Test
    public void shouldProrioritizeWithRegexPatternConfigOverGeneralConfig() { 
        // Given
        Class<?> type = String.class;
        String generalRegexPattern = "abc+";
        Configuration configuration = Configuration.builder()
            .maxSizeExclusive(10)
            .minSizeInclusive(5)
            .regexPattern(generalRegexPattern)
            .build();
        RandomInstanceGenerator generator = new RandomInstanceGenerator(configuration);
        String newRegexPattern = "test+";
        // When
        Object generatedObject = generator.withRegexPattern(newRegexPattern).generate(type);
        log.info("shouldProrioritizeWithRegexPatternConfigOverGeneralConfig : {}", generatedObject);
        // Then
        assertThat(generatedObject).isInstanceOf(type);
        assertThat(generatedObject)
            .asString()
            .matches(newRegexPattern)
            .hasSizeBetween(configuration.getMinSizeInclusive(), configuration.getMaxSizeExclusive() - 1);
    }

    @Test
    public void shouldProrioritizeWithOneTimeConfigOverGeneralConfig() { 
        // Given
        Class<?> type = String.class;
        String generalRegexPattern = "abc+";
        Configuration configuration = Configuration.builder()
            .maxSizeExclusive(10)
            .minSizeInclusive(5)
            .regexPattern(generalRegexPattern)
            .build();
        Configuration oneTimeConfig = Configuration.builder()
            .maxSizeExclusive(20)
            .minSizeInclusive(11)
            .build();
        RandomInstanceGenerator generator = new RandomInstanceGenerator(configuration);
        // When
        Object generatedObject = generator.withOneTimeConfig(oneTimeConfig).generate(type);
        log.info("shouldProrioritizeWithRegexPatternConfigOverGeneralConfig : {}", generatedObject);
        // Then
        assertThat(generatedObject).isInstanceOf(type);
        assertThat(generatedObject)
            .asString()
            .matches(oneTimeConfig.getRegexPattern())
            .hasSizeBetween(oneTimeConfig.getMinSizeInclusive(), oneTimeConfig.getMaxSizeExclusive() - 1);
    }
}

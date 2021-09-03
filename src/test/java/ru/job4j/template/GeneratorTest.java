package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void produce() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you");
        String rsl = generator.produce(template, args);
        assertThat("I am a Petr Arsentev, Who are you? ", is(rsl));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenWrongArgs() {
        Generator generator = new TemplateGenerator();
        String template = "Hello ${user}";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you");
        String rsl = generator.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenWrongMap() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "user", "Petr");
        String rsl = generator.produce(template, args);
    }

}
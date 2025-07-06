package ua.hudyma.notificationservice.service;

import com.github.mustachejava.DefaultMustacheFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

@Component
public class EmailTemplateBuilder {

    public String buildTemplate(String templateName, Map<String, Object> data) throws IOException {
        var mf = new DefaultMustacheFactory();
        var reader = new InputStreamReader(
                new ClassPathResource("templates/" + templateName + ".mustache.html").getInputStream());
        var mustache = mf.compile(reader, templateName);
        var writer = new StringWriter();
        mustache.execute(writer, data).flush();
        return writer.toString();
    }
}


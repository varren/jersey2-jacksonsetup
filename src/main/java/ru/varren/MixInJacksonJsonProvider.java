package ru.varren;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import ru.varren.model.TestBean;
import ru.varren.model.TestBeanMixin;

import javax.annotation.Priority;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Vestl_000 on 20.05.2016.
 */
@Provider
@Priority(1)
public class MixInJacksonJsonProvider extends JacksonJaxbJsonProvider {
    private static final ObjectMapper mapper = createMapper();

    public MixInJacksonJsonProvider() {
        System.out.println("DefaultJacksonJsonProvider: ");
        setMapper(mapper);
    }

    private static ObjectMapper createMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.enable(SerializationFeature.INDENT_OUTPUT);
        result.addMixIn(TestBean.class, TestBeanMixin.class);
        return result;
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return super.isReadable(type, genericType, annotations, mediaType) && hasMixInAnnotation(annotations);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return super.isWriteable(type, genericType, annotations, mediaType) && hasMixInAnnotation(annotations);
    }

    public static boolean hasMixInAnnotation(Annotation[] annotations){
        for(Annotation annotation: annotations){
            if (annotation instanceof MixIn){
                return true;
            }
        }
        return false;
    }
}

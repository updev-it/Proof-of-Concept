package api.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

/**
 * BaseConverter
 */
public abstract class BaseConverter<T> extends MapConverter implements Converter {

    protected Class<?> cls;
    protected Map<String, Field> aliasedFields = new HashMap<String, Field>();

    // protected final Logger logger;

    public BaseConverter(Mapper mapper) {
        super(mapper);
        this.cls = (Class<?>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        // this.controller = controller;
        // this.logger = LoggerFactory.getLogger(this.cls);

        initialize();
    }

    public void assignClass(Class<?> clazz) {
        this.cls = clazz;
        initialize();
    }

    private void initialize() {
        Map<String, Field> aliasedFields = getAnnotatedDeclaredFields(this.cls, XStreamAlias.class, true);

        aliasedFields.forEach((fieldName, field) -> {
            this.aliasedFields.put(field.getAnnotation(XStreamAlias.class).value(), field);
        });
    }

    /**
     * Retrieving fields list of specified class If recursively is true, retrieving
     * fields from all class hierarchy
     *
     * @param clazz       Class to search through
     * @param recursively boolean to indicate if search needs to be recursive
     *                    through class hierarchy
     * @return Map of fields
     */
    private static Map<String, Field> getDeclaredFields(Class<?> clazz, boolean recursively) {
        Map<String, Field> fields = new HashMap<String, Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            fields.put(field.getName(), field);
        }

        Class<?> superClass = clazz.getSuperclass();

        if (superClass != null && recursively) {
            Map<String, Field> declaredFieldsOfSuper = getDeclaredFields(superClass, true);
            if (declaredFieldsOfSuper.size() > 0) {
                fields.putAll(declaredFieldsOfSuper);
            }
        }

        return fields;
    }

    /**
     * Retrieving fields list of specified class and which are annotated by incoming
     * annotation class If recursively is true, retrieving fields from all class
     * hierarchy
     *
     * @param clazz           - Class to search through
     * @param annotationClass - specified annotation class
     * @param recursively     - boolean to indicate if search needs to be recursive
     * @return Map of annotated fields
     */
    public static Map<String, Field> getAnnotatedDeclaredFields(Class<?> clazz,
            Class<? extends Annotation> annotationClass, boolean recursively) {
        Map<String, Field> allFields = getDeclaredFields(clazz, recursively);
        Map<String, Field> annotatedFields = new HashMap<String, Field>();

        allFields.forEach((key, value) -> {
            if (value.isAnnotationPresent(annotationClass)) {
                annotatedFields.put(key, value);
            }
        });

        return annotatedFields;
    }
}
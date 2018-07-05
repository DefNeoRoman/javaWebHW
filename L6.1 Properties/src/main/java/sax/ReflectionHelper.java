package sax;


import java.lang.reflect.Field;


public class ReflectionHelper {
    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (IllegalArgumentException | SecurityException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object,
                                     String fieldName,
                                     String value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Class<?> type = field.getType();


            field.setAccessible(false);
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}

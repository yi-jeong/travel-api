package com.example.travel.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DataMapper {

    public static <T, U> U map(T source, Class<U> destinationClass) {
        try {
            U destination = destinationClass.getDeclaredConstructor().newInstance();
            BeanInfo sourceBeanInfo = Introspector.getBeanInfo(source.getClass());
            BeanInfo destinationBeanInfo = Introspector.getBeanInfo(destinationClass);

            PropertyDescriptor[] sourcePropertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
            PropertyDescriptor[] destinationPropertyDescriptors = destinationBeanInfo.getPropertyDescriptors();

            Map<String, PropertyDescriptor> destinationPropertyDescriptorMap = new HashMap<>();
            for (PropertyDescriptor descriptor : destinationPropertyDescriptors) {
                destinationPropertyDescriptorMap.put(descriptor.getName(), descriptor);
            }

            for (PropertyDescriptor sourceDescriptor : sourcePropertyDescriptors) {
                if (!sourceDescriptor.getName().equals("class")) {
                    PropertyDescriptor destinationDescriptor = destinationPropertyDescriptorMap.get(sourceDescriptor.getName());
                    if (destinationDescriptor != null && destinationDescriptor.getWriteMethod() != null) {
                        Object value = sourceDescriptor.getReadMethod().invoke(source);
                        destinationDescriptor.getWriteMethod().invoke(destination, value);
                    }
                }
            }

            return destination;
        } catch (IntrospectionException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Error mapping objects", e);
        }
    }
}

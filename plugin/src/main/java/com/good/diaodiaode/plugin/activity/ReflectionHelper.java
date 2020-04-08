/*
 * 上海基分文化传播有限公司Shanghai Jifen Culture Communications Co., Ltd.
 *  Copyright (c) 2018 Shanghai Jifen Culture Communications Co., Ltd. All rights reserved.
 */

package com.good.diaodiaode.plugin.activity;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper {
    public static void copyField(Object source, Object dst, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = source.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object valueSource = field.get(source);
        field.set(dst, valueSource);
    }

    public static <T> T getValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    public static <T> T getStaticValue(Class clazz, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(null);
    }

    public static void setValue(Object obj, Object value, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void setStaticValue(Class clazz, Object value, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

    public static Object invoke(Class clazz, Object target, String methodName, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] parameterTypes = null;
        if (params != null) {
            parameterTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                parameterTypes[i] = params[i].getClass();
            }
        }

        Method method = findMethod(clazz, methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(target, params);
    }

    public static Object invokeConstructor(Class clazz, Class[] parameterTypes, Object... args)
            throws Exception {
        Constructor constructor = clazz.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    public static Object invokeNoException(Class clazz, Object target, String name, Class[] parameterTypes, Object... args) {
        try {
            return invoke(clazz, target, name, parameterTypes, args);
        } catch (Exception e) {
        }

        return null;
    }

    public static Object getFieldQuality(Class clazz, Object target, String name) {
        try {
            return ReflectionHelper.getField(clazz, target, name);
        } catch (Exception e) {
            //ignored.
        }

        return null;
    }

    public static void setField(Class clazz, Object target, String name, Object value) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

    public static <T> T getField(Class clazz, Object target, String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return (T) field.get(target);
    }

    public static void setFieldQuality(Class clazz, Object target, String name, Object value) {
        try {
            setField(clazz, target, name, value);
        } catch (Exception e) {
            //ignored.
        }
    }


    /**
     * Locates a given field anywhere in the class inheritance hierarchy.
     *
     * @param instance an object to search the field into.
     * @param name     field name
     * @return a field object
     * @throws NoSuchFieldException if the field cannot be located
     */
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);


                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }

        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    public static Field findField(Class<?> originClazz, String name) throws NoSuchFieldException {
        for (Class<?> clazz = originClazz; clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }

        throw new NoSuchFieldException("Field " + name + " not found in " + originClazz);
    }

    public static <T> T findValue(Object instance, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = findField(instance, name);
        return (T) field.get(instance);
    }

    /**
     * Locates a given method anywhere in the class inheritance hierarchy.
     *
     * @param instance       an object to search the method into.
     * @param name           method name
     * @param parameterTypes method parameter types
     * @return a method object
     * @throws NoSuchMethodException if the method cannot be located
     */
    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);


                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }

                return method;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }

        throw new NoSuchMethodException("Method " + name + " with parameters " +
                Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
    }

    public static Method findMethod(Class<?> clazz, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        for (; clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);

                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }

                return method;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }

        throw new NoSuchMethodException("Method " + name + " with parameters " + Arrays.asList(parameterTypes) + " not found in " + clazz);
    }

    public static Constructor<?> findConstructor(Object instance, Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Constructor<?> ctor = clazz.getDeclaredConstructor(parameterTypes);

                if (!ctor.isAccessible()) {
                    ctor.setAccessible(true);
                }

                return ctor;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }

        throw new NoSuchMethodException("Constructor" + " with parameters " + Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
    }

    /**
     * Replace the value of a field containing a non null array, by a new array containing the
     * elements of the original array plus the elements of extraElements.
     *
     * @param instance      the instance whose field is to be modified.
     * @param fieldName     the field to modify.
     * @param extraElements elements to append at the end of the array.
     */
    public static void expandFieldArray(Object instance, String fieldName, Object[] extraElements)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field jlrField = findField(instance, fieldName);

        Object[] original = (Object[]) jlrField.get(instance);
        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);

        // NOTE: changed to copy extraElements first, for patch load first

        System.arraycopy(extraElements, 0, combined, 0, extraElements.length);
        System.arraycopy(original, 0, combined, extraElements.length, original.length);

        jlrField.set(instance, combined);
    }

    /**
     * Replace the value of a field containing a non null array, by a new array containing the
     * elements of the original array plus the elements of extraElements.
     *
     * @param instance      the instance whose field is to be modified.
     * @param fieldName     the field to modify.
     * @param extraElements elements to append at the end of the array.
     */
    public static <T> void expandFieldList(Object instance, String fieldName,
                                           T... extraElements) throws NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field jlrField = ReflectionHelper.findField(instance, fieldName);
        List<T> original = (List<T>) jlrField.get(instance);
        if (null == original) {
            original = new ArrayList<T>();
            if (!jlrField.isAccessible()) {
                jlrField.setAccessible(true);
            }
            jlrField.set(instance, original);
        }
        if (null == extraElements) {
            return;
        }

        // NOTE: changed to copy extraElements first, for patch load first
        for (T object : extraElements) {
            original.add(0, object);
        }
    }

    /**
     * 数组合并
     *
     * @param arrayLhs
     * @param arrayRhs
     * @return
     */
    public static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k) {
            if (k < i) {
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }

    public Object appendArray(Object array, Object value) {
        Class<?> localClass = array.getClass().getComponentType();
        int i = Array.getLength(array);
        int j = i + 1;
        Object localObject = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k) {
            if (k < i) {
                Array.set(localObject, k, Array.get(array, k));
            } else {
                Array.set(localObject, k, value);
            }
        }
        return localObject;
    }

    /**
     * Replace the value of a field containing a non null array, by a new array containing the
     * elements of the original array plus the elements of extraElements.
     *
     * @param instance  the instance whose field is to be modified.
     * @param fieldName the field to modify.
     */
    public static void reduceFieldArray(Object instance, String fieldName, int reduceSize)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (reduceSize <= 0) {
            return;
        }

        Field jlrField = findField(instance, fieldName);

        Object[] original = (Object[]) jlrField.get(instance);
        int finalLength = original.length - reduceSize;

        if (finalLength <= 0) {
            return;
        }

        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), finalLength);

        System.arraycopy(original, reduceSize, combined, 0, finalLength);

        jlrField.set(instance, combined);
    }

    public static void reduceFieldList(Object instance, String fieldName, int reduceSize)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (reduceSize <= 0) {
            return;
        }

        Field jlrField = findField(instance, fieldName);

        List<Object> original = (List<Object>) jlrField.get(instance);
        for (int i = 0; i < reduceSize; ++i) {
            original.remove(0);
        }

        jlrField.set(instance, original);
    }
}

package com.nnk.springboot.poseidon.mapper;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/***
 * Mapper bi-directionnelle générique permettant de convertir des Modèle <MT> en entité <E> et vis versa
 * @param <MT>
 * @param <E>
 */
public class ReflectMapper<MT, E> {

    private final Constructor<E> constructE;
    private final Constructor<MT> constructMT;

    private Map<String,  Map<String, Method>> methodsMT;
    private Map<String, Map<String, Method>> methodsE;

    @SneakyThrows
    public ReflectMapper(Class<MT> classMT, Class<E> classE) {
        constructE = classE.getConstructor();
        methodsE = generateGetSetMethodsMap(classE);

        constructMT = classMT.getConstructor();
        methodsMT = generateGetSetMethodsMap(classMT);
    }

    @SneakyThrows
    public E mapToEntity(MT model) {
        E instance = constructE.newInstance();
        methodsMT.get("get").forEach((k, gm) -> {
            invokeGetterInSetter(gm, model, methodsE.get("set").get(k), instance);
        });

        return instance;
    }

    @SneakyThrows
    public MT mapToModel(E entity) {
        MT instance = constructMT.newInstance();
        methodsMT.get("set").forEach((k, sm) -> {
            invokeGetterInSetter(methodsE.get("get").get(k), entity, sm, instance);
        });

        return instance;
    }


    @SneakyThrows
    private static void invokeGetterInSetter(Method getMethod, Object getObj, Method setMethod, Object setObj) {
        setMethod.invoke(setObj,
                getMethod.invoke(getObj));
    }

    private static Map<String,  Map<String, Method>> generateGetSetMethodsMap(Class<?> c) {
        Map<String,  Map<String, Method>> map = new HashMap<>();
        map.put("get", new HashMap<>());
        map.put("set", new HashMap<>());

        Arrays.stream(c.getDeclaredMethods())
                .forEach(m -> {
                    if(m.getName().startsWith("get"))
                        map.get("get").put(m.getName().toLowerCase(Locale.ROOT).replace("get",""), m);
                    else if(m.getName().startsWith("set"))
                        map.get("set").put(m.getName().toLowerCase(Locale.ROOT).replace("set",""),m);
                });

        return map;
    }
}

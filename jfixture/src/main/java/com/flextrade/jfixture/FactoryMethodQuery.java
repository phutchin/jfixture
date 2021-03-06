package com.flextrade.jfixture;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;
import java.util.List;

public interface FactoryMethodQuery {

    public List<Method> getFactoryMethodsForType(SpecimenType type);
}

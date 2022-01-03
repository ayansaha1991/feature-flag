package com.featureflags.poc.featureflags;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

public class RenamingServiceFactory<T> implements FactoryBean<T>{

	private T defaultRenamingService;
	private T customerPrefRenamingService;
	private Supplier<Boolean> isCustomerPrefAvailable;
	private Class<T> serviceType;
	
	
	public RenamingServiceFactory(T defaultRenamingService, T customerPrefRenamingService,
			Supplier<Boolean> isCustomerPrefAvailable, Class<T> serviceType) {
		super();
		this.defaultRenamingService = defaultRenamingService;
		this.customerPrefRenamingService = customerPrefRenamingService;
		this.isCustomerPrefAvailable = isCustomerPrefAvailable;
		this.serviceType = serviceType;
	}

	@Override
	public T getObject() throws Exception {
		
		//send a proxy service that decides target class on every call
		InvocationHandler invocationHandler = (proxy, method, args) -> {
			
			if (isCustomerPrefAvailable.get()) {
				return method.invoke(customerPrefRenamingService, args);
			} else {
				return method.invoke(defaultRenamingService, args);
			}
			
		};
		
		//construct the proxy that implement serviceType
		Object proxy = Proxy.newProxyInstance(serviceType.getClassLoader(), new Class[] {serviceType}, invocationHandler);
		
		return (T) proxy;
	}

	@Override
	public Class<?> getObjectType() {
		return serviceType;
	}

	
}

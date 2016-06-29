/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.lkumarjain.faces.common;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

@SuppressWarnings("serial")
public final class JNContext implements Serializable {
	private static JNContext instance;
	public static JNContext get() {
		if (instance != null) return instance;
		synchronized (JNContext.class) {
			if (instance == null) instance = new JNContext(); 
			return instance;
		}
	}

	private JNContext() {}

	@SuppressWarnings("unchecked")
	public static <T> T getManagedBean (final String name) {
		ELContext context = getELContext();
		if (context != null) {
			ELResolver resolver = context.getELResolver();
			return resolver != null ? (T)resolver.getValue(context, null, name) : null;
		}
		return null;
	}

	public static <T> void setValue(final T value, final String expression, final Class<T> klass) {
		ValueExpression valueExpression = getValueExpression(expression, klass);
		if (valueExpression != null) valueExpression.setValue(getELContext(), value);
	}

	public static ValueExpression getValueExpression (final String expression, final Class<?> klass) {
		ExpressionFactory factory = getExpressionFactory();
		if (factory != null) return factory.createValueExpression(getELContext(), expression, klass);
		return null;
	}

	public static void convertVariable2Expression (final String variable, final String expression, final Class<?> klass) {
		ValueExpression valueExpression = getValueExpression(expression, klass);
		ELContext context = getELContext();
		if (valueExpression != null) context.getVariableMapper().setVariable(variable, valueExpression);
	}

	public static MethodExpressionActionListener getMethodActionListener (final String expression, final Class<?> returnType, final Class<?> ... paramTypes) {
		MethodExpression methodExpression = getMethodExpression(expression, returnType, paramTypes);
		if (methodExpression != null) return new MethodExpressionActionListener(methodExpression);
		return null;
	}

	public static MethodExpression getMethodExpression (final String expression, final Class<?> returnType, final Class<?> ... paramTypes) {
		ExpressionFactory factory = getExpressionFactory();
		if (factory != null) return factory.createMethodExpression(getELContext(), expression, returnType, paramTypes);
		return null;
	}

	public static Application getApplication () {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) return context.getApplication();
		return null;
	}

	public static ELContext getELContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) return context.getELContext();
		return null;
	}

	public static ExpressionFactory getExpressionFactory() {
		Application application = getApplication();
		if (application != null) return application.getExpressionFactory();
		return null;
	}

	public static ExternalContext getExternalContext () {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) return context.getExternalContext();
		return null;
	}

	public static String getSessionId () {
		ExternalContext context = getExternalContext();
		if (context != null) return context.getSessionId(false);
		return null;
	}
	
	public static String getRequestContextPath () {
		ExternalContext context = getExternalContext();
		if (context != null) return context.getRequestContextPath();
		return "";
	}

	public static String getRequestParam (String key) {
		ExternalContext context = getExternalContext();
		if (context != null) return context.getRequestParameterMap().get(key);
		return null;
	}
}

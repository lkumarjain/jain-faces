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
package com.github.lkumarjain.faces.layout;

import java.util.List;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

import com.github.lkumarjain.faces.common.JNIConstant;

@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="core.js"),
	@ResourceDependency(library="primefaces", name="components.js"),
	@ResourceDependency(library="jainfaces", name="jnlayout.js"),
	@ResourceDependency(library="jainfaces", name="jnlayout.css")
})
public class JNLayout extends UIComponentBase implements Widget {
	public static final String COMPONENT_TYPE = "com.jain.faces.component.JNLayout";
	public static final String DEFAULT_RENDERER = "com.jain.faces.component.JNLayoutRenderer";

	public JNLayout() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return JNIConstant.COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		widgetVar
		,style
		,styleClass;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
	}

	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	public String resolveWidgetVar() {
		String userWidgetVar = getWidgetVar();
		if(userWidgetVar != null)
			return userWidgetVar;
		FacesContext context = getFacesContext();
		return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public JNLayoutPane center() {
		List<UIComponent> children = getChildren();
		for (UIComponent component : children) {
			JNLayoutPane pane = (JNLayoutPane)component;
			if (pane.isCenter()) {
				if (pane.hasChildPane()) return pane.center();
				return pane;
			}
		}
		return null;
	}
}

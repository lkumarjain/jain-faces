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
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.Widget;

import com.github.lkumarjain.faces.common.JNIConstant;

@ResourceDependencies({
})
public class JNLayoutPane extends UIComponentBase implements Widget {
	public static final String COMPONENT_TYPE = "com.jain.faces.component.JNLayoutPane";
	private static final String DEFAULT_STYLE_CLASS = "jn-layout jn-layout-pane-";

	public JNLayoutPane() {
		setRendererType(null);
	}

	public String getFamily() {
		return JNIConstant.COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		label
		,widgetVar
		,style
		,styleClass
		,position
		,width
		,height;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label, null);
	}

	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
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

	public String getPosition() {
		return (String) getStateHelper().eval(PropertyKeys.position, null);
	}

	public void setPosition(String _position) {
		getStateHelper().put(PropertyKeys.position, _position);
	}

	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
	}

	public String getHeight() {
		return (String) getStateHelper().eval(PropertyKeys.height, null);
	}

	public void setHeight(String _height) {
		getStateHelper().put(PropertyKeys.height, _height);
	}

	public String resolveWidgetVar() {
		String userWidgetVar = getWidgetVar();
		if(userWidgetVar != null)
			return userWidgetVar;
		FacesContext context = getFacesContext();
		return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public boolean hasChildPane() {
		List<UIComponent> children = getChildren();
		for (UIComponent component : children) {
			if (component instanceof JNLayoutPane) return true;
		}
		return false;
	}

	public boolean isCenter() {
		return getPosition().equalsIgnoreCase("center");
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

	public String styleClass() {
		StringBuilder styleClassBuilder = new StringBuilder(JNLayoutPane.DEFAULT_STYLE_CLASS);
		styleClassBuilder.append(getPosition().toLowerCase());
		if (!isCenter()) styleClassBuilder.append(" jn-hideOnMobile  ");
		if (getStyleClass() != null)  styleClassBuilder.append(getStyleClass());
		return styleClassBuilder.toString();
	}
}

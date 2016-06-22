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
package com.jain.faces.grid;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;

import com.jain.faces.common.JNIConstant;

@ResourceDependencies({ 
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="core.js"),
	@ResourceDependency(library="primefaces", name="components.js"),
	@ResourceDependency(library = "jainfaces", name = "jn-grid.css") 
})
public class JNGrid extends UIComponentBase {
	public static final String COMPONENT_TYPE = "com.jain.faces.component.JNGrid";
	public static final String DEFAULT_RENDERER = "com.jain.faces.component.JNGridRenderer";
	private static final String DEFAULT_STYLE_CLASS = "jn-grid";

	public JNGrid() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return JNIConstant.COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		style, 
		styleClass, 
		columns,
		template;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
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

	public int getColumns() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.columns, 1);
	}

	public void setColumns(int _columns) {
		getStateHelper().put(PropertyKeys.columns, _columns);
	}

	public String getTemplate() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.template, null);
	}

	public void setTemplate(String _template) {
		getStateHelper().put(PropertyKeys.template, _template);
	}

	public Object calculateStyleClass() {
		StringBuilder builder = new StringBuilder(DEFAULT_STYLE_CLASS);
		if (getStyleClass() != null) {
			builder.append(" ");
			builder.append(getStyleClass());
		}
		return builder.toString();
	}
}

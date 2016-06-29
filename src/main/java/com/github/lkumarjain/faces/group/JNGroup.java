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
package com.github.lkumarjain.faces.group;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;

import com.github.lkumarjain.faces.common.JNIConstant;

@ResourceDependencies({ 
	@ResourceDependency(library = "jainfaces", name = "jn-group.css") 
})
public class JNGroup extends UIComponentBase {
	public static final String COMPONENT_TYPE = "com.jain.faces.component.JNGroup";
	public static final String DEFAULT_RENDERER = "com.jain.faces.component.JNGroupRenderer";

	public static final String DEFAULT_STYLE_CLASS = "jn-group ui-widget-content ";
	public static final String DEFAULT_TOP_STYLE_CLASS = "jn-group-top";
	public static final String DEFAULT_CONTENT_WRAPPER_STYLE_CLASS = "jn-group-content-wrapper ui-state-default";
	public static final String DEFAULT_CONTENT_STYLE_CLASS = "jn-group-content";
	public static final String DEFAULT_LEFT_STYLE_CLASS = "jn-group-left";
	public static final String DEFAULT_RIGHT_STYLE_CLASS = "jn-group-right";
	public static final String DEFAULT_BOTTOM_STYLE_CLASS = "jn-group-bottom";

	public JNGroup() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return JNIConstant.COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		style, 
		styleClass;

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

	public Object calculateStyleClass() {
		StringBuilder builder = new StringBuilder(DEFAULT_STYLE_CLASS);
		if (getStyleClass() != null) {
			builder.append(" ");
			builder.append(getStyleClass());
		}
		return builder.toString();
	}
}

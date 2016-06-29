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
package com.github.lkumarjain.faces.output;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIOutput;

import com.github.lkumarjain.faces.common.JNIConstant;

@ResourceDependencies({ 
	@ResourceDependency(library = "jainfaces", name = "jn-output.css") 
})
public class JNOutput extends UIOutput {
	private static final String CARD = "CARD";
	private static final String HORIZONTAL = "HORIZONTAL";
	public static final String COMPONENT_TYPE = "com.jain.faces.component.JNOutput";
	public static final String DEFAULT_RENDERER = "com.jain.faces.component.JNRenderer";
	private static final String DEFAULT_STYLE_CLASS = "ui-widget jn-output";

	public JNOutput() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return JNIConstant.COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		type,
		label,
		labelIcon,
		valueIcon,
		escape,
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

	public String getType() {
		return (String) getStateHelper().eval(PropertyKeys.type, CARD);
	}

	public void setType(String _type) {
		getStateHelper().put(PropertyKeys.type, _type);
	}

	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label, null);
	}

	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
	}

	public String getLabelIcon() {
		return (String) getStateHelper().eval(PropertyKeys.labelIcon, null);
	}

	public void setLabelIcon(String _labelIcon) {
		getStateHelper().put(PropertyKeys.labelIcon, _labelIcon);
	}

	public String getValueIcon() {
		return (String) getStateHelper().eval(PropertyKeys.valueIcon, null);
	}

	public void setValueIcon(String _valueIcon) {
		getStateHelper().put(PropertyKeys.valueIcon, _valueIcon);
	}

	public boolean isEscape() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.escape, false);

    }

    public void setEscape(boolean escape) {
        getStateHelper().put(PropertyKeys.escape, escape);
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
		if (isCard()) builder.append(" ui-widget-content jn-output-with-header");
		if (isBage()) builder.append(" jn-output-bage ui-button");
		if (isHorizontal()) builder.append(" jn-output-horizontal");
		if (getStyleClass() != null) {
			builder.append(" ");
			builder.append(getStyleClass());
		}
		return builder.toString();
	}

	public boolean isHorizontal() {
		return HORIZONTAL.equalsIgnoreCase(getType());
	}

	public boolean isBage() {
		return CARD.equalsIgnoreCase(getType()) && getLabel() == null;
	}

	public boolean isCard() {
		return CARD.equalsIgnoreCase(getType()) && getLabel() != null;
	}
}

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

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

public class JNGroupRenderer extends CoreRenderer {
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		JNGroup group = (JNGroup) component;
		encodeMarkup(context, group);
	}

	protected void encodeMarkup(FacesContext context, JNGroup group) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = group.getClientId(context);
		writer.startElement("div", null);
		writer.writeAttribute("class", group.calculateStyleClass(), null);
		writer.writeAttribute("id", clientId, "id");
		if (group.getStyle() != null) writer.writeAttribute("style", group.getStyle(), null);
		encodeGroup(context, group);
		writer.endElement("div");
	}

	private void encodeGroup(FacesContext context, JNGroup group) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		encodeFacet(context, group, writer, "TOP", JNGroup.DEFAULT_TOP_STYLE_CLASS);
		writer.startElement("div", null);
		writer.writeAttribute("class", JNGroup.DEFAULT_CONTENT_WRAPPER_STYLE_CLASS, null);
		encodeFacet(context, group, writer, "LEFT", JNGroup.DEFAULT_LEFT_STYLE_CLASS);
		encodeFacet(context, group, writer, "RIGHT", JNGroup.DEFAULT_RIGHT_STYLE_CLASS);
		encodeCenter(context, group, writer);
		writer.endElement("div");
		encodeFacet(context, group, writer, "BOTTOM", JNGroup.DEFAULT_BOTTOM_STYLE_CLASS);
	}

	private void encodeCenter(FacesContext context, JNGroup group, ResponseWriter writer) throws IOException {
		List<UIComponent> components = group.getChildren();
		for (UIComponent component : components) {
			writer.startElement("div", null);
			writer.writeAttribute("class", JNGroup.DEFAULT_CONTENT_STYLE_CLASS, null);
			renderChild(context, component);
			writer.endElement("div");
		}
	}

	private void encodeFacet(FacesContext context, JNGroup group, ResponseWriter writer, String facet, String styleClass) throws IOException {
		UIComponent component = group.getFacet(facet);
		if (component != null) {
			writer.startElement("div", null);
			writer.writeAttribute("class", styleClass, null);
			renderChild(context, component);
			writer.endElement("div");
		}
	}

	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Do Nothing
	}

	public boolean getRendersChildren() {
		return true;
	}
}

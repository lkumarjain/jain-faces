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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;

public class JNOutputRenderer extends CoreRenderer {
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		JNOutput group = (JNOutput) component;
		encodeMarkup(context, group);
	}

	protected void encodeMarkup(FacesContext context, JNOutput output) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = output.getClientId(context);

		if (output.isBage()) {
			writer.startElement("span", null);
			writer.writeAttribute("class", output.calculateStyleClass(), null);
			writer.writeAttribute("id", clientId, "id");
			if (output.getStyle() != null) writer.writeAttribute("style", output.getStyle(), null);
			encodeIcon(writer, output.getValueIcon() + " jn-output-text-icon");
			if(output.isEscape()) writer.writeText(ComponentUtils.getValueToRender(context, output), "value");
			else writer.write(ComponentUtils.getValueToRender(context, output));
			writer.endElement("span");
		} else {
			writer.startElement("div", null);
			writer.writeAttribute("class", output.calculateStyleClass(), null);
			writer.writeAttribute("id", clientId, "id");
			if (output.getStyle() != null) writer.writeAttribute("style", output.getStyle(), null);
			encodeOutput(context, output);
			writer.endElement("div");
		}
	}

	private void encodeOutput(FacesContext context, JNOutput output) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		if(output.isCard()) {
			encodeCard(output, context);
		} else {
			if(output.getLabelIcon() != null) {
				writer.startElement("div", null);
				writer.writeAttribute("class", "jn-output-text-with-icon", null);
				encodeIcon(writer, output.getLabelIcon() + " jn-output-text-icon");
			}
			writer.startElement("div", null);
			writer.writeAttribute("class", "jn-output-label", null);
			if(output.isEscape()) writer.writeText(output.getLabel(), "value"); 
			else writer.write(output.getLabel());
			writer.endElement("div");

			if(output.getLabelIcon() != null) writer.endElement("div");

			encodeValue(output, context);
		}
	}

	private void encodeValue(JNOutput output, FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		if(output.getValueIcon() != null) {
			writer.startElement("div", null);
			writer.writeAttribute("class", "jn-output-text-with-icon", null);
			encodeIcon(writer, output.getValueIcon() + " jn-output-text-icon");
		}
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-output-text", null);
		if(output.isEscape()) writer.writeText(ComponentUtils.getValueToRender(context, output), "value");
		else writer.write(ComponentUtils.getValueToRender(context, output));
		writer.endElement("div");

		if(output.getValueIcon() != null) writer.endElement("div");
	}

	private void encodeCard(JNOutput output, FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-output-header", null);
		encodeIcon(writer, output.getLabelIcon() + " jn-output-header-icon");

		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-output-header-text", null);
		if(output.isEscape()) writer.writeText(output.getLabel(), "value"); 
		else writer.write(output.getLabel());
		writer.endElement("div");

		writer.startElement("hr", null);
		writer.writeAttribute("style", "margin: 7px 0px;", null);
		writer.endElement("hr");

		encodeValue(output, context);

		writer.endElement("div");
	}

	private void encodeIcon(ResponseWriter writer, String icon) throws IOException {
		if(icon != null) {
			writer.startElement("i", null);
			writer.writeAttribute("class", icon, null);
			writer.endElement("i");
		}
	}

	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Do Nothing
	}

	public boolean getRendersChildren() {
		return true;
	}
}

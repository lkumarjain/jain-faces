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
package com.jain.faces.layout;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

public class JNLayoutRenderer extends CoreRenderer {
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		JNLayout layout = (JNLayout) component;
		encodeMarkup(context, layout);
		encodeScript(context, layout);
	}

	protected void encodeScript(FacesContext context, JNLayout layout) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = layout.getClientId(context);
		String widgetVar = layout.resolveWidgetVar();
		
		startScript(writer, clientId);

		writer.write("$(function() {");

		writer.write("PrimeFaces.cw('JNLayout','" + widgetVar + "',{");
		writer.write("id:'" + clientId + "'");
		writer.write("});});");

		endScript(writer);
	}

	protected void encodeMarkup(FacesContext context, JNLayout layout) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-layout-wrapper", null);
		writer.writeAttribute("id", layout.getClientId(context), null);
		encodeMenuMarkup(context, layout);
		encodeLayoutMarkup(context, layout);
		writer.endElement("div");
	}

	private void encodeMenuMarkup(FacesContext context, JNLayout layout) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-layout-menu jn-showOnMobile", null);

		JNLayoutPane center = layout.center();		
		//----------------------------------------- Menu Text Start -----------------------------------------
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-menu-text", null);
		writer.write(center.getLabel());
		writer.endElement("div");
		//----------------------------------------- Menu Text End -----------------------------------------
		//----------------------------------------- Menu Button Start -----------------------------------------
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-menu", null);

		writer.startElement("span", null);
		writer.writeAttribute("class", "holder", null);
		writer.write("&#8285;&#8285;&#8285;");
		writer.endElement("span");
		writer.endElement("div");
		//----------------------------------------- Menu Button End -----------------------------------------
		//----------------------------------------- Menu Content Start -----------------------------------------
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-menu-content jn-hideOnMobile", null);
		writer.startElement("ul", null);
		encodeMenuContentMarkup(context, layout.getChildren(), center);
		writer.endElement("ul");
		writer.endElement("div");
		//----------------------------------------- Menu Content End -----------------------------------------

		writer.endElement("div");
	}

	private void encodeMenuContentMarkup(FacesContext context, List<UIComponent> children, JNLayoutPane center) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		for (UIComponent component : children) {
			JNLayoutPane pane = (JNLayoutPane)component;
			if (pane != center) {
				writer.startElement("li", null);

				writer.startElement("a", null);
				writer.writeAttribute("data", pane.getClientId(context), null);

				writer.startElement("span", null);
				writer.write(pane.getLabel());
				writer.endElement("span");

				writer.endElement("a");

				if (pane.hasChildPane()){
					writer.startElement("ul", null);
					writer.writeAttribute("class", "jn-hideOnMobile", null);
					writer.writeAttribute("id", pane.getClientId(context), null);
					encodeMenuContentMarkup(context, pane.getChildren(), center);
					writer.endElement("ul");
				}
				writer.endElement("li");
			}
		}
	}

	private void encodeLayoutMarkup(FacesContext context, JNLayout layout) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("class", "jn-layout jn-layout-content", null);
		encodeLayoutContentMarkup(context, layout.getChildren());
		writer.endElement("div");
	}

	private void encodeLayoutContentMarkup(FacesContext context, List<UIComponent> children) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		for (UIComponent component : children) {
			JNLayoutPane pane = (JNLayoutPane)component;
			writer.startElement("div", null);
			writer.writeAttribute("class", pane.styleClass(), null);
			if (pane.hasChildPane()) {
				encodeLayoutContentMarkup(context, pane.getChildren());				
			} else {
				writer.writeAttribute("id", pane.getClientId(context), null);
				renderChildren(context, pane);
			}
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

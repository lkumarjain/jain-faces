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

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.column.Column;
import org.primefaces.renderkit.CoreRenderer;

public class JNGridRenderer extends CoreRenderer {
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		JNGrid grid = (JNGrid) component;
		encodeMarkup(context, grid);
	}

	protected void encodeMarkup(FacesContext context, JNGrid grid) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = grid.getClientId(context);
		int columns = grid.getColumns();
		if(columns == 0) throw new FacesException("Columns of JN-Grid [" + clientId + "] must be greater than zero and less then Twenty.");
		writer.startElement("div", null);
		writer.writeAttribute("class", grid.calculateStyleClass(), null);
		writer.writeAttribute("id", clientId, "id");
		if (grid.getStyle() != null) writer.writeAttribute("style", grid.getStyle(), null);
		encodeGrid(context, grid, columns);
		writer.endElement("div");
	}

	private void encodeGrid(FacesContext context, JNGrid grid, int columns) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String[] templates = GridUtility.calculateTemplate(grid.getColumns(), grid.getTemplate());
		int i = 1;
		for(UIComponent child : grid.getChildren()) {
			if(!child.isRendered()) continue;
			if (i == 1) {
				writer.startElement("div", null);
				writer.writeAttribute("class", "jn-row", null);
			}
			writer.startElement("div", null); 
			int colSpan = 1;
			String styleClass = "  ";
			if (child instanceof Column) {
				Column column = (Column) child;
				colSpan = column.getColspan();
				if (colSpan > ((columns - i) + 1))  throw new FacesException("ColSpan [" + colSpan + "] of JN-Grid column must be less than remaing columns [" + (columns - i) +"]." );
				if (column.getStyle() != null) writer.writeAttribute("style", column.getStyle(), null);
				if (column.getStyleClass() != null) styleClass = column.getStyleClass();
				writer.writeAttribute("class", GridUtility.calculateColumnTemplate(templates, i - 1, colSpan) + styleClass, null);
				renderChildren(context, column);
			} else {
				writer.writeAttribute("class", GridUtility.calculateColumnTemplate(templates, i - 1, colSpan), null);
				child.encodeAll(context);	
			}
			writer.endElement("div");
			i = i + colSpan;
			if (i > columns) { 
				i = 1;
				writer.endElement("div");
			}
		}
	}

	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Do Nothing
	}

	public boolean getRendersChildren() {
		return true;
	}
}

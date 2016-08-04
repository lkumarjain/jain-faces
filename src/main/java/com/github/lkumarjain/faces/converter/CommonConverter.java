package com.github.lkumarjain.faces.converter;

import java.util.Collection;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "commonConverter")
public class CommonConverter  implements Converter {
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		return findValueObject(context, component, value);
	}

	private Object findValueObject(final FacesContext context, final UIComponent component, final String value) {
		if (component instanceof  UISelectItems) {
			Object object = ((UISelectItems)component).getValue();
			Collection<?> list = (Collection<?>) object;
			for (Object obj : list) {
				if (getAsString(context, component, obj).equals(value)) {
					return obj;
				}
			}
		}

		List<UIComponent> components = component.getChildren();
		for (UIComponent comp : components) {
			Object object = findValueObject(context, comp, value);
			if (object != null)
				return object;
		}
		return null;
	}

	public String getAsString(final FacesContext context, final UIComponent component, Object obj) {
		if (obj == null || obj.toString().equals("")) {
			return "";
		}
		return obj.toString();
	}
}

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
package com.github.lkumarjain.faces.grid;

import java.util.HashMap;
import java.util.Map;

public class GridUtility {
	public static final String DEFAULT_COLUMN_STYLE_CLASS_PREFIX = "jn-w-";
	private static final Map<Integer, String> COLUMN_2_TEMPLATE_MAP = new HashMap<Integer, String>();

	static {
		COLUMN_2_TEMPLATE_MAP.put(1, "20");
		COLUMN_2_TEMPLATE_MAP.put(2, "10,10");
		COLUMN_2_TEMPLATE_MAP.put(3, "7,6,7");
		COLUMN_2_TEMPLATE_MAP.put(4, "5,5,5,5");
		COLUMN_2_TEMPLATE_MAP.put(5, "4,4,4,4,4");
		COLUMN_2_TEMPLATE_MAP.put(6, "4,3,3,3,3,4");
		COLUMN_2_TEMPLATE_MAP.put(7, "3,3,3,2,3,3,3");
		COLUMN_2_TEMPLATE_MAP.put(8, "2,3,2,3,2,3,2,3");
		COLUMN_2_TEMPLATE_MAP.put(9, "3,2,2,2,2,2,2,2,3");
		COLUMN_2_TEMPLATE_MAP.put(10, "2,2,2,2,2,2,2,2,2,2");
		COLUMN_2_TEMPLATE_MAP.put(11, "1,2,2,2,2,2,2,2,2,2,1");
		COLUMN_2_TEMPLATE_MAP.put(12, "1,1,2,2,2,2,2,2,2,2,1,1");
		COLUMN_2_TEMPLATE_MAP.put(13, "1,1,1,2,2,2,2,2,2,2,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(14, "1,1,1,1,2,2,2,2,2,2,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(15, "1,1,1,1,1,2,2,2,2,2,1,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(16, "1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(17, "1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(18, "1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(19, "1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1");
		COLUMN_2_TEMPLATE_MAP.put(20, "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1");
	}

	private GridUtility() {}

	public static String getColumnTemplate(final int columns) {
		return COLUMN_2_TEMPLATE_MAP.get(columns);
	}

	public static String[] calculateTemplate(final int columns, String template) {
		if (template == null) template = getColumnTemplate(columns);
		return template.split(",");
	}

	public static String calculateColumnTemplate(String[] templates, final int column, final int colSpan) {
		String columnValue = templates[column];
		if (colSpan > 1) {
			int col = 0;
			for (int i = 0; i < colSpan; i++) {
				try {
					col = col + Integer.parseInt(templates[column + i].trim());
				} catch (Exception logOrIgnore) {}
			}
			columnValue = String.valueOf(col);
		} 
		return DEFAULT_COLUMN_STYLE_CLASS_PREFIX + columnValue;
	}
}

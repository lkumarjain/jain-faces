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
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
}
PrimeFaces.widget.JNLayout = PrimeFaces.widget.DeferredWidget.extend({
	init : function(a) {
		this._super(a);
		this.renderDeferred()
	},
	_render : function() {
		var a = this;
		var layout = document.getElementById(a.id);
		a.menu = layout.getElementsByClassName("jn-menu")[0];
		a.menuContent = layout.getElementsByClassName("jn-menu-content")[0];
		a.activeItemName = layout.getElementsByClassName("jn-menu-text")[0];
		a.activeItemText = a.activeItemName.innerHTML;
		a.addEvent(a.menu, a.menuContent);

		var elements = a.menuContent.getElementsByTagName('a');
		for (var i = 0; i < elements.length; i++) {
			var b = elements[i];
			var attr = b.getAttribute("data")
			if (attr) {
				var d = document.getElementById(attr);
				a.addEvent(b, d);
			}
		}

		var p = document.createElement("DIV");
		p.className = 'jn-layout-place-holder jn-hideOnMobile';
		p.onclick = function() {
			a.toggle(a.activeItem, 'jn-hideOnMobile', 'jn-showOnMobile');
			a.toggle(p, 'jn-hideOnMobile', 'jn-showOnMobile');
			a.activeItem = undefined;
			a.activeItemName.innerHTML = a.activeItemText;
		}
		a.placeHolder = p;
		layout.insertBefore(p, layout.firstElementChild);
	},
	toggle : function(v, c1, c2) {
		if (v.classList.contains(c1)) {
			v.classList.add(c2);
			v.classList.remove(c1);
			return c2;
		} else if (v.classList.contains(c2)) {
			v.classList.add(c1);
			v.classList.remove(c2);
			return c1;
		}
	},
	addEvent : function(b, c) {
		var a = this;
		b.onclick = function() {
			var applied = a.toggle(c, 'jn-hideOnMobile', 'jn-showOnMobile');
			if (c != a.menuContent && c.tagName != 'UL') {
				a.toggle(a.menuContent, 'jn-hideOnMobile', 'jn-showOnMobile');
				a.toggle(a.placeHolder, 'jn-hideOnMobile', 'jn-showOnMobile');
				if (applied == 'jn-showOnMobile') {
					if (a.activeItem) a.toggle(a.activeItem, 'jn-hideOnMobile', 'jn-showOnMobile');
					a.activeItem = c;
					a.activeItemName.innerHTML = c.innerHTML;
				} else {
					a.activeItem = undefined;
					a.activeItemName.innerHTML = a.activeItemText;
				}
			}
		}
	}
});
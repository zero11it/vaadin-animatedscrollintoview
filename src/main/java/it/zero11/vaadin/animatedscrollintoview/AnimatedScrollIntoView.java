/**
 * Copyright (C) 2014 Zero11 S.r.l.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.zero11.vaadin.animatedscrollintoview;

import it.zero11.vaadin.animatedscrollintoview.client.AnimatedScrollIntoViewState;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class AnimatedScrollIntoView extends AbstractExtension {
	private static final long serialVersionUID = 1L;
	private char offset = 'a';
	
	public static AnimatedScrollIntoView applyTo(UI target) {
		AnimatedScrollIntoView extension = new AnimatedScrollIntoView();
		extension.extend(target);
		return extension;
	}

	private AnimatedScrollIntoView(){
	}

	public void scrollIntoView(Component component){
		if (++offset > 'z')
			offset = 'a';
		getState().scrollToTarget = offset + component.getConnectorId();
	}

	@Override
	public AnimatedScrollIntoViewState getState() {
		return (AnimatedScrollIntoViewState) super.getState();
	}
}

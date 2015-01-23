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

package it.zero11.vaadin.animatedscrollintoview.client;

import it.zero11.vaadin.animatedscrollintoview.AnimatedScrollIntoView;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.VPanel;
import com.vaadin.shared.ui.Connect;

@Connect(value=AnimatedScrollIntoView.class)
public class AnimatedScrollIntoViewConnector extends AbstractExtensionConnector {
	private static final long serialVersionUID = 1L;
	
	private ScrollAnimation scrollAnimation = null;
	
    @Override
    public AnimatedScrollIntoViewState getState() {
        return (AnimatedScrollIntoViewState) super.getState();
    }
    
    @OnStateChange("scrollToTarget")
    void onScrollToTarget() {
    	ServerConnector targetConnector = getConnection().getConnector(getState().scrollToTarget.substring(1), -1);
    	Widget scrollTarget = ((AbstractComponentConnector)targetConnector).getWidget();
    	
		VPanel parent = getParentVPanel(scrollTarget);
		if (parent != null){
			final int scrollPosition = scrollTarget.getElement().getOffsetTop();

			if (scrollAnimation != null)
				scrollAnimation.cancel();
			scrollAnimation = new ScrollAnimation(parent.contentNode, scrollPosition);
			scrollAnimation.run(250);
		}
	}

	private VPanel getParentVPanel(Widget target){
		while(true){
			target = target.getParent();
			if (target == null)
				return null;
			else if (target instanceof VPanel)
				return (VPanel) target;
		}
	}

	private class ScrollAnimation extends Animation {
		private final Element target;
		private final int scrollStart;
		private final int scrollEnd;

		public ScrollAnimation(Element target, int scrollEnd) {
			this.target = target;
			this.scrollStart = target.getScrollTop();
			this.scrollEnd = scrollEnd;
		}

		@Override
		protected void onComplete() {
			target.setScrollTop(scrollEnd);
			scrollAnimation = null;
		}

		@Override
		protected void onUpdate(double progress) {
			target.setScrollTop(scrollStart + (int) ((scrollEnd - scrollStart) * progress));
		}
	}

	@Override
	protected void extend(ServerConnector target) {
		
	}
}

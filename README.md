# Animated Scroll Into View Add-on for Vaadin 7

Animated Scroll Into View allow to scroll to a Component inside a scrollable Panel 

## Download release

Official releases of this add-on will be soon available at Vaadin Directory.

## Release notes

### Version 0.1.0
- First Public Release

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

ScrollTo is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

ScrollTo is written by Zero11

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

Add widgetset dependency:

```xml
<inherits name="it.zero11.vaadin.animatedscrollintoview.WidgetSet" /> 
```

Extend you UI and call scrollTo to scroll to the Component you want to be displayed:

```java
ScrollToExtension scroller;

// In you UI init
@Override
protected final void init(VaadinRequest request) {
	scroller = AnimatedScrollIntoView.applyTo(this);
	
	// Your UI initialization code
}

// To scroll
scroller.scrollIntoView(myComponent);
		
```

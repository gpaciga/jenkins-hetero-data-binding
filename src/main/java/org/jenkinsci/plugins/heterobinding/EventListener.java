package org.jenkinsci.plugins.heterobinding;

import hudson.Extension;
import hudson.XmlFile;
import hudson.model.Saveable;
import hudson.model.listeners.SaveableListener;
import jenkins.YesNoMaybe;

@Extension(dynamicLoadable=YesNoMaybe.YES)
public class EventListener extends SaveableListener {

	@Override
	public void onChange(Saveable saveable, XmlFile file) {
        HeteroBinding.call("saveable onchange");
	}

}

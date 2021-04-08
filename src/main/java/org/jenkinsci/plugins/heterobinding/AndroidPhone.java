package org.jenkinsci.plugins.heterobinding;

import hudson.Extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class AndroidPhone extends Phone {

	private static final Logger LOGGER = Logger.getLogger(AndroidPhone.class.getName());

	private String name;
	
	public AndroidPhone() {}

	@DataBoundConstructor
	public AndroidPhone(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}
	
	@DataBoundSetter
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void call(String event) {
		LOGGER.log(Level.INFO, "android " + this.name + " " + event);
	}

	private Object readResolve() {
		setName(name);
		return this;
	}

	@Extension
    public static final class DescriptorImpl extends Phone.DescriptorImpl {
		
        public String getDisplayName() {
            return "Android Phone";
        }

    }
}

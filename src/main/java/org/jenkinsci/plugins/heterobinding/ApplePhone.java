package org.jenkinsci.plugins.heterobinding;

import hudson.Extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class ApplePhone extends Phone {

	private static final Logger LOGGER = Logger.getLogger(ApplePhone.class.getName());

	private String name;
	
	public ApplePhone() {}

	@DataBoundConstructor
	public ApplePhone(String name) {
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
		LOGGER.log(Level.INFO, "apple " + this.name + " " + event);
	}

	private Object readResolve() {
		setName(name);
		return this;
	}

	@Extension
    public static final class DescriptorImpl extends Phone.DescriptorImpl {
		
        public String getDisplayName() {
            return "Apple Phone";
        }

    }
}

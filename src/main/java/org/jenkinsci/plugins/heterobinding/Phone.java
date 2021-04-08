package org.jenkinsci.plugins.heterobinding;

import java.util.logging.Level;
import java.util.logging.Logger;
import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public abstract class Phone extends AbstractDescribableImpl<Phone> implements ExtensionPoint {

	private static final Logger LOGGER = Logger.getLogger(Phone.class.getName());
	
	public abstract void call(String event);

	public abstract static class DescriptorImpl extends Descriptor<Phone> {

        @Override
        public Phone newInstance(StaplerRequest req, JSONObject formData) throws hudson.model.Descriptor.FormException {
			LOGGER.log(Level.SEVERE, "newInstance: " + formData.toString());
        	return super.newInstance(req, formData);
        }
        
    }
	
}

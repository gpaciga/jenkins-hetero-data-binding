package org.jenkinsci.plugins.heterobinding;

import static hudson.init.InitMilestone.PLUGINS_STARTED;
import hudson.Plugin;
import hudson.init.Initializer;
import hudson.model.Descriptor;
import hudson.model.Descriptor.FormException;
import hudson.util.DescribableList;
import hudson.DescriptorExtensionList;
import jenkins.util.Timer;
import jenkins.model.Jenkins;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.DataBoundSetter;

public class HeteroBinding extends Plugin {
	
	private static final Logger LOGGER = Logger.getLogger(HeteroBinding.class.getName());

	private static HeteroBinding instance;

	public static void call(String event) {
		if (instance != null) {
			instance._call(event);
		}
	}

	@Initializer(after = PLUGINS_STARTED)
	public static void init() {
		instance = Jenkins.getActiveInstance().getPlugin(HeteroBinding.class);
	}

	private DescribableList<Phone, Descriptor<Phone>> phones = new DescribableList<Phone, Descriptor<Phone>>(this);

	@Override
	public void start() throws Exception {
		try {
			load();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load", e);
		}
	}

	public DescribableList<Phone, Descriptor<Phone>> getPhones() {
		return phones;
	}

	@DataBoundSetter
	public void setPhones(DescribableList<Phone, Descriptor<Phone>> phones) {
		this.phones = phones;
	}

	public DescriptorExtensionList<Phone, Descriptor<Phone>> allPhones() {
		return Jenkins.getInstance().getDescriptorList(Phone.class);
	}

	@Override
	public void configure(StaplerRequest req, JSONObject formData) throws IOException, ServletException, FormException {
		LOGGER.log(Level.SEVERE, formData.toString());
		try {
			phones.rebuildHetero(req, formData, allPhones(), "phones");
			save();
	    } catch (IOException e) {
	        throw new FormException(e, "phones");
	    }
	}

	private void start(Runnable runnable) {
		Timer.get().submit(runnable);
	}

	private void _call(final String event) {
		for (final Phone phone : phones) {
			start(new Runnable() {
				public void run() {
					phone.call(event);
				}
			});
		}
	}

}

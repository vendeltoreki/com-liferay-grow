package growrecommendationsportlet.configuration;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import growrecommendationsportlet.configuration.GrowRecommendationsConfiguration;
import growrecommendationsportlet.constants.GrowRecommendationsPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
    configurationPid = "growrecommendationsportlet.configuration.GrowRecommendationsConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "javax.portlet.name=" + GrowRecommendationsPortletKeys.GrowRecommendations
    },
    service = ConfigurationAction.class
)
public class GrowRecommendationsConfigurationAction extends DefaultConfigurationAction {

    /**
	 * getJspPath: Return the path to our configuration jsp file.
	 * @param request The servlet request.
	 * @return String The path
	 */
	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration.jsp";
	}

    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
        throws Exception {

        String likedForRecommendation = ParamUtil.getString(actionRequest, "likedForRecommendation");
        String instanceNumber = ParamUtil.getString(actionRequest, "instanceNumber");
        String totalNumberOfInstances = ParamUtil.getString(actionRequest, "totalNumberOfInstances");
        String random = ParamUtil.getString(actionRequest, "random");

        setPreference(actionRequest, "likedForRecommendation", likedForRecommendation);
        setPreference(actionRequest, "instanceNumber", instanceNumber);
        setPreference(actionRequest, "totalNumberOfInstances", totalNumberOfInstances);
        setPreference(actionRequest, "random", random);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
            GrowRecommendationsConfiguration.class.getName(),
            _configuration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
            GrowRecommendationsConfiguration.class, properties);
    }

    private volatile GrowRecommendationsConfiguration _configuration;

    /**
	 * setServletContext: Sets the servlet context, use your portlet's bnd.bnd Bundle-SymbolicName value.
	 * @param servletContext The servlet context to use.
	 */
	@Override
	@Reference(
		target = "(osgi.web.symbolicname=growrecommendationsportlet)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}
}
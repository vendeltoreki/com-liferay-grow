package growrecommendationsportlet.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import growrecommendationsportlet.configuration.GrowRecommendationsConfiguration;
import growrecommendationsportlet.constants.GrowRecommendationsPortletKeys;

/**
 * @author norbertnemeth
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/index.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GrowRecommendationsPortletKeys.GrowRecommendations,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GrowRecommendationsPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			"mainRequire",
			_npmResolver.resolveModuleName("GrowRecommendationsPortlet") + " as main");

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try{
			_configuration = portletDisplay.getPortletInstanceConfiguration(
				GrowRecommendationsConfiguration.class);
		}
		catch(Exception e){
		}
		finally {
		renderRequest.setAttribute(
			GrowRecommendationsConfiguration.class.getName(), _configuration);
		}

		super.doView(renderRequest, renderResponse);
	}

	public Object getLikedForRecommendation(Map config) {
		return config.get(_configuration.likedForRecommendation());
	}

	public Object getInstanceNumber(Map config) {
		return config.get(_configuration.instanceNumber());
	}

	public Object getTotalNumberOfInstances(Map config) {
		return config.get(_configuration.totalNumberOfInstances());
	}

	public Object getRandom(Map config) {
		return config.get(_configuration.random());
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			GrowRecommendationsConfiguration.class, properties);
	}

	@Reference
	private NPMResolver _npmResolver;

	private volatile GrowRecommendationsConfiguration _configuration;

}
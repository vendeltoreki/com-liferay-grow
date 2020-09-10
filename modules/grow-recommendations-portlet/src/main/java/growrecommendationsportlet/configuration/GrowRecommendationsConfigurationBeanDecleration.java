package growrecommendationsportlet.configuration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component
public class GrowRecommendationsConfigurationBeanDecleration
    implements ConfigurationBeanDeclaration {

    @Override
    public Class<?> getConfigurationBeanClass() {
        return GrowRecommendationsConfiguration.class;
    }
}
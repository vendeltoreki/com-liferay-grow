package growrecommendationsportlet.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
   category = "Other",
   scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
   id = "growrecommendationsportlet.configuration.GrowRecommendationsConfiguration",
   name = "GrowRecommendationsConfiguration"
   )
public interface GrowRecommendationsConfiguration {

    @Meta.AD(
        deflt = "5",
        required = false
    )
    public String likedForRecommendation();

    @Meta.AD(
       deflt = "1",
       required = false
    )
    public String instanceNumber();

    @Meta.AD(
       deflt = "1",
       required = false
    )
    public String totalNumberOfInstances();

    @Meta.AD(
       deflt = "false",
       required = false
    )
    public String random();
}
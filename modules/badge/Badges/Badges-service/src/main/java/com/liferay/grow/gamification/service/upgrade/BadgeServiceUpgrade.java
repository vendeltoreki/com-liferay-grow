package com.liferay.grow.gamification.service.upgrade;



import com.liferay.grow.gamification.service.upgrade.v1_0_1.BadgeUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        service = { BadgeServiceUpgrade.class, UpgradeStepRegistrator.class}
)

public class BadgeServiceUpgrade implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {
        registry.register("1.0.0", "1.0.1", new BadgeUpgradeProcess());
    }
}
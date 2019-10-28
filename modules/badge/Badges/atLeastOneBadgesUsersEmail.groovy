import com.liferay.grow.gamification.model.Badge;
import com.liferay.grow.gamification.service.BadgeLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;

		List<Badge> badges = BadgeLocalServiceUtil.getBadges();

		HashMap<String, String> emailAddressScreenNameMap = new HashMap<String, String>();

		for (Badge badge : badges) {
			User user = UserLocalServiceUtil.fetchUser(badge.getToUserId());

			if (user == null) {
				out.println("errrrror");
				out.println(badge.getToUserId());
				continue;
			}

			emailAddressScreenNameMap.put(user.getScreenName(), user.getEmailAddress());
		}

		for (String screenName : emailAddressScreenNameMap.values()) {
		    out.println(screenName)
		}
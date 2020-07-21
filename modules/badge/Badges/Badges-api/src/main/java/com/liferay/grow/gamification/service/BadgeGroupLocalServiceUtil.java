/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.grow.gamification.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for BadgeGroup. This utility wraps
 * {@link com.liferay.grow.gamification.service.impl.BadgeGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Vilmos Papp
 * @see BadgeGroupLocalService
 * @see com.liferay.grow.gamification.service.base.BadgeGroupLocalServiceBaseImpl
 * @see com.liferay.grow.gamification.service.impl.BadgeGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class BadgeGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.grow.gamification.service.impl.BadgeGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the badge group to the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was added
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup addBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return getService().addBadgeGroup(badgeGroup);
	}

	/**
	* Creates a new badge group with the primary key. Does not add the badge group to the database.
	*
	* @param badgeGroupId the primary key for the new badge group
	* @return the new badge group
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup createBadgeGroup(
		long badgeGroupId) {
		return getService().createBadgeGroup(badgeGroupId);
	}

	/**
	* Deletes the badge group from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was removed
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup deleteBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return getService().deleteBadgeGroup(badgeGroup);
	}

	/**
	* Deletes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group that was removed
	* @throws PortalException if a badge group with the primary key could not be found
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup deleteBadgeGroup(
		long badgeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBadgeGroup(badgeGroupId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.grow.gamification.model.BadgeGroup fetchBadgeGroup(
		long badgeGroupId) {
		return getService().fetchBadgeGroup(badgeGroupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the badge group with the primary key.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group
	* @throws PortalException if a badge group with the primary key could not be found
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup getBadgeGroup(
		long badgeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBadgeGroup(badgeGroupId);
	}

	/**
	* Returns a range of all the badge groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.grow.gamification.model.impl.BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of badge groups
	* @param end the upper bound of the range of badge groups (not inclusive)
	* @return the range of badge groups
	*/
	public static java.util.List<com.liferay.grow.gamification.model.BadgeGroup> getBadgeGroups(
		int start, int end) {
		return getService().getBadgeGroups(start, end);
	}

	/**
	* Returns the number of badge groups.
	*
	* @return the number of badge groups
	*/
	public static int getBadgeGroupsCount() {
		return getService().getBadgeGroupsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the badge group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was updated
	*/
	public static com.liferay.grow.gamification.model.BadgeGroup updateBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return getService().updateBadgeGroup(badgeGroup);
	}

	public static BadgeGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BadgeGroupLocalService, BadgeGroupLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BadgeGroupLocalService.class);

		ServiceTracker<BadgeGroupLocalService, BadgeGroupLocalService> serviceTracker =
			new ServiceTracker<BadgeGroupLocalService, BadgeGroupLocalService>(bundle.getBundleContext(),
				BadgeGroupLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}
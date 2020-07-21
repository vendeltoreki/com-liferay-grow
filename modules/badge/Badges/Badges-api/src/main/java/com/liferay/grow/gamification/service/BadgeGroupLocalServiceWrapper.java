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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BadgeGroupLocalService}.
 *
 * @author Vilmos Papp
 * @see BadgeGroupLocalService
 * @generated
 */
@ProviderType
public class BadgeGroupLocalServiceWrapper implements BadgeGroupLocalService,
	ServiceWrapper<BadgeGroupLocalService> {
	public BadgeGroupLocalServiceWrapper(
		BadgeGroupLocalService badgeGroupLocalService) {
		_badgeGroupLocalService = badgeGroupLocalService;
	}

	/**
	* Adds the badge group to the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was added
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup addBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return _badgeGroupLocalService.addBadgeGroup(badgeGroup);
	}

	/**
	* Creates a new badge group with the primary key. Does not add the badge group to the database.
	*
	* @param badgeGroupId the primary key for the new badge group
	* @return the new badge group
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup createBadgeGroup(
		long badgeGroupId) {
		return _badgeGroupLocalService.createBadgeGroup(badgeGroupId);
	}

	/**
	* Deletes the badge group from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was removed
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup deleteBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return _badgeGroupLocalService.deleteBadgeGroup(badgeGroup);
	}

	/**
	* Deletes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group that was removed
	* @throws PortalException if a badge group with the primary key could not be found
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup deleteBadgeGroup(
		long badgeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _badgeGroupLocalService.deleteBadgeGroup(badgeGroupId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _badgeGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _badgeGroupLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _badgeGroupLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _badgeGroupLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _badgeGroupLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _badgeGroupLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _badgeGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.grow.gamification.model.BadgeGroup fetchBadgeGroup(
		long badgeGroupId) {
		return _badgeGroupLocalService.fetchBadgeGroup(badgeGroupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _badgeGroupLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the badge group with the primary key.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group
	* @throws PortalException if a badge group with the primary key could not be found
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup getBadgeGroup(
		long badgeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _badgeGroupLocalService.getBadgeGroup(badgeGroupId);
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
	@Override
	public java.util.List<com.liferay.grow.gamification.model.BadgeGroup> getBadgeGroups(
		int start, int end) {
		return _badgeGroupLocalService.getBadgeGroups(start, end);
	}

	/**
	* Returns the number of badge groups.
	*
	* @return the number of badge groups
	*/
	@Override
	public int getBadgeGroupsCount() {
		return _badgeGroupLocalService.getBadgeGroupsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _badgeGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _badgeGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _badgeGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the badge group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param badgeGroup the badge group
	* @return the badge group that was updated
	*/
	@Override
	public com.liferay.grow.gamification.model.BadgeGroup updateBadgeGroup(
		com.liferay.grow.gamification.model.BadgeGroup badgeGroup) {
		return _badgeGroupLocalService.updateBadgeGroup(badgeGroup);
	}

	@Override
	public BadgeGroupLocalService getWrappedService() {
		return _badgeGroupLocalService;
	}

	@Override
	public void setWrappedService(BadgeGroupLocalService badgeGroupLocalService) {
		_badgeGroupLocalService = badgeGroupLocalService;
	}

	private BadgeGroupLocalService _badgeGroupLocalService;
}
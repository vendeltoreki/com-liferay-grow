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

package com.liferay.grow.gamification.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.grow.gamification.model.BadgeGroup;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the badge group service. This utility wraps {@link com.liferay.grow.gamification.service.persistence.impl.BadgeGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeGroupPersistence
 * @see com.liferay.grow.gamification.service.persistence.impl.BadgeGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class BadgeGroupUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(BadgeGroup badgeGroup) {
		getPersistence().clearCache(badgeGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, BadgeGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BadgeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BadgeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BadgeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BadgeGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BadgeGroup update(BadgeGroup badgeGroup) {
		return getPersistence().update(badgeGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BadgeGroup update(BadgeGroup badgeGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(badgeGroup, serviceContext);
	}

	/**
	* Caches the badge group in the entity cache if it is enabled.
	*
	* @param badgeGroup the badge group
	*/
	public static void cacheResult(BadgeGroup badgeGroup) {
		getPersistence().cacheResult(badgeGroup);
	}

	/**
	* Caches the badge groups in the entity cache if it is enabled.
	*
	* @param badgeGroups the badge groups
	*/
	public static void cacheResult(List<BadgeGroup> badgeGroups) {
		getPersistence().cacheResult(badgeGroups);
	}

	/**
	* Creates a new badge group with the primary key. Does not add the badge group to the database.
	*
	* @param badgeGroupId the primary key for the new badge group
	* @return the new badge group
	*/
	public static BadgeGroup create(long badgeGroupId) {
		return getPersistence().create(badgeGroupId);
	}

	/**
	* Removes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group that was removed
	* @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	*/
	public static BadgeGroup remove(long badgeGroupId)
		throws com.liferay.grow.gamification.exception.NoSuchBadgeGroupException {
		return getPersistence().remove(badgeGroupId);
	}

	public static BadgeGroup updateImpl(BadgeGroup badgeGroup) {
		return getPersistence().updateImpl(badgeGroup);
	}

	/**
	* Returns the badge group with the primary key or throws a {@link NoSuchBadgeGroupException} if it could not be found.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group
	* @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	*/
	public static BadgeGroup findByPrimaryKey(long badgeGroupId)
		throws com.liferay.grow.gamification.exception.NoSuchBadgeGroupException {
		return getPersistence().findByPrimaryKey(badgeGroupId);
	}

	/**
	* Returns the badge group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group, or <code>null</code> if a badge group with the primary key could not be found
	*/
	public static BadgeGroup fetchByPrimaryKey(long badgeGroupId) {
		return getPersistence().fetchByPrimaryKey(badgeGroupId);
	}

	/**
	* Returns all the badge groups.
	*
	* @return the badge groups
	*/
	public static List<BadgeGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the badge groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of badge groups
	* @param end the upper bound of the range of badge groups (not inclusive)
	* @return the range of badge groups
	*/
	public static List<BadgeGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the badge groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of badge groups
	* @param end the upper bound of the range of badge groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of badge groups
	*/
	public static List<BadgeGroup> findAll(int start, int end,
		OrderByComparator<BadgeGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the badge groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BadgeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of badge groups
	* @param end the upper bound of the range of badge groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of badge groups
	*/
	public static List<BadgeGroup> findAll(int start, int end,
		OrderByComparator<BadgeGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the badge groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of badge groups.
	*
	* @return the number of badge groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BadgeGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BadgeGroupPersistence, BadgeGroupPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BadgeGroupPersistence.class);

		ServiceTracker<BadgeGroupPersistence, BadgeGroupPersistence> serviceTracker =
			new ServiceTracker<BadgeGroupPersistence, BadgeGroupPersistence>(bundle.getBundleContext(),
				BadgeGroupPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}
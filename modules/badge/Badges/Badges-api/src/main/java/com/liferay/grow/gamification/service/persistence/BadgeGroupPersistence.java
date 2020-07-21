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

import com.liferay.grow.gamification.exception.NoSuchBadgeGroupException;
import com.liferay.grow.gamification.model.BadgeGroup;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the badge group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vilmos Papp
 * @see com.liferay.grow.gamification.service.persistence.impl.BadgeGroupPersistenceImpl
 * @see BadgeGroupUtil
 * @generated
 */
@ProviderType
public interface BadgeGroupPersistence extends BasePersistence<BadgeGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BadgeGroupUtil} to access the badge group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, BadgeGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the badge group in the entity cache if it is enabled.
	*
	* @param badgeGroup the badge group
	*/
	public void cacheResult(BadgeGroup badgeGroup);

	/**
	* Caches the badge groups in the entity cache if it is enabled.
	*
	* @param badgeGroups the badge groups
	*/
	public void cacheResult(java.util.List<BadgeGroup> badgeGroups);

	/**
	* Creates a new badge group with the primary key. Does not add the badge group to the database.
	*
	* @param badgeGroupId the primary key for the new badge group
	* @return the new badge group
	*/
	public BadgeGroup create(long badgeGroupId);

	/**
	* Removes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group that was removed
	* @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	*/
	public BadgeGroup remove(long badgeGroupId)
		throws NoSuchBadgeGroupException;

	public BadgeGroup updateImpl(BadgeGroup badgeGroup);

	/**
	* Returns the badge group with the primary key or throws a {@link NoSuchBadgeGroupException} if it could not be found.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group
	* @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	*/
	public BadgeGroup findByPrimaryKey(long badgeGroupId)
		throws NoSuchBadgeGroupException;

	/**
	* Returns the badge group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param badgeGroupId the primary key of the badge group
	* @return the badge group, or <code>null</code> if a badge group with the primary key could not be found
	*/
	public BadgeGroup fetchByPrimaryKey(long badgeGroupId);

	/**
	* Returns all the badge groups.
	*
	* @return the badge groups
	*/
	public java.util.List<BadgeGroup> findAll();

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
	public java.util.List<BadgeGroup> findAll(int start, int end);

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
	public java.util.List<BadgeGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BadgeGroup> orderByComparator);

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
	public java.util.List<BadgeGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BadgeGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the badge groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of badge groups.
	*
	* @return the number of badge groups
	*/
	public int countAll();
}
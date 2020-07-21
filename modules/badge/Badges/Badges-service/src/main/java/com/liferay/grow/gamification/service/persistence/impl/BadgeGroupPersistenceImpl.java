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

package com.liferay.grow.gamification.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.grow.gamification.exception.NoSuchBadgeGroupException;
import com.liferay.grow.gamification.model.BadgeGroup;
import com.liferay.grow.gamification.model.impl.BadgeGroupImpl;
import com.liferay.grow.gamification.model.impl.BadgeGroupModelImpl;
import com.liferay.grow.gamification.service.persistence.BadgeGroupPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the badge group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vilmos Papp
 * @see BadgeGroupPersistence
 * @see com.liferay.grow.gamification.service.persistence.BadgeGroupUtil
 * @generated
 */
@ProviderType
public class BadgeGroupPersistenceImpl extends BasePersistenceImpl<BadgeGroup>
	implements BadgeGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BadgeGroupUtil} to access the badge group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BadgeGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public BadgeGroupPersistenceImpl() {
		setModelClass(BadgeGroup.class);
	}

	/**
	 * Caches the badge group in the entity cache if it is enabled.
	 *
	 * @param badgeGroup the badge group
	 */
	@Override
	public void cacheResult(BadgeGroup badgeGroup) {
		entityCache.putResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
			BadgeGroupImpl.class, badgeGroup.getPrimaryKey(), badgeGroup);

		badgeGroup.resetOriginalValues();
	}

	/**
	 * Caches the badge groups in the entity cache if it is enabled.
	 *
	 * @param badgeGroups the badge groups
	 */
	@Override
	public void cacheResult(List<BadgeGroup> badgeGroups) {
		for (BadgeGroup badgeGroup : badgeGroups) {
			if (entityCache.getResult(
						BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
						BadgeGroupImpl.class, badgeGroup.getPrimaryKey()) == null) {
				cacheResult(badgeGroup);
			}
			else {
				badgeGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all badge groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BadgeGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the badge group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BadgeGroup badgeGroup) {
		entityCache.removeResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
			BadgeGroupImpl.class, badgeGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BadgeGroup> badgeGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BadgeGroup badgeGroup : badgeGroups) {
			entityCache.removeResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
				BadgeGroupImpl.class, badgeGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new badge group with the primary key. Does not add the badge group to the database.
	 *
	 * @param badgeGroupId the primary key for the new badge group
	 * @return the new badge group
	 */
	@Override
	public BadgeGroup create(long badgeGroupId) {
		BadgeGroup badgeGroup = new BadgeGroupImpl();

		badgeGroup.setNew(true);
		badgeGroup.setPrimaryKey(badgeGroupId);

		return badgeGroup;
	}

	/**
	 * Removes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param badgeGroupId the primary key of the badge group
	 * @return the badge group that was removed
	 * @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup remove(long badgeGroupId)
		throws NoSuchBadgeGroupException {
		return remove((Serializable)badgeGroupId);
	}

	/**
	 * Removes the badge group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the badge group
	 * @return the badge group that was removed
	 * @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup remove(Serializable primaryKey)
		throws NoSuchBadgeGroupException {
		Session session = null;

		try {
			session = openSession();

			BadgeGroup badgeGroup = (BadgeGroup)session.get(BadgeGroupImpl.class,
					primaryKey);

			if (badgeGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBadgeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(badgeGroup);
		}
		catch (NoSuchBadgeGroupException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected BadgeGroup removeImpl(BadgeGroup badgeGroup) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(badgeGroup)) {
				badgeGroup = (BadgeGroup)session.get(BadgeGroupImpl.class,
						badgeGroup.getPrimaryKeyObj());
			}

			if (badgeGroup != null) {
				session.delete(badgeGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (badgeGroup != null) {
			clearCache(badgeGroup);
		}

		return badgeGroup;
	}

	@Override
	public BadgeGroup updateImpl(BadgeGroup badgeGroup) {
		boolean isNew = badgeGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (badgeGroup.isNew()) {
				session.save(badgeGroup);

				badgeGroup.setNew(false);
			}
			else {
				badgeGroup = (BadgeGroup)session.merge(badgeGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
			BadgeGroupImpl.class, badgeGroup.getPrimaryKey(), badgeGroup, false);

		badgeGroup.resetOriginalValues();

		return badgeGroup;
	}

	/**
	 * Returns the badge group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the badge group
	 * @return the badge group
	 * @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBadgeGroupException {
		BadgeGroup badgeGroup = fetchByPrimaryKey(primaryKey);

		if (badgeGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBadgeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return badgeGroup;
	}

	/**
	 * Returns the badge group with the primary key or throws a {@link NoSuchBadgeGroupException} if it could not be found.
	 *
	 * @param badgeGroupId the primary key of the badge group
	 * @return the badge group
	 * @throws NoSuchBadgeGroupException if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup findByPrimaryKey(long badgeGroupId)
		throws NoSuchBadgeGroupException {
		return findByPrimaryKey((Serializable)badgeGroupId);
	}

	/**
	 * Returns the badge group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the badge group
	 * @return the badge group, or <code>null</code> if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
				BadgeGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BadgeGroup badgeGroup = (BadgeGroup)serializable;

		if (badgeGroup == null) {
			Session session = null;

			try {
				session = openSession();

				badgeGroup = (BadgeGroup)session.get(BadgeGroupImpl.class,
						primaryKey);

				if (badgeGroup != null) {
					cacheResult(badgeGroup);
				}
				else {
					entityCache.putResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
						BadgeGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
					BadgeGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return badgeGroup;
	}

	/**
	 * Returns the badge group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param badgeGroupId the primary key of the badge group
	 * @return the badge group, or <code>null</code> if a badge group with the primary key could not be found
	 */
	@Override
	public BadgeGroup fetchByPrimaryKey(long badgeGroupId) {
		return fetchByPrimaryKey((Serializable)badgeGroupId);
	}

	@Override
	public Map<Serializable, BadgeGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BadgeGroup> map = new HashMap<Serializable, BadgeGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BadgeGroup badgeGroup = fetchByPrimaryKey(primaryKey);

			if (badgeGroup != null) {
				map.put(primaryKey, badgeGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
					BadgeGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BadgeGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BADGEGROUP_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (BadgeGroup badgeGroup : (List<BadgeGroup>)q.list()) {
				map.put(badgeGroup.getPrimaryKeyObj(), badgeGroup);

				cacheResult(badgeGroup);

				uncachedPrimaryKeys.remove(badgeGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
					BadgeGroupImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the badge groups.
	 *
	 * @return the badge groups
	 */
	@Override
	public List<BadgeGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<BadgeGroup> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<BadgeGroup> findAll(int start, int end,
		OrderByComparator<BadgeGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<BadgeGroup> findAll(int start, int end,
		OrderByComparator<BadgeGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<BadgeGroup> list = null;

		if (retrieveFromCache) {
			list = (List<BadgeGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BADGEGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BADGEGROUP;

				if (pagination) {
					sql = sql.concat(BadgeGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BadgeGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BadgeGroup>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the badge groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BadgeGroup badgeGroup : findAll()) {
			remove(badgeGroup);
		}
	}

	/**
	 * Returns the number of badge groups.
	 *
	 * @return the number of badge groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BADGEGROUP);

				count = (Long)q.uniqueResult();

				finderCache.putResult(_finderPathCountAll, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BadgeGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the badge group persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
				BadgeGroupModelImpl.FINDER_CACHE_ENABLED, BadgeGroupImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
				BadgeGroupModelImpl.FINDER_CACHE_ENABLED, BadgeGroupImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(BadgeGroupModelImpl.ENTITY_CACHE_ENABLED,
				BadgeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(BadgeGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_BADGEGROUP = "SELECT badgeGroup FROM BadgeGroup badgeGroup";
	private static final String _SQL_SELECT_BADGEGROUP_WHERE_PKS_IN = "SELECT badgeGroup FROM BadgeGroup badgeGroup WHERE badgeGroupId IN (";
	private static final String _SQL_COUNT_BADGEGROUP = "SELECT COUNT(badgeGroup) FROM BadgeGroup badgeGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "badgeGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BadgeGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(BadgeGroupPersistenceImpl.class);
}
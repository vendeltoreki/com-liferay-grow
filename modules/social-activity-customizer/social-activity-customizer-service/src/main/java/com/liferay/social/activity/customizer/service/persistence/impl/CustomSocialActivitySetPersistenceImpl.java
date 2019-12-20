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

package com.liferay.social.activity.customizer.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.social.activity.customizer.exception.NoSuchCustomSocialActivitySetException;
import com.liferay.social.activity.customizer.model.CustomSocialActivitySet;
import com.liferay.social.activity.customizer.model.impl.CustomSocialActivitySetImpl;
import com.liferay.social.activity.customizer.model.impl.CustomSocialActivitySetModelImpl;
import com.liferay.social.activity.customizer.service.persistence.CustomSocialActivitySetPersistence;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the custom social activity set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CustomSocialActivitySetPersistenceImpl
	extends BasePersistenceImpl<CustomSocialActivitySet>
	implements CustomSocialActivitySetPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CustomSocialActivitySetUtil</code> to access the custom social activity set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CustomSocialActivitySetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CustomSocialActivitySetPersistenceImpl() {
		setModelClass(CustomSocialActivitySet.class);

		setModelImplClass(CustomSocialActivitySetImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the custom social activity set in the entity cache if it is enabled.
	 *
	 * @param customSocialActivitySet the custom social activity set
	 */
	@Override
	public void cacheResult(CustomSocialActivitySet customSocialActivitySet) {
		entityCache.putResult(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetImpl.class,
			customSocialActivitySet.getPrimaryKey(), customSocialActivitySet);

		customSocialActivitySet.resetOriginalValues();
	}

	/**
	 * Caches the custom social activity sets in the entity cache if it is enabled.
	 *
	 * @param customSocialActivitySets the custom social activity sets
	 */
	@Override
	public void cacheResult(
		List<CustomSocialActivitySet> customSocialActivitySets) {

		for (CustomSocialActivitySet customSocialActivitySet :
				customSocialActivitySets) {

			if (entityCache.getResult(
					CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
					CustomSocialActivitySetImpl.class,
					customSocialActivitySet.getPrimaryKey()) == null) {

				cacheResult(customSocialActivitySet);
			}
			else {
				customSocialActivitySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all custom social activity sets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomSocialActivitySetImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the custom social activity set.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomSocialActivitySet customSocialActivitySet) {
		entityCache.removeResult(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetImpl.class,
			customSocialActivitySet.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CustomSocialActivitySet> customSocialActivitySets) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomSocialActivitySet customSocialActivitySet :
				customSocialActivitySets) {

			entityCache.removeResult(
				CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
				CustomSocialActivitySetImpl.class,
				customSocialActivitySet.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
				CustomSocialActivitySetImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new custom social activity set with the primary key. Does not add the custom social activity set to the database.
	 *
	 * @param id the primary key for the new custom social activity set
	 * @return the new custom social activity set
	 */
	@Override
	public CustomSocialActivitySet create(long id) {
		CustomSocialActivitySet customSocialActivitySet =
			new CustomSocialActivitySetImpl();

		customSocialActivitySet.setNew(true);
		customSocialActivitySet.setPrimaryKey(id);

		return customSocialActivitySet;
	}

	/**
	 * Removes the custom social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the custom social activity set
	 * @return the custom social activity set that was removed
	 * @throws NoSuchCustomSocialActivitySetException if a custom social activity set with the primary key could not be found
	 */
	@Override
	public CustomSocialActivitySet remove(long id)
		throws NoSuchCustomSocialActivitySetException {

		return remove((Serializable)id);
	}

	/**
	 * Removes the custom social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the custom social activity set
	 * @return the custom social activity set that was removed
	 * @throws NoSuchCustomSocialActivitySetException if a custom social activity set with the primary key could not be found
	 */
	@Override
	public CustomSocialActivitySet remove(Serializable primaryKey)
		throws NoSuchCustomSocialActivitySetException {

		Session session = null;

		try {
			session = openSession();

			CustomSocialActivitySet customSocialActivitySet =
				(CustomSocialActivitySet)session.get(
					CustomSocialActivitySetImpl.class, primaryKey);

			if (customSocialActivitySet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomSocialActivitySetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(customSocialActivitySet);
		}
		catch (NoSuchCustomSocialActivitySetException nsee) {
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
	protected CustomSocialActivitySet removeImpl(
		CustomSocialActivitySet customSocialActivitySet) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customSocialActivitySet)) {
				customSocialActivitySet = (CustomSocialActivitySet)session.get(
					CustomSocialActivitySetImpl.class,
					customSocialActivitySet.getPrimaryKeyObj());
			}

			if (customSocialActivitySet != null) {
				session.delete(customSocialActivitySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customSocialActivitySet != null) {
			clearCache(customSocialActivitySet);
		}

		return customSocialActivitySet;
	}

	@Override
	public CustomSocialActivitySet updateImpl(
		CustomSocialActivitySet customSocialActivitySet) {

		boolean isNew = customSocialActivitySet.isNew();

		Session session = null;

		try {
			session = openSession();

			if (customSocialActivitySet.isNew()) {
				session.save(customSocialActivitySet);

				customSocialActivitySet.setNew(false);
			}
			else {
				customSocialActivitySet =
					(CustomSocialActivitySet)session.merge(
						customSocialActivitySet);
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
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetImpl.class,
			customSocialActivitySet.getPrimaryKey(), customSocialActivitySet,
			false);

		customSocialActivitySet.resetOriginalValues();

		return customSocialActivitySet;
	}

	/**
	 * Returns the custom social activity set with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom social activity set
	 * @return the custom social activity set
	 * @throws NoSuchCustomSocialActivitySetException if a custom social activity set with the primary key could not be found
	 */
	@Override
	public CustomSocialActivitySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomSocialActivitySetException {

		CustomSocialActivitySet customSocialActivitySet = fetchByPrimaryKey(
			primaryKey);

		if (customSocialActivitySet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomSocialActivitySetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return customSocialActivitySet;
	}

	/**
	 * Returns the custom social activity set with the primary key or throws a <code>NoSuchCustomSocialActivitySetException</code> if it could not be found.
	 *
	 * @param id the primary key of the custom social activity set
	 * @return the custom social activity set
	 * @throws NoSuchCustomSocialActivitySetException if a custom social activity set with the primary key could not be found
	 */
	@Override
	public CustomSocialActivitySet findByPrimaryKey(long id)
		throws NoSuchCustomSocialActivitySetException {

		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the custom social activity set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the custom social activity set
	 * @return the custom social activity set, or <code>null</code> if a custom social activity set with the primary key could not be found
	 */
	@Override
	public CustomSocialActivitySet fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the custom social activity sets.
	 *
	 * @return the custom social activity sets
	 */
	@Override
	public List<CustomSocialActivitySet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom social activity sets
	 * @param end the upper bound of the range of custom social activity sets (not inclusive)
	 * @return the range of custom social activity sets
	 */
	@Override
	public List<CustomSocialActivitySet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom social activity sets
	 * @param end the upper bound of the range of custom social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of custom social activity sets
	 */
	@Override
	public List<CustomSocialActivitySet> findAll(
		int start, int end,
		OrderByComparator<CustomSocialActivitySet> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CustomSocialActivitySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom social activity sets
	 * @param end the upper bound of the range of custom social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of custom social activity sets
	 */
	@Override
	public List<CustomSocialActivitySet> findAll(
		int start, int end,
		OrderByComparator<CustomSocialActivitySet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CustomSocialActivitySet> list = null;

		if (useFinderCache) {
			list = (List<CustomSocialActivitySet>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMSOCIALACTIVITYSET);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMSOCIALACTIVITYSET;

				sql = sql.concat(
					CustomSocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<CustomSocialActivitySet>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the custom social activity sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomSocialActivitySet customSocialActivitySet : findAll()) {
			remove(customSocialActivitySet);
		}
	}

	/**
	 * Returns the number of custom social activity sets.
	 *
	 * @return the number of custom social activity sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
					_SQL_COUNT_CUSTOMSOCIALACTIVITYSET);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "id_";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CUSTOMSOCIALACTIVITYSET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CustomSocialActivitySetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the custom social activity set persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			CustomSocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			CustomSocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CustomSocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			CustomSocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(CustomSocialActivitySetImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CUSTOMSOCIALACTIVITYSET =
		"SELECT customSocialActivitySet FROM CustomSocialActivitySet customSocialActivitySet";

	private static final String _SQL_COUNT_CUSTOMSOCIALACTIVITYSET =
		"SELECT COUNT(customSocialActivitySet) FROM CustomSocialActivitySet customSocialActivitySet";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"customSocialActivitySet.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CustomSocialActivitySet exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CustomSocialActivitySetPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id"});

}
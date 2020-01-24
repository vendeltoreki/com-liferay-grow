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

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.social.activity.customizer.service.persistence.CustomSocialActivitySetFinder;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;

import java.util.List;

/**
 * @author Istvan Sajtos
 */
public class CustomSocialActivitySetFinderImpl
	extends CustomSocialActivitySetFinderBaseImpl
	implements CustomSocialActivitySetFinder {

	public static final String COUNT_BY_USERID_CLASSNAMEID =
		CustomSocialActivitySetFinder.class.getName() + ".countByU_C";

	public static final String COUNT_BY_USERID_CLASSNAMEID_TYPE =
		CustomSocialActivitySetFinder.class.getName() + ".countByU_C_T";

	public static final String FIND_BY_USERID_CLASSNAMEID =
		CustomSocialActivitySetFinder.class.getName() + ".findByU_C";

	public static final String FIND_BY_USERID_CLASSNAMEID_TYPE =
		CustomSocialActivitySetFinder.class.getName() + ".findByU_C_T";

	public int countByU_C(long userId, long classNameId) {
		Session session = null;

		try {
			session = openSession();

			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery activitySetQuery = DynamicQueryFactoryUtil.forClass(
				SocialActivitySet.class, "classPKView", classLoader);

			activitySetQuery.add(
				RestrictionsFactoryUtil.and(
					RestrictionsFactoryUtil.eq("userId", userId),
					RestrictionsFactoryUtil.eq("classNameId", classNameId)));

			long count = SocialActivitySetLocalServiceUtil.dynamicQueryCount(
				activitySetQuery);

			return Math.toIntExact(count);
		}
		catch (Exception e) {
			try {
				throw new SystemException(e);
			}
			catch (SystemException se) {
				se.printStackTrace();
			}
		}
		finally {
			closeSession(session);
		}

		return 0;
	}

	// Switching to DynamicQuery temporarily. (See https://issues.liferay.com/browse/GROW-979)

	/*	public int countByU_C(long userId, long classNameId) {
			Session session = null;

			try {
				session = CustomFinderHelperUtil.openPortalSession();
				String sql = _customSQL.get(
					CustomSocialActivitySetFinderImpl.class,
					COUNT_BY_USERID_CLASSNAMEID);

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.setCacheable(false);
				q.addEntity(
					"SocialActivitySet",
					CustomFinderHelperUtil.getImplClass(
						"com.liferay.portlet.social.model.impl." +
							"SocialActivitySetImpl",
						PortalClassLoaderUtil.getClassLoader()));
				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);
				qPos.add(classNameId);
				Iterator<Long> itr = q.iterate();

				// The SELECT COUNT(*) ... query doesn't work probably because of
				// Hibernate mapping problems due to subquery. Querying the records
				// instead to be able to determine the count.

				//if (itr.hasNext()) {
				//	Long count = itr.next();
				//	if (count != null) {
				//		return count.intValue();
				//	}
				//}
				List<SocialActivitySet> list =
					(List<SocialActivitySet>)QueryUtil.list(
						q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					return list.size();
			}
			catch (Exception e) {
				try {
					throw new SystemException(e);
				}
				catch (SystemException se) {
					se.printStackTrace();
				}
			}
			finally {
				closeSession(session);
			}

			return 0;
		}*/

	public int countByU_C_T(long userId, long classNameId, int[] types) {
		Session session = null;

		try {
			session = openSession();

			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery activitySetQuery = DynamicQueryFactoryUtil.forClass(
				SocialActivitySet.class, "classPKView", classLoader);

			activitySetQuery.add(
				RestrictionsFactoryUtil.and(
					RestrictionsFactoryUtil.and(
						RestrictionsFactoryUtil.eq("userId", userId),
						RestrictionsFactoryUtil.eq("classNameId", classNameId)),
					RestrictionsFactoryUtil.in(
						"type", ArrayUtil.toArray(types))));

			long count = SocialActivitySetLocalServiceUtil.dynamicQueryCount(
				activitySetQuery);

			return Math.toIntExact(count);
		}
		catch (Exception e) {
			try {
				throw new SystemException(e);
			}
			catch (SystemException se) {
				se.printStackTrace();
			}
		}
		finally {
			closeSession(session);
		}

		return 0;
	}

	// Switching to DynamicQuery temporarily. (See https://issues.liferay.com/browse/GROW-979)

	/*	public int countByU_C_T(long userId, long classNameId, long[] types) {
			Session session = null;

			try {
				session = CustomFinderHelperUtil.openPortalSession();
				String sql = _customSQL.get(
					CustomSocialActivitySetFinderImpl.class,
					COUNT_BY_USERID_CLASSNAMEID_TYPE);

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.setCacheable(false);
				q.addEntity(
					"SocialActivitySet",
					CustomFinderHelperUtil.getImplClass(
						"com.liferay.portlet.social.model.impl." +
							"SocialActivitySetImpl",
						PortalClassLoaderUtil.getClassLoader()));
				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);
				qPos.add(classNameId);
				qPos.add(StringUtil.merge(types));
				Iterator<Long> itr = q.iterate();

				// The SELECT COUNT(*) ... query doesn't work probably because of
				// Hibernate mapping problems due to subquery. Querying the records
				// instead to be able to determine the count.

				//if (itr.hasNext()) {
				//	Long count = itr.next();
				//	if (count != null) {
				//		return count.intValue();
				//	}
				//}
				List<SocialActivitySet> list =
					(List<SocialActivitySet>)QueryUtil.list(
						q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				return list.size();
			}
			catch (Exception e) {
				try {
					throw new SystemException(e);
				}
				catch (SystemException se) {
					se.printStackTrace();
				}
			}
			finally {
				closeSession(session);
			}

			return 0;
		}*/

	public List<SocialActivitySet> findByU_C(
		long userId, long classNameId, int begin, int end) {

		Session session = null;

		try {
			session = openSession();

			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery activitySetQuery = DynamicQueryFactoryUtil.forClass(
				SocialActivitySet.class, "classPKView", classLoader);

			/*			ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

						projectionList.add(PropertyFactoryUtil.forName("activitySetId"));
						projectionList.add(PropertyFactoryUtil.forName("classPK"));
						projectionList.add(PropertyFactoryUtil.forName("modifiedDate").max());
						projectionList.add(ProjectionFactoryUtil.groupProperty("classPK"));*/

			activitySetQuery.add(
				RestrictionsFactoryUtil.and(
					RestrictionsFactoryUtil.eq("userId", userId),
					RestrictionsFactoryUtil.eq("classNameId", classNameId)));
			//				.setProjection(projectionList);

			/*classPKQuery.add(RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.eq("userId", userId)
			, RestrictionsFactoryUtil.eq("classNameId", classNameId))
			.setProjection(ProjectionFactoryUtil.groupProperty("classPK"));*/
			/*
						DynamicQuery activitySetQuery =
							DynamicQueryFactoryUtil.forClass(
								SocialActivitySet.class, "socialActivitySet", classLoader);

						activitySetQuery.add(RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.eq("classPK", ), RestrictionsFactryUtil.eq("modifiedDate", ));
			*/
			List<SocialActivitySet> activitySets =
				SocialActivitySetLocalServiceUtil.dynamicQuery(
					activitySetQuery, begin, end);
			return activitySets;
		}
		catch (Exception e) {
			try {
				throw new SystemException(e);
			}
			catch (SystemException se) {
				se.printStackTrace();
			}
		}
		finally {
			closeSession(session);
		}

		return null;
	}

	// Switching to DynamicQuery temporarily. (See https://issues.liferay.com/browse/GROW-979)

	/*	public List<SocialActivitySet> findByU_C(
			long userId, long classNameId, int begin, int end) {

			Session session = null;

			try {
				session = CustomFinderHelperUtil.openPortalSession();
				String sql = _customSQL.get(
					CustomSocialActivitySetFinderImpl.class,
					FIND_BY_USERID_CLASSNAMEID);

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.setCacheable(false);
				q.addEntity(
					"SocialActivitySet",
					CustomFinderHelperUtil.getImplClass(
						"com.liferay.portlet.social.model.impl." +
							"SocialActivitySetImpl",
						PortalClassLoaderUtil.getClassLoader()));
				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);
				qPos.add(classNameId);

				return (List<SocialActivitySet>)QueryUtil.list(
					q, getDialect(), begin, end);
			}
			catch (Exception e) {
				try {
					throw new SystemException(e);
				}
				catch (SystemException se) {
					se.printStackTrace();
				}
			}
			finally {
				closeSession(session);
			}

			return null;
		}*/

	public List<SocialActivitySet> findByU_C_T(
		long userId, long classNameId, int[] types, int begin, int end) {

		Session session = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			DynamicQuery activitySetQuery = DynamicQueryFactoryUtil.forClass(
				SocialActivitySet.class, "classPKView", classLoader);

			activitySetQuery.add(
				RestrictionsFactoryUtil.and(
					RestrictionsFactoryUtil.and(
						RestrictionsFactoryUtil.eq("userId", userId),
						RestrictionsFactoryUtil.eq("classNameId", classNameId)),
					RestrictionsFactoryUtil.in(
						"type", ArrayUtil.toArray(types))));

			List<SocialActivitySet> activitySets =
				SocialActivitySetLocalServiceUtil.dynamicQuery(
					activitySetQuery, begin, end);

			return activitySets;
		}
		catch (Exception e) {
			try {
				throw new SystemException(e);
			}
			catch (SystemException se) {
				se.printStackTrace();
			}
		}
		finally {
			closeSession(session);
		}

		return null;
	}

	// Switching to DynamicQuery temporarily. (See https://issues.liferay.com/browse/GROW-979)

	/*	public List<SocialActivitySet> findByU_C_T(
			long userId, long classNameId, long[] types, int begin, int end) {

			Session session = null;

			try {
				session = CustomFinderHelperUtil.openPortalSession();
				String sql = _customSQL.get(
					CustomSocialActivitySetFinderImpl.class,
					FIND_BY_USERID_CLASSNAMEID_TYPE);

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.setCacheable(false);
				q.addEntity(
					"SocialActivitySet",
					CustomFinderHelperUtil.getImplClass(
						"com.liferay.portlet.social.model.impl." +
							"SocialActivitySetImpl",
						PortalClassLoaderUtil.getClassLoader()));
				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);
				qPos.add(classNameId);
				qPos.add(StringUtil.merge(types));

				return (List<SocialActivitySet>)QueryUtil.list(
					q, getDialect(), begin, end);
			}
			catch (Exception e) {
				try {
					throw new SystemException(e);
				}
				catch (SystemException se) {
					se.printStackTrace();
				}
			}
			finally {
				closeSession(session);
			}

			return null;
		}*/

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}
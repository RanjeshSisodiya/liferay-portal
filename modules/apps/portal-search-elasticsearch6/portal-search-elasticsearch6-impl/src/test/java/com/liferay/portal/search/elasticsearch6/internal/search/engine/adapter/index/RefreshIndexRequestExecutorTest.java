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

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.TestElasticsearchConnectionManager;
import com.liferay.portal.search.engine.adapter.index.RefreshIndexRequest;

import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class RefreshIndexRequestExecutorTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			PutMappingIndexRequestExecutorTest.class.getSimpleName());

		_elasticsearchFixture.setUp();

		_elasticsearchConnectionManager =
			new TestElasticsearchConnectionManager(_elasticsearchFixture);
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testIndexRequestTranslation() {
		RefreshIndexRequest refreshIndexRequest = new RefreshIndexRequest(
			_INDEX_NAME);

		RefreshIndexRequestExecutorImpl refreshIndexRequestExecutorImpl =
			new RefreshIndexRequestExecutorImpl() {
				{
					elasticsearchConnectionManager =
						_elasticsearchConnectionManager;

					indexRequestShardFailureTranslator =
						new IndexRequestShardFailureTranslatorImpl();
				}
			};

		RefreshRequestBuilder refreshRequestBuilder =
			refreshIndexRequestExecutorImpl.createRefreshRequestBuilder(
				refreshIndexRequest);

		RefreshRequest refreshRequest = refreshRequestBuilder.request();

		Assert.assertArrayEquals(
			new String[] {_INDEX_NAME}, refreshRequest.indices());
	}

	private static final String _INDEX_NAME = "test_request_index";

	private ElasticsearchConnectionManager _elasticsearchConnectionManager;
	private ElasticsearchFixture _elasticsearchFixture;

}
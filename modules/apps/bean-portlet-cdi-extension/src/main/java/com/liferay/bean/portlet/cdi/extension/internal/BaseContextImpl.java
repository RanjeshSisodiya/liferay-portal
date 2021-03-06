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

package com.liferay.bean.portlet.cdi.extension.internal;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.spi.Bean;

/**
 * @author Neil Griffin
 */
public abstract class BaseContextImpl implements Context {

	@Override
	public <T> T get(Contextual<T> contextual) {
		return get(contextual, null);
	}

	@Override
	public boolean isActive() {

		// TODO

		return false;
	}

	protected String getAttributeName(Bean<?> bean) {

		// TODO

		return null;
	}

}
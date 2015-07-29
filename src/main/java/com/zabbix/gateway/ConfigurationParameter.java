/*
** Zabbix
** Copyright (C) 2001-2015 Zabbix SIA
**
** This program is free software; you can redistribute it and/or modify
** it under the terms of the GNU General Public License as published by
** the Free Software Foundation; either version 2 of the License, or
** (at your option) any later version.
**
** This program is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
** GNU General Public License for more details.
**
** You should have received a copy of the GNU General Public License
** along with this program; if not, write to the Free Software
** Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
**/

package com.zabbix.gateway;

import java.io.File;
import java.net.InetAddress;

class ConfigurationParameter
{
	public static enum ParameterType {
		INTEGER,
		INETADDRESS,
		FILE;

	}

	private final String name;
	private ParameterType type;
	private Object value;
	private InputValidator validator;
	private PostInputValidator postValidator;

	public ConfigurationParameter(String name,ParameterType type, Object defaultValue, InputValidator validator, PostInputValidator postValidator)
	{
		this.name = name;
		this.type = type;
		this.value = defaultValue;
		this.validator = validator;
		this.postValidator = postValidator;
	}

	public String getName()
	{
		return name;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(String text)
	{
		 Object userValue = null;

		try
		{
			switch (type)
			{
				case INTEGER:
					userValue = Integer.valueOf(text);
					break;
				case INETADDRESS:
					userValue = InetAddress.getByName(text);
					break;
				case FILE:
					userValue = new File(text);
					break;
			}
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e);
		}

		if (null != validator && !validator.validate(userValue))
			throw new IllegalArgumentException("bad value for " + name + " parameter: '" + text + "'");

		if (null != postValidator)
			postValidator.execute(userValue);

		this.value = userValue;
	}
}

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

import org.junit.*;
import static org.junit.Assert.*;

public class IntegerValidatorTest
{
	@Test
	public void testCorrectValidation()
	{
		InputValidator validator = new IntegerValidator(3, 7);

		assertFalse("(3,7) should return false for 2",validator.validate(Integer.valueOf(2)));
		assertTrue("(3,7) should return true for 3", validator.validate(Integer.valueOf(3)));
		assertTrue("(3,7) should return true for 5", validator.validate(Integer.valueOf(5)));
		assertTrue("(3,7) should return false for 7",validator.validate(Integer.valueOf(7)));
		assertFalse("(3,7) should return true for 8",validator.validate(Integer.valueOf(8)));

	}

	@Test
	public void testMinimumInterval()
	{
		new IntegerValidator(5, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInterval()
	{
		new IntegerValidator(7, 3);
	}
}

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

class IntegerValidator implements InputValidator {
    private final int lowerBound;
    private final int upperBound;

    public IntegerValidator(final int lowerBound, final int upperBound) {
        if (lowerBound > upperBound)
            throw new IllegalArgumentException("bad validation bounds: " + lowerBound + " and " + upperBound);

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean validate(Object value) {
        if (!(value instanceof Integer)) {
            return false;
        }

        final int integer = ((Integer) value).intValue();
        if (integer < lowerBound) {
            return false;
        }

        return integer <= upperBound;
    }
}

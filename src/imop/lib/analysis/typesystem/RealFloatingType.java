/*
 * Copyright (c) 2019 Aman Nougrahiya, V Krishna Nandivada, IIT Madras.
 * This file is a part of the project IMOP, licensed under the MIT license.
 * See LICENSE.md for the full text of the license.
 * 
 * The above notice shall be included in all copies or substantial
 * portions of this file.
 */
package imop.lib.analysis.typesystem;

/**
 * Superclass of all real floating types, which are three in number:
 * <ol>
 * <li>Float</li>
 * <li>Double</li>
 * <li>LongDouble</li>
 * </ol>
 * 
 * @author aman
 *
 */
public abstract class RealFloatingType extends FloatingType {

	@Override
	public boolean isRealType() {
		return true;
	}
}

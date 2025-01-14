/*
 * Copyright (c) 2019 Aman Nougrahiya, V Krishna Nandivada, IIT Madras.
 * This file is a part of the project IMOP, licensed under the MIT license.
 * See LICENSE.md for the full text of the license.
 * 
 * The above notice shall be included in all copies or substantial
 * portions of this file.
 */
package imop.lib.analysis.typesystem;

import java.util.HashSet;
import java.util.Set;

public class DoubleType extends RealFloatingType {
	private static DoubleType singleton;

	private DoubleType() {
	}

	public static DoubleType type() {
		if (singleton == null) {
			singleton = new DoubleType();
		}
		return singleton;
	}

	@Override
	public Type getComplexType() {
		return DoubleComplexType.type();
	}

	@Override
	protected String preDeclarationString() {
		String string = "double ";
		return string;
	}

	@Override
	protected String preString() {
		return "double ";
	}

	@Override
	public Set<Type> getAllTypes() {
		Set<Type> typeSet = new HashSet<>();
		typeSet.add(DoubleType.type());
		return typeSet;
	}

	@Override
	public boolean isCompatibleWith(Type t) {
		if (t == this) {
			return true;
		}
		return false;
	}
}

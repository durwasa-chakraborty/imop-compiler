/*
 * Copyright (c) 2019 Aman Nougrahiya, V Krishna Nandivada, IIT Madras.
 * This file is a part of the project IMOP, licensed under the MIT license.
 * See LICENSE.md for the full text of the license.
 *
 * The above notice shall be included in all copies or substantial
 * portions of this file.
 */
package demo.backupCGO.demo0;

import imop.parser.Program;

public class Demo0 {

	public static void main(String[] args) {
		args = new String[] { "-f", "runner/pldi-eg/example.c", "-nru" };
		Program.parseNormalizeInput(args);
		System.out.println("Setup successful!");
	}

}

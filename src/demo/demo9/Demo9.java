/*
 * Copyright (c) 2019 Aman Nougrahiya, V Krishna Nandivada, IIT Madras.
 * This file is a part of the project IMOP, licensed under the MIT license.
 * See LICENSE.md for the full text of the license.
 *
 * The above notice shall be included in all copies or substantial
 * portions of this file.
 */
package demo.demo9;

import imop.ast.node.external.*;
import imop.lib.util.DumpSnapshot;
import imop.lib.util.Misc;
import imop.parser.Program;

public class Demo9 {

    public static void main(String[] args) {
        args = new String[]{"-f", "runner/cgo-eg/example.c", "-nru"};
        Program.parseNormalizeInput(args);
        /*
         * Check if a barrier-directive is required to preserve
         * dependences among
         * phases across it. If not, then delete the barrier.
         * For any given barrier, get the set of phases that it may end, and the
         * set of phases that may start after it.
         * For each pair of phases from the sets in the last step, see if the
         * pair conflicts, i.e. see if there exists any conflicting accesses
         * between two phases of the pair.
         * If no conflicts are found across a barrier, remove it from the
         * program.
         */
        demo9();
        DumpSnapshot.dumpRoot("final");
        System.out.println(Program.getRoot());
    }

    public static void demo9() {
        for (BarrierDirective barrier : Misc.getInheritedEncloseeList(Program.getRoot(), BarrierDirective.class)) {
            //		    Set<Phase> allPhaseSet = new HashSet<>();
            //			for (ParallelConstruct parConsNode : Misc.getExactEnclosee(Program.getRoot(),
            //					ParallelConstruct.class)) {
            //				TODO: allPhaseSet.addAll((Collection<? extends Phase>) parConsNode.getInfo().CONNECTED-PH());
            //			}
            //			TODO: Set<Phase> phasesAbove = (Set<Phase>) barrier.getInfo().getNodePhaseInfo().PH();
            //			Set<Phase> phasesBelow = new HashSet<>();
            //			for (Phase ph : allPhaseSet) {
            //				TODO: for (AbstractPhasePointable bpp : ph.BPP()) {
            //					TODO: if (bpp.NODE() == barrier) {
            //						phasesBelow.add(ph);
            //					}
            //				}
            //			}
            //
            //			boolean removable = true;
            //			outer: for (Phase phAbove : phasesAbove) {
            //				for (Phase phBelow : phasesBelow) {
            //					if (phAbove == phBelow) {
            //						continue;
            //					}
            //					for (Node stmt1 : phAbove.getNodeSet()) {
            //						for (Node stmt2 : phBelow.getNodeSet()) {
            //							CellSet reads1 = new CellSet(stmt1.getInfo().getSharedReads());
            //							CellSet reads2 = new CellSet(stmt2.getInfo().getSharedReads());
            //							CellSet writes1 = new CellSet(stmt1.getInfo().getSharedWrites());
            //							CellSet writes2 = new CellSet(stmt2.getInfo().getSharedWrites());
            //							if (reads1.overlapsWith(writes2) || reads2.overlapsWith(writes1)
            //									|| writes1.overlapsWith(writes2)) {
            //								removable = false;
            //								break outer;
            //							}
            //						}
            //					}
            //				}
            //			}
            //			if (removable) {
            //				TODO: CompoundStatement enclosingCS = (CompoundStatement) Misc.GET-CS(barrier);
            //				CompoundStatementCFGInfo csCFGInfo = enclosingCS.getInfo().getCFGInfo();
            //				TODO: csCFGInfo.REMOVE(barrier);
            //				demo9();
            //				return;
            //			}
        }
    }

}

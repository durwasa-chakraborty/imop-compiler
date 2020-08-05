/*
 * Copyright (c) 2019 Aman Nougrahiya, V Krishna Nandivada, IIT Madras.
 * This file is a part of the project IMOP, licensed under the MIT license.
 * See LICENSE.md for the full text of the license.
 * 
 * The above notice shall be included in all copies or substantial
 * portions of this file.
 */
package imop.lib.analysis;

import java.util.HashSet;
import java.util.Set;

import imop.ast.node.external.Node;
import imop.lib.analysis.BasePA.StableStatus;
import imop.lib.cfg.Edge;

public abstract class BasePA {
	public static Set<BasePA> allAbstractions = new HashSet<>();
	protected Set<Node> addedNodes = new HashSet<>();
	protected Set<Node> removedNodes = new HashSet<>();
	protected Set<Edge> addedEdges = new HashSet<>();
	protected Set<Edge> removedEdges = new HashSet<>();
	protected StableStatus stableStatus = StableStatus.INIT;
	protected final StabilizationMode stabilizationMode;

	public static enum StableStatus {
		STABLE, UNSTABLE, PROCESSING, INIT
	}

	public static enum StabilizationMode {
		EGINV,
		EGUPD,
		LZINV, // This is considered to be the default mode of stabilization.
		LZUPD
	}

	public BasePA() {
		this.stabilizationMode = StabilizationMode.LZINV;
		BasePA.allAbstractions.add(this);
	}

	/**
	 * Constructs a program-abstraction with {@code stabilizationMode} as its
	 * mode of stabilization. The default, LZINV is used.
	 * 
	 * @param stabilizationMode
	 *            selected mode of stabilization for this program-abstraction;
	 *            default value: LZINV.
	 */
	public BasePA(StabilizationMode stabilizationMode) {
		this.stabilizationMode = stabilizationMode;
		BasePA.allAbstractions.add(this);
	}

	public static void modelNodeAddition(Node addedNode) {
		for (BasePA pa : BasePA.allAbstractions) {
			boolean changed = pa.removedNodes.remove(addedNode);
			if (!changed) {
				pa.stableStatus = pa.addedNodes.add(addedNode) ? StableStatus.UNSTABLE : pa.stableStatus;
			}
		}
	}

	public static void modelNodeSetAddition(Set<Node> addedNodes) {
		for (BasePA pa : BasePA.allAbstractions) {
			for (Node n : addedNodes) {
				boolean changed = pa.removedNodes.remove(n);
				if (!changed) {
					pa.stableStatus = pa.addedNodes.add(n) ? StableStatus.UNSTABLE : pa.stableStatus;
				}
			}
		}
	}

	public static void modelNodeRemoval(Node removedNode) {
		for (BasePA pa : BasePA.allAbstractions) {
			boolean changed = pa.addedNodes.remove(removedNode);
			if (!changed) {
				pa.stableStatus = pa.removedNodes.add(removedNode) ? StableStatus.UNSTABLE : pa.stableStatus;
			}
		}
	}

	public static void modelNodeSetRemoval(Set<Node> removedNodes) {
		for (BasePA pa : BasePA.allAbstractions) {
			for (Node n : removedNodes) {
				boolean changed = pa.addedNodes.remove(n);
				if (!changed) {
					pa.stableStatus = pa.removedNodes.add(n) ? StableStatus.UNSTABLE : pa.stableStatus;
				}
			}
		}
	}

	public static void modelEdgeAddition(Edge addedEdge) {
		for (BasePA pa : BasePA.allAbstractions) {
			boolean changed = pa.removedEdges.remove(addedEdge);
			if (!changed) {
				pa.stableStatus = pa.addedEdges.add(addedEdge) ? StableStatus.UNSTABLE : pa.stableStatus;
			}
		}
	}

	public static void modelEdgeSetAddition(Set<Edge> addedEdges) {
		for (BasePA pa : BasePA.allAbstractions) {
			for (Edge n : addedEdges) {
				boolean changed = pa.removedEdges.remove(n);
				if (!changed) {
					pa.stableStatus = pa.addedEdges.add(n) ? StableStatus.UNSTABLE : pa.stableStatus;
				}
			}
		}
	}

	public static void modelEdgeRemoval(Edge removedEdge) {
		for (BasePA pa : BasePA.allAbstractions) {
			boolean changed = pa.addedEdges.remove(removedEdge);
			if (!changed) {
				pa.stableStatus = pa.removedEdges.add(removedEdge) ? StableStatus.UNSTABLE : pa.stableStatus;
			}
		}
	}

	public static void modelEdgeSetRemoval(Set<Edge> removedEdges) {
		for (BasePA pa : BasePA.allAbstractions) {
			for (Edge n : removedEdges) {
				boolean changed = pa.addedEdges.remove(n);
				if (!changed) {
					pa.stableStatus = pa.removedEdges.add(n) ? StableStatus.UNSTABLE : pa.stableStatus;
				}
			}
		}
	}

	public abstract void commonPre();

	public abstract void commonPost();

	public abstract void handleNodeAddition(Node n);

	public abstract void handleNodeRemoval(Node n);

	public abstract void handleEdgeAddition(Edge e);

	public abstract void handleEdgeRemoval(Edge e);

	public void stabilizer() {
		if (this.stableStatus == StableStatus.STABLE) {
			return;
		}
		this.stableStatus = StableStatus.PROCESSING;
		this.commonPre();
		for (Node n : this.addedNodes) {
			this.handleNodeAddition(n);
		}
		this.addedNodes.clear();
		for (Node n : this.removedNodes) {
			this.handleNodeRemoval(n);
		}
		this.removedNodes.clear();
		for (Edge e : this.addedEdges) {
			this.handleEdgeAddition(e);
		}
		this.addedEdges.clear();
		for (Edge e : this.removedEdges) {
			this.handleEdgeRemoval(e);
		}
		this.removedEdges.clear();
		this.commonPost();
		this.stableStatus = StableStatus.STABLE;
	}
}

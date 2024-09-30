import java.util.List;
public interface FlightFinderAEInterface<NodeType,EdgeType extends Number> extends GraphADT<NodeType,EdgeType>
{
	public boolean insertNode(NodeType data);
	public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight);
	public int getNodeCount();
	public boolean removeEdge(NodeType pred, NodeType succ);
	public boolean containsEdge(NodeType pred, NodeType succ);
	public EdgeType getEdge(NodeType pred, NodeType succ);
	public int getEdgeCount();
	public List<NodeType> shortestPathData(NodeType start, NodeType end);
	public double shortestPathCost(NodeType start, NodeType end);
	public double shortestPathCostWithRequiredRestraints(NodeType start, NodeType end, List<NodeType> restraints);
	public double shortestPathCostWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints);
	public List<NodeType> shortestPathDataWithRestrictedRestraints(NodeType start, NodeType end, List<NodeType> restraints);
	public List<NodeType> shortestPathDataWithRequiredRestraints(NodeType start, NodeType end, List<NodeType> restraints);
	public List<NodeType> listOfAllNodes();
}

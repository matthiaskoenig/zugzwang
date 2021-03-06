/*package org.cytoscape.zugzwang.internal.input.handler.commands;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.zugzwang.internal.data.GraphicsData;
import org.cytoscape.zugzwang.internal.input.handler.MouseCommandAdapter;
import org.cytoscape.zugzwang.internal.task.PopupMenuCreator;

public class PopupMenuMouseCommand extends MouseCommandAdapter {

	private PopupMenuCreator popupMenuCreator = null;
	private final GraphicsData graphicsData;
	
	public PopupMenuMouseCommand(GraphicsData graphicsData) {
		this.graphicsData = graphicsData;
	}

	@Override
	public void clicked(int x, int y) {
		if (popupMenuCreator == null) {
			popupMenuCreator = new PopupMenuCreator(graphicsData.getTaskManager());
		}
		
		CyNetworkView networkView = graphicsData.getNetworkView();
		
		CyNode node = networkView.getModel().getNode(graphicsData.getSelectionData().getHoverNodeIndex());
		CyEdge edge = networkView.getModel().getEdge(graphicsData.getSelectionData().getHoverEdgeIndex());
		
		JPopupMenu popupMenu = null;
		
		if (node != null) {
			View<CyNode> nodeView = networkView.getNodeView(node);
			
			popupMenu = popupMenuCreator.createNodeMenu(nodeView, 
					networkView, graphicsData.getVisualLexicon(), 
					graphicsData.getTaskFactoryListener().getNodeViewTaskFactories());
		} else if (edge != null) {
			View<CyEdge> edgeView = networkView.getEdgeView(edge);
			
			popupMenu = popupMenuCreator.createEdgeMenu(edgeView, 
					networkView, graphicsData.getVisualLexicon(), 
					graphicsData.getTaskFactoryListener().getEdgeViewTaskFactories());
		} else {
			popupMenu = popupMenuCreator.createNetworkMenu(networkView, 
					graphicsData.getVisualLexicon(),
					graphicsData.getTaskFactoryListener().getNetworkViewTaskFactories());
		}
		
		if (popupMenu != null) {
			// This is kind of a hack, but we have to convert BACK to window coordinates, this at least keeps a consistent interface.
			Point p = new Point(x, y);
			graphicsData.getPixelConverter().convertToWindowUnits(p);
			popupMenu.show(graphicsData.getContainer(), p.x, p.y);
		}
	}

}
*/
package org.cytoscape.zugzwang.internal.viewmodel;

import java.util.HashMap;
import java.util.Map;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;

/**
 * Manages default values for visual properties. When a visual property value needs 
 * to be obtained for some node/edge/network, and it is marked as default, the
 * request is routed to this object.
 */
public class DefaultValueVault 
{	
	// Assumes VisualProperty ID names are unique
	private Map<String, VisualPropertyValue<?>> nodeDefaultValues;
	private Map<String, VisualPropertyValue<?>> edgeDefaultValues;
	private Map<String, VisualPropertyValue<?>> networkDefaultValues;
	
	private Map<Class<? extends CyIdentifiable>, Map<String, VisualPropertyValue<?>>> defaultValueSets;
	
	private VisualLexicon visualLexicon;
	
	public DefaultValueVault(VisualLexicon visualLexicon) 
	{
		this.visualLexicon = visualLexicon;
		
		nodeDefaultValues = new HashMap<String, VisualPropertyValue<?>>();
		edgeDefaultValues = new HashMap<String, VisualPropertyValue<?>>();
		networkDefaultValues = new HashMap<String, VisualPropertyValue<?>>();
		
		defaultValueSets = new HashMap<Class<? extends CyIdentifiable>, Map<String, VisualPropertyValue<?>>>();
		defaultValueSets.put(CyNode.class, nodeDefaultValues);
		defaultValueSets.put(CyEdge.class, edgeDefaultValues);
		defaultValueSets.put(CyNetwork.class, networkDefaultValues);
	
		// Populate with default values from the relevant VisualLexicon
		populateDefaultValues();
	}
	
	/**
	 * For every property in the current visual lexicon, populate the vault 
	 * with the default value the property provides in its getDefault method.
	 */
	private void populateDefaultValues()
	{
		VisualPropertyValue<?> valueHolder;
		Class<?> targetDataType;
		
		for (VisualProperty<?> visualProperty : visualLexicon.getAllVisualProperties()) 
		{
			valueHolder = new VisualPropertyValue<Object>(visualProperty.getDefault());
			targetDataType = visualProperty.getTargetDataType();
			
			if (defaultValueSets.get(targetDataType) != null)
				defaultValueSets.get(targetDataType).put(visualProperty.getIdString(), valueHolder);
		}
	}
	
	/**
	 * Sets a new default value for a visual property, no matter if it has been set before or not.
	 * 
	 * @param visualProperty Visual property
	 * @param value New default value
	 */
	public <T, V extends T> void modifyDefaultValue(VisualProperty<? extends T> visualProperty, V value) 
	{
		Class<?> targetDataType = visualProperty.getTargetDataType();
		
		VisualPropertyValue<V> valueHolder = new VisualPropertyValue<V>(value);
		
		if (defaultValueSets.get(targetDataType) != null)
			defaultValueSets.get(targetDataType).put(visualProperty.getIdString(), valueHolder);
	}
	
	/**
	 * Obtain the default value stored for a given visual property.
	 * 
	 * @param <T> The type of the visual property's value
	 * @param visualProperty The visual property to look for a default value with
	 * @return The default value of the visual property
	 */
	public <T> T getDefaultValue(VisualProperty<T> visualProperty) 
	{
		Class<?> targetDataType = visualProperty.getTargetDataType();
		
		if (defaultValueSets.get(targetDataType) != null) 
		{
			VisualPropertyValue<T> valueHolder = (VisualPropertyValue<T>)defaultValueSets.get(targetDataType).get(visualProperty.getIdString());
		
			if (valueHolder != null)
				return valueHolder.getValue();
		}
		
		return null;
	}
}



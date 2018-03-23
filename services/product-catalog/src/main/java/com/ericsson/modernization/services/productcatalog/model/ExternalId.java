package com.ericsson.modernization.services.productcatalog.model;

/// <summary>
/// Marks the entities with an external ID.
/// </summary>
public interface ExternalId {
	/// <summary>
	/// Gets or sets the external identifier.
	/// </summary>
	/// <value>
	/// The external identifier.
	/// </value>
	String getExternalId();

	void setExternalId(String externalId);
}

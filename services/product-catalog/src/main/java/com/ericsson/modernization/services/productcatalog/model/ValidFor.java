
package com.ericsson.modernization.services.productcatalog.model;

/// <summary>
/// Marks the entities which are valid for a period of time.
/// </summary>
public interface ValidFor {
	/// <summary>
	/// Represents the period of time when entity is valid.
	/// </summary>
	TimePeriod getValidFor();

	void setValidFor(TimePeriod validFor);
}

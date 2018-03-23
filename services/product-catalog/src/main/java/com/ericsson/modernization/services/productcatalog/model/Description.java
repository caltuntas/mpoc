package com.ericsson.modernization.services.productcatalog.model;

/// <summary>
/// Marks the entites which contain name and description.
/// </summary>
public interface Description {
    /// <summary>
    /// Gets or sets the name.
    /// </summary>
    /// <value>
    /// The name.
    /// </value>
    String getName();

    /// <summary>
    /// Gets or sets the description.
    /// </summary>
    /// <value>
    /// The description.
    /// </value>
    String getDescription();
}

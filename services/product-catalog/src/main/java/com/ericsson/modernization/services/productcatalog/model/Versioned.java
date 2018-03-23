
package com.ericsson.modernization.services.productcatalog.model;

    /// <summary>
    /// Marks a versioned class
    /// </summary>
    public interface Versioned
    {
        /// <summary>
        /// Gets or sets the version number.
        /// </summary>
        /// <value>
        /// The version number.
        /// </value>
        long getVersionNumber() ;
        void setVersionNumber(long versionNumber);
    }

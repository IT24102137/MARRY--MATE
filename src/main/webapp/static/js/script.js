/**
 * Shows or hides specific vendor type fields based on the selection
 */
function showSpecificFields() {
    // Hide all specific fields first
    document.getElementById("photographerFields").style.display = "none";
    document.getElementById("catererFields").style.display = "none";
    document.getElementById("floristFields").style.display = "none";

    // Get the selected vendor type
    const vendorType = document.getElementById("vendorType").value;

    // Show the corresponding fields based on the selection
    if (vendorType === "Photographer") {
        document.getElementById("photographerFields").style.display = "block";
    } else if (vendorType === "Caterer") {
        document.getElementById("catererFields").style.display = "block";
    } else if (vendorType === "Florist") {
        document.getElementById("floristFields").style.display = "block";
    }
}

// Call the function on page load if the dropdown has a pre-selected value
document.addEventListener("DOMContentLoaded", function() {
    // Check if form exists on the page
    if (document.getElementById("vendorForm")) {
        // If the dropdown has a value (e.g., when editing a vendor), show the appropriate fields
        showSpecificFields();
    }
});
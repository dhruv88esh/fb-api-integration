//AJAX-function to refresh the data in the tables
var refresh_data = function() {
	$.get('MainServlet', function(resp) { // on success
		update_data_tables(resp);
	}).fail(function() { // on failure
		alert("Ajax call failed to refresh the data.");
	});
};


//Calling refresh data before setting the interval
refresh_data();

// Refresh the page automatically after 60 seconds
var interval = window.setInterval(refresh_data, 60000);

// Function to update the data tables
function update_data_tables(json) {
	jsonObj = JSON.parse(json);

	// Fill Meta data table
	var metaDataTable = document.getElementById('meta_data_table');
	metaDataTable.rows[0].innerHTML = "";
	var i = 0;
	$.each(jsonObj["meta_data"], function(key, value) {
		metaDataTable.rows[0].insertCell(i);
		metaDataTable.rows[0].cells[i].innerHTML = value;
		i++;
	});

	// Fill perf metrics table
	var perfMetricsTable = document.getElementById('perf_metrics_table');
	perfMetricsTable.rows[0].innerHTML = "";
	var j = 0;
	$.each(jsonObj["perf_metrics"], function(key, value) {
		perfMetricsTable.rows[0].insertCell(j);
		perfMetricsTable.rows[0].cells[j].innerHTML = value;
		j++;
	});
};
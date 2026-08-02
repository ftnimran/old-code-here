/**
 *
 * A JQUERY GOOGLE MAPS LATITUDE AND LONGITUDE LOCATION PICKER
 * version 1.2
 *
 * Supports multiple maps. Works on touchscreen. Easy to customize markup and CSS.
 *
 * To see a live demo, go to:
 * http://www.wimagguc.com/projects/jquery-latitude-longitude-picker-gmaps/
 *
 * by Richard Dancsi
 * http://www.wimagguc.com/
 *
 */

var kk = jQuery.noConflict(); 
(function(kk) {

// for ie9 doesn't support debug console >>>
if (!window.console) window.console = {};
if (!window.console.log) window.console.log = function () { };
// ^^^

kk.fn.gMapsLatLonPicker = (function() {
	//alert("Test");

	var _self = this;

	///////////////////////////////////////////////////////////////////////////////////////////////
	// PARAMETERS (MODIFY THIS PART) //////////////////////////////////////////////////////////////
	_self.params = {
		defLat : 0,
		defLng : 0,
		defZoom : 1,
		queryLocationNameWhenLatLngChanges: true,
		queryElevationWhenLatLngChanges: true,
		mapOptions : {
			mapTypeId: google.maps.MapTypeId.HYBRID,
			mapTypeControl: false,
			disableDoubleClickZoom: true,
			zoomControlOptions: true,
			streetViewControl: false
		},
		strings : {
			markerText : "Drag this Marker",
			error_empty_field : "Couldn't find coordinates for this place",
			error_no_results : "Couldn't find coordinates for this place"
		}
	};


	///////////////////////////////////////////////////////////////////////////////////////////////
	// VARIABLES USED BY THE FUNCTION (DON'T MODIFY THIS PART) ////////////////////////////////////
	_self.vars = {
		ID : null,
		LATLNG : null,
		map : null,
		marker : null,
		geocoder : null
	};

	///////////////////////////////////////////////////////////////////////////////////////////////
	// PRIVATE FUNCTIONS FOR MANIPULATING DATA ////////////////////////////////////////////////////
	var setPosition = function(position) {
		_self.vars.marker.setPosition(position);
		_self.vars.map.panTo(position);

		kk(_self.vars.cssID + ".gllpZoom").val( _self.vars.map.getZoom() );
		kk(_self.vars.cssID + ".gllpLongitude").val( parseFloat(position.lng()).toFixed(4) );
		kk(_self.vars.cssID + ".gllpLatitude").val(  parseFloat(position.lat()).toFixed(4) );

		kk(_self.vars.cssID).trigger("location_changed", kk(_self.vars.cssID));

		if (_self.params.queryLocationNameWhenLatLngChanges) {
			getLocationName(position);
		}
		if (_self.params.queryElevationWhenLatLngChanges) {
			getElevation(position);
		}
	};

	// for reverse geocoding
	var getLocationName = function(position) {
		var latlng = new google.maps.LatLng(position.lat(), position.lng());
		_self.vars.geocoder.geocode({'latLng': latlng}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK && results[1]) {
				kk(_self.vars.cssID + ".gllpLocationName").val(results[1].formatted_address);
			} else {
				kk(_self.vars.cssID + ".gllpLocationName").val("");
			}
			kk(_self.vars.cssID).trigger("location_name_changed", kk(_self.vars.cssID));
		});
	};

	// for getting the elevation value for a position
	var getElevation = function(position) {
		var latlng = new google.maps.LatLng(position.lat(), position.lng());

		var locations = [latlng];

		var positionalRequest = { 'locations': locations };

		_self.vars.elevator.getElevationForLocations(positionalRequest, function(results, status) {
			if (status == google.maps.ElevationStatus.OK) {
				if (results[0]) {
					kk(_self.vars.cssID + ".gllpElevation").val( results[0].elevation.toFixed(3));
				} else {
					kk(_self.vars.cssID + ".gllpElevation").val("");
				}
			} else {
				kk(_self.vars.cssID + ".gllpElevation").val("");
			}
			kk(_self.vars.cssID).trigger("elevation_changed", kk(_self.vars.cssID));
		});
	};

	// search function
	var performSearch = function(string, silent) {
		if (string == "") {
			if (!silent) {
				displayError( _self.params.strings.error_empty_field );
			}
			return;
		}
		_self.vars.geocoder.geocode(
			{"address": string},
			function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					kk(_self.vars.cssID + ".gllpZoom").val(16);
					_self.vars.map.setZoom( parseInt(kk(_self.vars.cssID + ".gllpZoom").val()) );
					setPosition( results[0].geometry.location );
				} else {
					if (!silent) {
						displayError( _self.params.strings.error_no_results );
					}
				}
			}
		);
	};

	// error function
	var displayError = function(message) {
//		//alert(message);
	};

	///////////////////////////////////////////////////////////////////////////////////////////////
	// PUBLIC FUNCTIONS  //////////////////////////////////////////////////////////////////////////
	var publicfunc = {

		// INITIALIZE MAP ON DIV //////////////////////////////////////////////////////////////////
		init : function(object) {

			if ( !kk(object).attr("id") ) {
				if ( kk(object).attr("name") ) {
					kk(object).attr("id", kk(object).attr("name") );
				} else {
					kk(object).attr("id", "_MAP_" + Math.ceil(Math.random() * 10000) );
				}
			}

			_self.vars.ID = kk(object).attr("id");
			_self.vars.cssID = "#" + _self.vars.ID + " ";

			_self.params.defLat  = kk(_self.vars.cssID + ".gllpLatitude").val()  ? kk(_self.vars.cssID + ".gllpLatitude").val()		: _self.params.defLat;
			_self.params.defLng  = kk(_self.vars.cssID + ".gllpLongitude").val() ? kk(_self.vars.cssID + ".gllpLongitude").val()	    : _self.params.defLng;
			_self.params.defZoom = kk(_self.vars.cssID + ".gllpZoom").val()      ? parseInt(kk(_self.vars.cssID + ".gllpZoom").val()) : _self.params.defZoom;

			_self.vars.LATLNG = new google.maps.LatLng(_self.params.defLat, _self.params.defLng);

			_self.vars.MAPOPTIONS		 = _self.params.mapOptions;
			_self.vars.MAPOPTIONS.zoom   = _self.params.defZoom;
			_self.vars.MAPOPTIONS.center = _self.vars.LATLNG;

			_self.vars.map = new google.maps.Map(kk(_self.vars.cssID + ".gllpMap").get(0), _self.vars.MAPOPTIONS);
			_self.vars.geocoder = new google.maps.Geocoder();
			_self.vars.elevator = new google.maps.ElevationService();

			_self.vars.marker = new google.maps.Marker({
				position: _self.vars.LATLNG,
				map: _self.vars.map,
				title: _self.params.strings.markerText,
				draggable: true
			});

			
			   var customPara =
			        [
			           "FORMAT=image/png8",
			           "LAYERS=	adminbdy:mpvillage"

			        ];

			//loadWMS(_self.vars.map, "http://geoportal.mp.gov.in:8080/geoserver/wms?", customPara)
			// Set position on doubleclick
			google.maps.event.addListener(_self.vars.map, 'dblclick', function(event) {
				updateSwanPopDetails();
				setPosition(event.latLng);
			});

			// Set position on marker move
			google.maps.event.addListener(_self.vars.marker, 'dragend', function(event) {
				setPosition(_self.vars.marker.position);
			});

			// Set zoom feld's value when user changes zoom on the map
			google.maps.event.addListener(_self.vars.map, 'zoom_changed', function(event) {
				kk(_self.vars.cssID + ".gllpZoom").val( _self.vars.map.getZoom() );
				kk(_self.vars.cssID).trigger("location_changed", kk(_self.vars.cssID));
			});

			// Update location and zoom values based on input field's value
			kk(_self.vars.cssID + ".gllpUpdateButton").bind("click", function() {
				var lat = kk(_self.vars.cssID + ".gllpLatitude").val();
				var lng = kk(_self.vars.cssID + ".gllpLongitude").val();
				var latlng = new google.maps.LatLng(lat, lng);
				_self.vars.map.setZoom( parseInt( kk(_self.vars.cssID + ".gllpZoom").val() ) );
				setPosition(latlng);
			});

			// Search function by search button
			kk(_self.vars.cssID + ".gllpSearchButton").bind("click", function() {
				performSearch( kk(_self.vars.cssID + ".gllpSearchField").val(), false );
			});

			// Search function by gllp_perform_search listener
			kk(document).bind("gllp_perform_search", function(event, object) {
				performSearch( kk(object).attr('string'), true );
			});

			// Zoom function triggered by gllp_perform_zoom listener
			kk(document).bind("gllp_update_fields", function(event) {
				var lat = kk(_self.vars.cssID + ".gllpLatitude").val();
				var lng = kk(_self.vars.cssID + ".gllpLongitude").val();
				var latlng = new google.maps.LatLng(lat, lng);
				_self.vars.map.setZoom( parseInt( kk(_self.vars.cssID + ".gllpZoom").val() ) );
				setPosition(latlng);
			});
		}

	}

	return publicfunc;
});

}(jQuery));

kk(document).ready( function() {
	kk(".gllpLatlonPicker").each(function() {
		kk(document).gMapsLatLonPicker().init( kk(this) );
	});
});

kk(document).bind("location_changed", function(event, object) {
	console.log("changed: " + kk(object).attr('id') );
});

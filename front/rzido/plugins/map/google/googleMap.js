import googleOptions from "./googleMapOptions"
export default{
    map : null,
    rgMapMarker : [],
    searchMarker : [],
    createMap : function(container, options){
        // if(!this.map){
            this.map = new google.maps.Map(container, googleOptions.mapOptions(options));
        // }
        return this.map;
    },
    LatLng : function(lat, lng){
        return {lat, lng};
    },
    marker : function(option){
        return new google.maps.Marker(googleOptions.markerOptions(option));
    },
    point : function(x, y){
        return new google.maps.Point(x, y);
    },
    size : function(x, y){
        return new google.maps.Size(x, y);
    },
    markerImage : function(imagaeSrc, size, options){
        let imageOptions = googleOptions.imageOptions(options);
        imageOptions.url = imagaeSrc;
        imageOptions.size = size;
        //imageOptions.anchor = this.point(size.width/2, size.height/2);
        //imageOptions.anchor = this.point(0, 0);
        return imageOptions;
    },
    addListener : function(obj, event, fn){
        obj.addListener(event, fn);
    },
    removeMarker : function(marker){
        marker.setMap(null);
    },
    getCenter : function(){
        if(this.map){
            let center = this.map.center;
            return {lat: center.lat(),lng: center.lng()};

        }
    },
    getLevel : function(){
        if(map){
            let zoom = this.map.getZoom();
            return googleOptions.level(zoom);

        }
    },
    searchBox : function(input, vue){
        let _this = this;
        let searchBox = new google.maps.places.SearchBox(input);
        //this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        this.map.addListener('bounds_changed', function() {
            searchBox.setBounds(this.getBounds());
          });

          searchBox.addListener('places_changed', function() {
            vue.searchPlaces = [];
            var places = searchBox.getPlaces();
            if (places.length == 0) {
                return;
              }
              
            let bounds = new google.maps.LatLngBounds();
            _this.searchMarker.forEach(function(marker){
                marker.setMap(null);
            });

            places.forEach(function(place){
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }

                let icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                _this.searchMarker.push(new google.maps.Marker({
                    map: _this.map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location
                }));
                vue.searchPlaces.push({name:place.name,address:place.formatted_address,lat:place.geometry.location.lat(),lng:place.geometry.location.lng()});
                

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            })
            _this.map.fitBounds(bounds);

          });
    },
    markerClusterer : function(markers){
        let _this = this;
        let clusterer = new MarkerClusterer(this.map, markers,
            {zoomOnClick:false, imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});

        _this.addListener(clusterer,'clusterclick', function(cluster){
            console.log(cluster.getMarkers());
        });
    }
}
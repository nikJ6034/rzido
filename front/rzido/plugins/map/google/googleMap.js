import googleOptions from "./googleMapOptions"
export default{
    map : null,
    createMap : function(container, options){
        this.map = new google.maps.Map(container, googleOptions.mapOptions(options));
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
    }
}
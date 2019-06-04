export default{
    createMap : function(container, options){
        return new google.maps.Map(container, options);
    },
    mapOptions : function(options){
        
        return {center : options.center, zoom : this.zoom(options.level)}

    },
    zoom : function(level){
        //구글 지도 zoom은 0~18까지 있음.
        if(level == 1){return 18}
        else if(level == 2) return 18
        else if(level == 3) return 17
        else if(level == 4) return 16
        else if(level == 4) return 15
        else if(level == 5) return 14
        else if(level == 6) return 13
        else if(level == 7) return 12
        else if(level == 8) return 11
        else if(level == 9) return 10
        else if(level == 10) return 9
        else if(level == 11) return 8
        else if(level == 12) return 7
        else if(level == 13) return 6
        else if(level == 14) return 5
    },
    LatLng : function(lat, lng){
        return {lat, lng};
    },
    marker : function(option){
        return new google.maps.Marker(option);
    }
}
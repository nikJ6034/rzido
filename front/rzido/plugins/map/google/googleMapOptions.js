export default{
    mapOptions : function(options){
        //center, level
        return {center : options.center, zoom : this.zoom(options.level)}
    },
    zoom : function(level){
        //구글 지도 zoom은 0~18까지 있음.
        if(level >= 1 && level <= 2){
            return 18;
        }else{
            return 20-level;
        }
    },
    level : function(zoom){
        if(zoom >= 1 && zoom <= 6){
            return 14
        }else{
            return 20-zoom;
        }
    },
    markerOptions : function(options){
        //position, map, image
        return {position:options.position, map : options.map, icon:options.image};
    },
    imageOptions : function(options){
        //offset
        
        return { anchor: options.offset, url : null, size : null, origin : new google.maps.Point(0, 0)};
    }
}
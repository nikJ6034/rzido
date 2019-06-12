import kakaoOptions from "./kakaoMapOptions"
export default{
    map : null,
    createMap : function(container, options){
        this.map = new daum.maps.Map(container, kakaoOptions.mapOptions(options)); //지도 생성 및 객체 리턴
        return this.map;
    },
    LatLng : function(lat, lng){
        return new daum.maps.LatLng(lat, lng);
    },
    marker : function(options){
        return new daum.maps.Marker(kakaoOptions.markerOptions(options));
    },
    point : function(x, y){
        return new daum.maps.Point(x, y)
    },
    size : function(x, y){
        return new daum.maps.Size(x, y);
    },
    markerImage : function(imagaeSrc, size, options){
        return new daum.maps.MarkerImage(imagaeSrc, size, kakaoOptions.imageOptions(options)) 
    },
    addListener : function(obj, event, fn){
        daum.maps.event.addListener(obj, event, fn);
    },
    removeMarker : function(marker){
        marker.setMap(null);
    },
    getCenter : function(){
        if(map){
            let center = this.map.getCenter();
            return {lat: center.getLat(),lng: center.getLng()};

        }
    },
    getLevel : function(){
        if(map){
            let level = this.map.getLevel();
            return level;

        }
    }
}
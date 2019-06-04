export default{
    createMap : function(container, options){
        return new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
    },
    mapOptions : function(options){
        //카카오 지도 레벨은 1~14까지 있음.
        return options;
    },
    LatLng : function(lat, lng){
        return new daum.maps.LatLng(lat, lng);
    },
    marker : function(option){
        new daum.maps.Marker(option);
    }
}
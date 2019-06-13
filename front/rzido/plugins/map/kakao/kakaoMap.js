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
    },
    searchBox : function(input, vue){
        let _this = this;
        let ps = new daum.maps.services.Places(); 
        let infowindow = new daum.maps.InfoWindow({zIndex:1});

        input.addEventListener("keypress",function(e){
            let key = e.which || e.keyCode;
            if (key === 13) { // 13 is enter
                // code for enter
                ps.keywordSearch(this.value, placesSearchCB,{location:_this.map.getCenter()}); 
            }
            
        });


        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB (data, status, pagination) {
            if (status === daum.maps.services.Status.OK) {
                vue.searchPlaces = [];
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                var bounds = new daum.maps.LatLngBounds();
                for (var i=0; i<data.length; i++) {
                
                    vue.searchPlaces.push({name:data[i].place_name,address:data[i].address_name,lat:data[i].y,lng:data[i].x,road_address:data[i].road_address_name})
                    displayMarker(data[i]);    
                    bounds.extend(new daum.maps.LatLng(data[i].y, data[i].x));
                }       

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                _this.map.setBounds(bounds);
            } 
        }

        // 지도에 마커를 표시하는 함수입니다
        function displayMarker(place) {
            
            // 마커를 생성하고 지도에 표시합니다
            var marker = new daum.maps.Marker({
                map: _this.map,
                position: new daum.maps.LatLng(place.y, place.x) 
            });
            // 마커에 클릭이벤트를 등록합니다
            daum.maps.event.addListener(marker, 'click', function() {
                // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infowindow.open(_this.map, marker);
            });
        }
    }
}
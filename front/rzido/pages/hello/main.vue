<template>
  <section class="container">
    <div class="map-con" id="map-con">
        <div id="map" style="width:100%;height:600px;">

        </div>
    </div>
    <div class="con-area">
        <div style="overflow: hidden;">
            <div>
                <div style="float:left">
                    <ul>
                        <template  v-if="!$store.state.userInfo">
                            <li><nuxt-link to='/hello/signin'>로그인</nuxt-link></li>
                            <li><nuxt-link to='/hello/signup'>회원가입</nuxt-link></li>
                        </template>
                        <template  v-else>
                            <li class="user">{{$store.state.userInfo.ieumUserName}}님 환영합니다.</li>
                            <li><button type="button" @click="logout">로그아웃</button></li>
                        </template>
                    </ul>
                </div>
                <div style="float:right">
                    <button type="button" @click="rgmap('kakao')">다음 지도</button><button type="button" @click="rgmap('google')">구글 지도</button>
                </div>
            </div>
            
            
            
        </div>
        <div>
          <input v-show="$store.state.map =='google'" id="pac-input" class="controls" type="text" placeholder="Search Box">
          <input v-show="$store.state.map =='kakao'" id="kakaoSearch" type="text" placeholder="Search Box">
          <div>
              <div v-for="item in searchPlaces" :key="item.id">
                {{item.name}}
              </div>
          </div>
        </div>
    </div>
    
  </section>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'
import rgMap from '~/plugins/map/rgMap.js'
import marker1 from '~/assets/images/markers/marker1.png'
export default {
  mounted(){
    this.rgmap(this.$store.state.map);
  },
  data() {
    return {
      options : {level : 10},
      markerList : [{lat : 36.341262, lng:127.389555, name:"1" },{lat : 36.341711, lng: 127.382860, name:"1" },{lat : 36.341711, lng: 127.382860, name:"1" },{lat : 36.348724, lng:127.368097, name:"1" },{lat : 36.347842, lng:127.371134, name:"1"}],
      searchPlaces : []
    }
  },
  methods: {
    rgmap : function(kind){
      this.$store.state.map = kind;
      let mapObj = rgMap.map(kind);
      
      let mapCon = document.getElementById('map-con');
      var div = document.createElement('div');
      div.id = "map";
      div.style.width= "100%";
      div.style.height= "600px";
      mapCon.innerHTML = div.outerHTML;

      document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      let container = document.getElementById('map')
      this.options.center = mapObj.LatLng(36.348724, 127.368097);
      let map = mapObj.createMap(container,this.options); //지도 생성 및 객체 리턴
      let imageSize = mapObj.size(41,63);
      let imageOption = {offset: mapObj.point(20, 63)};
      let markerImage = mapObj.markerImage(marker1, imageSize, imageOption);
      let input = null;
      if(kind=="google"){
        input = document.getElementById('pac-input');
      }else{
        input = document.getElementById('kakaoSearch');
      }
      
      mapObj.searchBox(input,this);

      mapObj.addListener(map,"center_changed", function(){
        //console.log(mapObj.getCenter());
      })

      mapObj.addListener(map,"zoom_changed", function(){
        console.log(mapObj.getLevel());
      })
      
      let markers = this.markerList.map(function(location){
          let marker = mapObj.marker({position:mapObj.LatLng(location.lat, location.lng)  ,image:markerImage/*, map:map */ });
          marker.name = location.name;
          mapObj.addListener(marker,"click", function(){
            alert("클릭");
          });
          return marker;
      });
      let clusterer = mapObj.markerClusterer(markers);
      
    },
    logout : function(){
      this.$store.dispatch('LOGOUT');
      /* this.$store.dispatch('LOGOUT');
	    next('/'); */
    }
  },
  components: {
    AppLogo
  }
}
</script>

<style>
ul li {list-style:none}
ul {
    list-style:none;
    margin:0;
    padding:0;
}

li {
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}
.container {
  
}
.con-area{
    display: inline-block;
    width: 29%;
    margin: 0;
    padding: 0;
    vertical-align: top;
}
.map-con {
    width: 70%;
    height: 600px;
    display: inline-block;
    margin: 0;
    padding: 0;
    vertical-align: top;
}
.map-btn-area {
    text-align: right;
    margin: 10px;
}

.title {
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>


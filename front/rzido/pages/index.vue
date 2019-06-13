<template>
  <section class="container">
      <div class="con-area">
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
    <div>
      <button type="button" @click="rgmap('kakao')">다음 지도</button><button type="button" @click="rgmap('google')">구글 지도</button>
      <div id="map" style="width:500px;height:400px;"></div>
    </div>
  </section>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'
import rgMap from '~/plugins/map/rgMap.js'
import marker1 from '~/assets/images/markers/marker1.png'
export default {
  mounted(){
    this.rgmap("kakao");
  },
  data() {
    return {
      options : {level : 3},
      markerList : [{lat : 36.348724, lng:127.368097 },{lat : 36.347842, lng:127.371134}],
    }
  },
  methods: {
    rgmap : function(kind){
      let mapObj = rgMap.map(kind);
      let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      this.options.center = mapObj.LatLng(36.348724, 127.368097);
      let map = mapObj.createMap(container,this.options); //지도 생성 및 객체 리턴
      let imageSize = mapObj.size(41,63);
      let imageOption = {offset: mapObj.point(20, 63)};
      let markerImage = mapObj.markerImage(marker1, imageSize, imageOption);

      mapObj.addListener(map,"center_changed", function(){
        console.log(mapObj.getCenter());
      })

      mapObj.addListener(map,"zoom_changed", function(){
        console.log(mapObj.getLevel());
      })
      
      this.markerList.forEach(element => {
        let marker = mapObj.marker({position:mapObj.LatLng(element.lat, element.lng),image:markerImage, map:map});
        mapObj.addListener(marker,"click", function(){
          alert("클릭");
        });
      });
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
.container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
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


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
      <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=152e92776f0d7051da092720060f2eb3"></script>
      <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAUYOsfi9dX-CdH9v4RB6tLcvRP03LrdvU&amp;callback=initMap" async defer></script>
      <button type="button" @click="kakaoMap">다음 지도</button><button type="button" @click="googleMap">구글 지도</button>
      <div id="map" style="width:500px;height:400px;"></div>
    </div>
  </section>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'
import rgMap from '~/plugins/map/rgMap.js'
export default {
  mounted(){
    this.kakaoMap();
  },
  data() {
    return {
      options : {level : 3}
    }
  },
  methods: {
    googleMap : function(){
      let mapObj = rgMap.map("google");
      let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      this.options.center = mapObj.LatLng(33.450701, 126.570667);
      let map = mapObj.createMap(container,mapObj.mapOptions(this.options)); //지도 생성 및 객체 리턴
      mapObj.marker({position:mapObj.LatLng(33.450701, 126.570667), map:map});
    },
    kakaoMap : function(){
      let mapObj = rgMap.map("kakao");
      let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      this.options.center = mapObj.LatLng(33.450701, 126.570667)
      let map = mapObj.createMap(container,mapObj.mapOptions(this.options)); //지도 생성 및 객체 리턴
      mapObj.marker({position:mapObj.LatLng(33.450701, 126.570667), map:map});
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


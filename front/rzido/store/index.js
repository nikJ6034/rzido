import Vue from 'vue'
import Vuex from 'vuex'
import $http from 'axios'
import $qs from 'qs';

Vue.use(Vuex)

    const state = {
        basePath : "http://localhost:8080",
        userInfo : null,
        isAdmin: false,
        map: "kakao",
        menu: {},
        menuLevel : [],
        kind : null,
        token : null,
    }

    const getters = {
        restWebPath : state=>{
            return `${state.basePath}/api/web`;
          },
        restAdminPath : state=>{
            return `${state.basePath}/api/admin`;
          }
    }

    const mutations = {
        LOGIN (state, session) {
            localStorage.ieumRefreshToken = session["refresh_token"];
            let userInfo = {};
            userInfo.ieumAccessToken = session["access_token"];
            userInfo.ieumUserName = session["name"];
            userInfo.ieumUserId = session["id"];
            state.userInfo = userInfo;
            $http.defaults.headers.common['Authorization'] = "Bearer "+session["access_token"];
          },
          LOGOUT (state) {
              state.userInfo = null;
             
              delete $http.defaults.headers.common["Authorization"];
              delete localStorage.ieumRefreshToken;
              if(Kakao){
                  Kakao.Auth.logout();
              }
          },
          LOGINCHECK (state, data){
            if(data){
              state.isAdmin = data.isAdmin||false;
            }
          },
          MENU(state, data) {
              state.menu = data;
          },
          SIGNUPREADY(state, data){
              state.kind = data.kind;
              state.token = data.token;
          }
      }

      export const actions = {
        LOGIN: function ({commit}, {memberName, memberPassword, kind}) {
            let str = $qs.stringify({
                grant_type: 'password',
                username: memberName,
                password: memberPassword,
                scope: 'openid',
                kind : kind
            });
            $http.request({
                url: `/oauth/token`,
                method: "post",
                baseURL: this.state.basePath,
                auth: {
                    username: "rzido", // This is the client_id
                    password: "rzido2019", // This is the client_secret
                },
                data: str,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then((result)=>{
                console.log(result.data);
                commit('LOGIN', result.data);
                this.$router.push('/');
            }).catch((error)=>{
                if(error.response.data.error == "invalid_grant"){
                    let data = {};
                    data.token = memberName;
                    data.kind = kind;
                    commit('SIGNUPREADY', data);
                    this.$router.push('/hello/signup/userinfo');
                }
            })
                
                
        },
      REFRESHTOKEN : function({commit},refresh_token){
          let str = $qs.stringify({
              "grant_type":"refresh_token",
              "refresh_token":refresh_token
          });
          $http.request({
              url: `/oauth/token`,
              method: "post",
              baseURL: this.state.basePath,
              auth: {
                  username: "rzido", // This is the client_id
                  password: "rzido2019", // This is the client_secret
              },
              data: str,
              headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
              }
          }).then(({data})=>{
            commit('LOGIN', data);
          })
          
      },
      LOGOUT ({commit}) {
          commit('LOGOUT');
      },
      LOGINCHECK ({commit}, {session}){
          commit('LOGINCHECK', session);
      }
    }

 const store = () => new Vuex.Store({
    state,
    getters,
    mutations,
    actions
  })
  
  export default store;
<template>
    <div>
        <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
        <button type="button" @click="kakao">카카오</button>
        <button type="button" @click="google">구글</button>
    </div>
</template>

<script>
    export default {
        methods: {
            kakao : function(){
                let _this = this;
                Kakao.init('152e92776f0d7051da092720060f2eb3');
                Kakao.Auth.loginForm({success: function(authObj) {
                    _this.$store.dispatch('LOGIN', {memberName:authObj.access_token, password:"", kind : "kakao"});
                },
                fail: function() {
                    alert("로그인에 실패하였습니다.");
                }})
            },
            google : function(){
                
            }
        },
    }
</script>

<style lang="scss" scoped>

</style>
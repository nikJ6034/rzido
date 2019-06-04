<template>
    <div>
        <button type="button" @click="signin">sign in</button>
        <button type="button" @click="signup">sign up</button>
    </div>
</template>

<script>
import router from 'vue-router'
    export default {
        methods: {
            signin : function(){
                this.$router.push('/hello/signin')
            },
            signup : function(){
                this.$router.push('/hello/signup')
            }
        },
    }
</script>

<style lang="scss" scoped>

</style>
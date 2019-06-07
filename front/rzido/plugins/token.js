export default function ({ app, store }) {
  
    app.router.onReady(() => {
      if (process.client) {
        Kakao.init('152e92776f0d7051da092720060f2eb3');
        const {ieumRefreshToken} = localStorage;
        if(ieumRefreshToken){
          store.dispatch('REFRESHTOKEN',ieumRefreshToken);
        }
      }
    })
  }
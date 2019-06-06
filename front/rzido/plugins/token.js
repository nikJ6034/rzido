export default function ({ app, store }) {
  
    app.router.onReady(() => {
      if (process.client) {
        const {ieumRefreshToken} = localStorage;
        store.dispatch('REFRESHTOKEN',ieumRefreshToken);
      }
    })
  }
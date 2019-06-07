module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'rzido',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Nuxt.js project' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ],
    script : [
      {src : "//developers.kakao.com/sdk/js/kakao.min.js"},
      {src : "//dapi.kakao.com/v2/maps/sdk.js?appkey=152e92776f0d7051da092720060f2eb3"},
      {src : "//maps.googleapis.com/maps/api/js?key=AIzaSyAUYOsfi9dX-CdH9v4RB6tLcvRP03LrdvU&amp;callback=initMap"},
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
   vendor: ['axios'],
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  },
  plugins: [{src: '~/plugins/route.js', ssr: true},'~/plugins/token']
}


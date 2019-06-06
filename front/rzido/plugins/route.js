export default ({ app }) => {
    // Every time the route changes (fired on initialization too)


    
    app.router.beforeEach( (to, from, next) => {
        // ...후
    
        console.log("라우터 변경이 일어날 때 전처리 하는 부분 입니다.");
        next();
    });

    app.router.afterEach((to, from) => {
        console.log("라우터 변경이 일어날 때 후처리 하는 부분 입니다."+from);
        //console.log(123);
      
    });

    
 };
